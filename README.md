## MPAndroidChartFirstSample
Sample for using "MPAndroidChart" in AndroidStudio4.0 (this sample in private)  
<br />  
  
  
### 【まえがき】
 このサンプルはAndroidStudio4.0でMPAndroidChartを使うための自分用覚書です。  
 Android開発初心者でMPAndroidChartも当然初心者レベルです。  
 だから、コピペして動かなくても文句言わないでください。（笑  
  
  
### 【基本的な使い方】  
  ０．まず事前にGradleの設定ファイル「build.gradle」に以下の設定を追記する。（このプロジェクトのファイルを参考に）  
  　repositories {  
  　  maven { url 'https://jitpack.io' }  
  　}  
  　  
  　dependencies {  
  　   implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'  
  　}  
  
  １．activity_main/xmlで使うチャートを定義しておく。名前とか位置の制約とか。  
  ２．MainActivityのクラス宣言部分で「OnChartValueSelectedListener」を記述する。  
  　　これでチャートのイベントリスナを設定する感じ。  
  ３．mTypefaceという名前でTypeface（外観？）を定義  
  ４．チャートを実装するときに必要になる以下のイベントをoverrideで記述する。（記述してないとビルド時にエラーになる）  
  　・「onValueSelected」  
  　・「onNothingSelected」  
  ５．setupLineChartメソッドの中でチャートの設定をしている。（メソッド名はなんでもいい、軸やラベルの設定はここでやっている）  
  ６．lineDataWithCountメソッドの中でチャートのデータを作成している。（ここは実際に使うデータによって修正が必要）  
  ７．元からあるonCreateイベントの中で上記5と6で作ったメソッドを使用してチャートの初期設定をおこなっている。  
  ８．基本はこれでチャートは表示されるが、チャートの種類によっては設定が異なる模様。  
  　　あと、データが変更された際の再描画などは自動なのかどうか不明。  
  ９．一番上のimportしているところでいくつか追加しているので、コードを書いてて参照できないエラーが出る場合はimportの不足をチェックするとよいかも。（多分名前空間が参照できていない）  
   
  
### 【参考サイト】  
 ・MPAndroidChartの設定は以下のサイトを参考にしました。  
  https://re-engines.com/2019/03/11/kotlin-mpandroidchart%e3%83%a9%e3%82%a4%e3%83%96%e3%83%a9%e3%83%aa%e3%82%92%e4%bd%bf%e3%81%84%e7%a7%bb%e5%8b%95%e5%b9%b3%e5%9d%87%e7%b7%9a%e3%82%92%e6%8f%8f%e7%94%bb%e3%81%97%e3%81%a6%e3%81%bf/  
 　※但し、大変なので、３つチャートがあるうちの一番上だけ設定してます。  

