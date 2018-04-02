package fortest.hlwu.com.fortest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import fortest.hlwu.com.fortest.activitylifecycle.LifeCycleActivity;
import fortest.hlwu.com.fortest.aidl.MyTestServiceWithAidl;
import fortest.hlwu.com.fortest.multi_process.NewProcessActivity;
import fortest.hlwu.com.fortest.thread_synchronized.MyThreadTest;
import fortest.hlwu.com.fortest.valuetransfer.ValueTransfer;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mSc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("MainActivity");
        Log.d("flaggg", "MainActivity.onCreate task: " + getTaskId());
        ((Button) findViewById(R.id.start_life_cycle_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LifeCycleActivity.class));
            }
        });

        ((Button) findViewById(R.id.start_thread_test_button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyThreadTest().run3ThreadWithLock();   //使用lock
//                new MyThreadTest().run3ThreadWithLock2();   //使用lock，每个线程分开执行
//                new MyThreadTest().run3ThreadWithSynchronized();    //使用synchronized 锁increase()方法,
                new MyThreadTest().run3ThreadWithSynchronized2();    //使用synchronized 锁run() 方法块, 每个线程分开执行
            }
        });

        ((Button) findViewById(R.id.start_value_transfer_button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ValueTransfer().valueTest();
            }
        });

        ((Button) findViewById(R.id.start_new_process_button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewProcessActivity.staticInt = 2;
                startActivity(new Intent(MainActivity.this, NewProcessActivity.class));
            }
        });

        ((Button) findViewById(R.id.bind_service_button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSc = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        Log.d("flaggg", "onServiceConnected, componentName: " + name + "; serviceBinder: " + service);
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d("flaggg", "onServiceDisconnected, componentName: " + name);
                    }
                };
                bindService(new Intent(MainActivity.this, MyTestServiceWithAidl.class), mSc, BIND_AUTO_CREATE);
            }
        });

        ((Button) findViewById(R.id.unbind_service_button6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSc != null) {
                    unbindService(mSc);
                }
            }
        });
    }
}
