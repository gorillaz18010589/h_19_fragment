package tw.org.iii.appps.h_19_fragment;
//這裡打算做計時器
//當f2被呼叫實體時,這個iCount秒數慢慢加,並且顯示
//代表他到f2這頁還能自己做volley等事情,頁面處理更獨立
////這個f2_title要給f1來玩
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A simple {@link Fragment} subclass.
 */
public class f2 extends Fragment {
    private View mainView;
    private TextView tv;
    private Timer timer;
    private  int iCount;//次數
    private  UIhandle handle;
    private TextView title; //這個f2_title要給f1來玩


    //2.建構時就啟動這個計時器
    public  f2(){
        timer = new Timer();
        timer.schedule(new MyTask(),0,1000*1);//排程1秒一次
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(mainView == null){//如果一開始為空的話
            mainView =inflater.inflate(R.layout.fragment_f2, container, false);//這個f2浮現畫面變成mainView
            tv = mainView.findViewById(R.id.f2_tv);//從這個f2裡面抓到tv
            handle = new UIhandle();
            title = mainView.findViewById(R.id.f2_title);//這個f2_title要給f1來玩
        }
       return mainView;
    }

    //2.這個方法提供給f1去玩
    public  void  setF2itlte (String titlteString){
        if(title!= null) { //如果titlte有物件實體近來,再讓他玩
            title.setText(titlteString);
        }
    }

    //1.時間到要做的事情,想把時間顯示在TV上面
    private  class MyTask extends TimerTask{
        @Override
        public void run() {
            if(tv!=null){
                iCount ++;
                handle.sendEmptyMessage(0);
                Log.v("brad","有來Mytask傳送Message到handler");
            }
        }
    }

    private  class  UIhandle extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            tv.setText("" + iCount);
            Log.v("brad","有來UIhanler" + msg);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
