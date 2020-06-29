package com.example.hellotestapp

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_main.*


//OnChartValueSelectedListenerリスナを登録する準備
class MainActivity : AppCompatActivity(), OnChartValueSelectedListener {

    // MPAndroidChartの設定は以下のサイトを参考に。
    // https://re-engines.com/2019/03/11/kotlin-mpandroidchart%e3%83%a9%e3%82%a4%e3%83%96%e3%83%a9%e3%83%aa%e3%82%92%e4%bd%bf%e3%81%84%e7%a7%bb%e5%8b%95%e5%b9%b3%e5%9d%87%e7%b7%9a%e3%82%92%e6%8f%8f%e7%94%bb%e3%81%97%e3%81%a6%e3%81%bf/
    // 但し、大変なので、３つチャートがあるうちの一番上だけ設定してます。

    // まず事前にGradleの設定ファイル「build.gradle」に以下の設定を追記する。（このプロジェクトのファイルを参考に）
    //  repositories {
    //    maven { url 'https://jitpack.io' }
    //  }
    //
    //  dependencies {
    //     implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'
    //  }

    // 基本的な使い方は
    // １．activity_main/xmlで使うチャートを定義しておく。名前とか位置の制約とか。
    // ２．MainActivityのクラス宣言部分で「OnChartValueSelectedListener」を記述する。
    //   　これでちゃとのイベントリスナを設定する感じ。たぶん。
    // ３．mTypefaceという名前でTypeface（外観？）を定義
    // ４．チャートを実装するときに必要になる以下のイベントをoverrideで記述する。（記述してないとビルド時にエラーになる）
    //  　・「onValueSelected」
    //  　・「onNothingSelected」
    // 5．setupLineChartメソッドの中でチャートの設定をしている。（メソッド名はなんでもいい、軸やラベルの設定はここでやっている）
    // 6．lineDataWithCountメソッドの中でチャートのデータを作成している。（ここは実際に使うデータによって修正が必要）
    // 7．元からあるonCreateイベントの中で上記5と6で作ったメソッドを使用してチャートの初期設定をおこなっている。
    // 8．基本はこれでチャートは表示されるが、チャートの種類によっては設定が異なる模様。
    //  　あと、データが変更された際の再描画などは自動なのかどうか不明。
    // 9．一番上のimportしているところでいくつか追加しているので、コードを書いてて参照できないエラーが出る場合はimportの不足をチェックするとよいかも。（多分名前空間が参照できていない）

    private val chartDataCount = 20
    //Typefaceの設定、後ほど使用します。
    private var mTypeface = Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL)

    override fun onValueSelected(e: Entry, h: Highlight) {
        Log.i("Entry selected", e.toString())
    }

    override fun onNothingSelected() {
        Log.i("Nothing selected", "Nothing selected.")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupLineChart()
        lineChart.data = lineDataWithCount(chartDataCount, 100f)
    }

    private fun setupLineChart(){
        lineChart.apply {
            setOnChartValueSelectedListener(this@MainActivity)
            description.isEnabled = false
            setTouchEnabled(true)
            isDragEnabled = true
            isScaleXEnabled = true
            setPinchZoom(false)
            setDrawGridBackground(false)

            //データラベルの表示
            legend.apply {
                form = Legend.LegendForm.LINE
                typeface = mTypeface
                textSize = 11f
                textColor = Color.BLACK
                verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
                horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
                orientation = Legend.LegendOrientation.HORIZONTAL
                setDrawInside(false)
            }

            //y軸右側の表示
            axisRight.isEnabled = false

            //X軸表示
            xAxis.apply {
                typeface = mTypeface
                setDrawLabels(false)
                setDrawGridLines(true)
            }

            //y軸左側の表示
            axisLeft.apply {
                typeface = mTypeface
                textColor = Color.BLACK
                setDrawGridLines(true)
            }
        }
    }

    //    LineChart用のデータ作成
    private fun lineDataWithCount(count: Int, range: Float): LineData {

        val values = mutableListOf<Entry>()

        for (i in 0 until count) {
            val value = (Math.random() * (range)).toFloat()
            values.add(Entry(i.toFloat(), value))
        }
        // create a dataset and give it a type
        val yVals = LineDataSet(values, "SampleLineData").apply {
            axisDependency =  YAxis.AxisDependency.LEFT
            color = Color.BLACK
            highLightColor = Color.YELLOW
            setDrawCircles(false)
            setDrawCircleHole(false)
            setDrawValues(false)
            lineWidth = 2f
        }
        val data = LineData(yVals)
        data.setValueTextColor(Color.BLACK)
        data.setValueTextSize(9f)
        return data
    }
}
