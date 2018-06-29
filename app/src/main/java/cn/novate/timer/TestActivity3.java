package cn.novate.timer;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Email: 2185134304@qq.com
 * Created by Novate 2018/6/29 16:50
 * Version 1.0
 * Params:
 * Description:
*/

public class TestActivity3 extends AppCompatActivity {


    private Timer timer;
    private TimerTask timerTask;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // 0.5秒之后，每隔2秒 调用一次 接口
//                doPayMoney(paymentCodeThing);
//                Log.e("TAG" , "我爱王子文") ;
            }
        }, 500, 2000);
    }
}
