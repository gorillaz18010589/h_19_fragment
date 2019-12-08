package tw.org.iii.appps.h_19_fragment;
//Fragment,整個還是一個Activity但可以點選不同分頁片段還在同一頁
//android Fragment 一率擺在activity,但會受到activity影響
//通常是為了平板而設計,重點是切換
//等下會使用第三方的api去選擇檔案,人家寫好的還有畫面,可能會用fragment選取檔案再跑回來
//開始交易有進有出
//1.先設計版面
//2.接下來設計一個一個Fradgeemnt:app => Fragment =>blankFragment(剛開始都用空白練習)進入fragment處理,建議不要打勾
//3.//只留三個onCreateView, onAttach, onDetach()
//3.1開始設計f1.xml頁面
//4.回到這邊用FragmentManager,取得交易,開始轉換頁面
//Main的地位是指管理要到哪個頁面,按下按鈕後的邏輯相關,是在各自f1~f4按鈕


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fmgr; //Frament經理人
    private f1 f1;
    private f2 f2;
    private f3 f3;
    private f4 f4;
    private TextView title; //main_title這個要給f1玩
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.main_title);

        fmgr =  getSupportFragmentManager(); // 透過這招取得Fragment經理人(回傳到FragmentManager)
        f1 = new f1(); //f1頁面物件實體化
        f2 = new f2();
        f3 = new f3();
        f4 = new f4();

        //頁面有進有出就是一種交易的感覺,這是一開始產生f1_frgament頁面,一開始沒有所以用add
       FragmentTransaction transaction = fmgr.beginTransaction();//從Frgment經理人裡面,取得開始交易物件(回傳到FragmentTransaction)
       //FragmentTransaction.add(@IdRes int containerViewId, @NonNull Fragment fragment)
       transaction.add(R.id.container,f1);//(1.我要被加的FrameLayout頁面,2.要進來的的Fragment頁面)
       transaction.commit();//交易commit執行
        Log.v("brad","有commit到");
    }
    //2.讓這裡的main_title可以給f1玩,提供一個公開方法
    public  void  setMainTitlte (String titlteString){
        title.setText(titlteString);
    }

    //3.讓f1認識f2的titile方法
    public f2 getf2(){
        return  f2;
    }
    //按下按鈕換成f1
    public void test1(View view) {
        FragmentTransaction transaction = fmgr.beginTransaction();
        //replace(@IdRes int containerViewId, @NonNull Fragment fragment)://切換Fragment(1.我要被加的FrameLayout頁面,2.要換的Fragment頁面)
        transaction.replace(R.id.container,f1);//切換Fragment(1.我要被加的FrameLayout頁面,2.要換的Fragment頁面)
        transaction.commit();
        Log.v("brad","有切換到F1");
    }
    //按下按鈕換成f2
    public void test2(View view) {
        FragmentTransaction transaction = fmgr.beginTransaction();
        transaction.replace(R.id.container,f2);
        transaction.commit();
        Log.v("brad","有切換到F2");
    }
    public void test3(View view) {
        FragmentTransaction transaction = fmgr.beginTransaction();
        transaction.replace(R.id.container,f3);
        transaction.commit();
        Log.v("brad","有切換到F3");
    }
    public void test4(View view) {
        FragmentTransaction transaction = fmgr.beginTransaction();
        transaction.replace(R.id.container,f4);
        transaction.commit();
        Log.v("brad","有切換到F4");
    }
}
