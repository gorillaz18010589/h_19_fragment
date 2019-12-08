package tw.org.iii.appps.h_19_fragment;
//1.只留三個onCreateView, onAttach, onDetach()
//2.開始設計f1.xml頁面
//3.物件實體化 mainView;//這個f1的主畫面把它變成主畫面,就可以在這畫面裡面找按鈕來玩
//4.按下按鈕產生樂透,但是跳頁回來樂透值就消失,因為每一次回來就又再創一次,所以再創要視加上IF(MainView == null)
//5.代表一開始MainView空會進來指向,但如果不是null就不用重新指向
//6.不同的Frgatment,可以玩到同樣得直
//7.讓MainACtivity讓f1認識去玩,宣告後,事實上是onAttahc(Context context)收到而來的


//if (mainView == null) {//如果你頁面一開始不是空的,就做以下事情
//        mainView = inflater.inflate(R.layout.fragment_f1, container, false); //(1.浮現出版面)
//
//        tv = mainView.findViewById(R.id.f1_tv);//抓到顯示頁面
//
//        btn = mainView.findViewById(R.id.f1_btn);//在主畫面裡面,去找到她肚子裡的f1按鈕
//        btn.setOnClickListener(new View.OnClickListener() { //這個按鈕設置是件,(螢幕的點擊事件)
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class f1 extends Fragment {
    private MainActivity mainActivity; //讓MainActivity給f1認識
    private View mainView;//這個f1的主畫面
    private TextView tv;
    private Button btn,btn2,btn3;

    //1.當這個Fragment被叫時創造時出現,1.inFlate:浮現的視窗
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
//        Log.v("brad","onCreateView");
        if(mainView == null){//如果一開始是空,近來給值創造指向
            mainView =  inflater.inflate(R.layout.fragment_f1, container, false);//inflater浮現出你的版面,這就是指定你一開始創造的那個畫面
            tv = mainView.findViewById(R.id.f1_tv);

             btn = mainView.findViewById(R.id.f1_btn);//從我的mainView肚子哩,找到id按鈕來玩
            btn2 = mainView.findViewById(R.id.f1_btn2);
            btn3 = mainView.findViewById(R.id.f1_btn3);

            //按鈕1有了按鈕就設置按鈕觸發事件
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f1BtnClick();
                }
            });

            //按鈕2有了按鈕就設置按鈕觸發事件
            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f1Btn2Click();
                }
            });

            //按鈕3有了按鈕就設置按鈕觸發事件
            btn3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    f1Btn3Click();
                }
            });
        }
        //如果不是空代表有指向,直接回傳這個值就可以讓參數不會因為換頁而消失
        return mainView;
    }

    //2.按下按鈕1觸發的方法
    private  void f1BtnClick(){
        tv.setText("樂透號碼:" + (int)(Math.random()*38+1));
        Log.v("brad","f1BtnClick有道");
    }

    //3.按下按鈕2,玩main_title物件
    private  void  f1Btn2Click(){
        mainActivity.setMainTitlte("這是在f1頁面玩MainActivty的title");
        Log.v("brad","f1Btn2Click有到");
    }
    //按下按鈕3
    private  void  f1Btn3Click(){
        mainActivity.getf2().setF2itlte("按下f1裡的按鈕3,可以玩f2的title");
    }

    //3.掛上去時會呼叫,並把context傳到別的分頁,呼叫mainactivity讓我f1認識就可以去玩他的東西
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //如果這個頁面是同一個activity這樣玩,沒有的話走其他
        if(context instanceof  MainActivity){
            Log.v("brad","context有含在裡面");
        }

        mainActivity = (MainActivity) context; //把抓到的context,轉乘Mainactivity讓我這隻f1認識
        Log.v("brad","onAttach");
    }
    //解除時跳別的f1會出現
    @Override
    public void onDetach() {
        super.onDetach();
        Log.v("brad","onDetach");
    }
}
