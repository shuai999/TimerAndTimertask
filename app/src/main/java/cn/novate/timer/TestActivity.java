package cn.novate.timer;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Administrator on 2018/6/27.
 */

public class TestActivity extends Activity {
    private TextView timer;//显示时间的控件
    private int recLen = 60;//定时长度
    private Button startRec, stopRec;//控制开始计时和停止计时的按钮
    private static final int START = 0;//开始计时消息标志，下面用到
    private static final int STOP = 1;//停止计时消息标志，下面用到
    private static int RUN = 0;//子线程中的while循环判断标志
    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        timer = (TextView) findViewById(R.id.timer);

        startRec = (Button) findViewById(R.id.start);
        stopRec = (Button) findViewById(R.id.stop);
        startRec.setOnClickListener(listener);
        stopRec.setOnClickListener(listener);

    }

    public View.OnClickListener listener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.start:
                    RUN = 0;
                    thread = new Thread(new MyThread()); // start thread
                    thread.start();
                    break;
                case R.id.stop:
//              long tid = thread.getId();
                    RUN = 1111;
                    thread.interrupt();
                    thread.run();
                    Message message = new Message();
                    message.what = STOP;
                    handler.sendMessage(message);
                    break;
                default:
                    break;
            }

        }

    };



    final Handler handler = new Handler() { // handle
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case START:
                    recLen--;
                    timer.setText("" + recLen);
                    break;
                case STOP:
                    timer.setText("" + recLen);
                    break;
            }
            super.handleMessage(msg);
        }
    };

    public class MyThread implements Runnable { // thread
        @Override
        public void run() {
            while (0 == RUN) {
                try {
                    Thread.sleep(1000); // sleep 1s
                    Message message = new Message();
                    message.what = START;
                    handler.sendMessage(message);
                } catch (Exception e) {
                }
            }
        }
    }
}
