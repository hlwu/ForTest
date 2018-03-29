package fortest.hlwu.com.fortest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fortest.hlwu.com.fortest.activitylifecycle.LifeCycleActivity;
import fortest.hlwu.com.fortest.thread_synchronized.MyThreadTest;
import fortest.hlwu.com.fortest.valuetransfer.ValueTransfer;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("MainActivity");
        ((Button) findViewById(R.id.start_life_cycle_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LifeCycleActivity.class));
            }
        });

        ((Button) findViewById(R.id.start_life_cycle_button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new MyThreadTest().run3ThreadWithLock();   //使用lock
//                new MyThreadTest().run3ThreadWithLock2();   //使用lock，每个线程分开执行
//                new MyThreadTest().run3ThreadWithSynchronized();    //使用synchronized 锁increase()方法,
                new MyThreadTest().run3ThreadWithSynchronized2();    //使用synchronized 锁run() 方法块, 每个线程分开执行
            }
        });

        ((Button) findViewById(R.id.start_life_cycle_button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ValueTransfer().valueTest();
            }
        });
    }
}
