package fortest.hlwu.com.fortest;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

import fortest.hlwu.com.fortest.activitylifecycle.LifeCycleActivity;
import fortest.hlwu.com.fortest.aidl.IMyTestAidlInterface;
import fortest.hlwu.com.fortest.aidlAndService.MyTestServiceWithAidl;
import fortest.hlwu.com.fortest.linkedlist.MyLinkedList;
import fortest.hlwu.com.fortest.multi_process.NewProcessActivity;
import fortest.hlwu.com.fortest.socket.ClientActivity;
import fortest.hlwu.com.fortest.thread_synchronized.MyThreadTest;
import fortest.hlwu.com.fortest.thread_synchronized.MyThreadTest2;
import fortest.hlwu.com.fortest.valuetransfer.ValueTransfer;
import fortest.hlwu.com.fortest.view.ViewTestActivity;

public class MainActivity extends AppCompatActivity {

    private ServiceConnection mSc;
    private boolean serviceBound = false;

    private class WeakHandler extends Handler {
        private WeakReference<Activity> activityWeekReference;

        WeakHandler(Activity a) {
            activityWeekReference = new WeakReference<Activity>(a);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 2:
                    if (activityWeekReference.get() != null) {
                        Log.d("flaggg", msg.getData().getString("msg", "default string at client"));
                    } else {
                        Log.d("flaggg", "MainActivity has already destroyed");
                    }
            }
        }
    }
    private Handler mMessengerHandler = new WeakHandler(this);
    private Messenger mMessenger = new Messenger(mMessengerHandler);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("flaggg", "MainActivity onDestroy");
    }

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

        ((Button) findViewById(R.id.start_thread_test_button2_2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyThreadTest2().startIncreaseTest();
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
                        //1. 拿到AIDL 的返回.
//                        try {
////                            (IMyTestAidlInterface.Stub.asInterface(service)).updateSomething(); //非本地进程的service 是Stub.Proxy
////                            ((IMyTestAidlInterface.Stub) service).updateSomething();    //本地进程的service 是Stub.
//                        } catch (RemoteException e) {
//                            Log.d("flaggg", "got RemoteException when do updateSomething()");
//                        }

                        //2. 拿到Service 的Binder.
//                        MyTestServiceWithAidl.MyTestBinder binder = (MyTestServiceWithAidl.MyTestBinder) service;

                        //3. 拿到Messenger 的Binder.
                        Messenger m = new Messenger(service);
                        Message msg = Message.obtain(null, 1);
                        Bundle b = new Bundle();
                        b.putString("msg", "this is client msg");
                        msg.setData(b);
                        msg.replyTo = mMessenger;
                        try {
                            m.send(msg);
                        } catch (RemoteException e) {
                            Log.d("flaggg", "got Remote when send msg by Messenger");
                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {
                        Log.d("flaggg", "onServiceDisconnected, componentName: " + name);
                    }
                };
                serviceBound = bindService(new Intent(MainActivity.this, MyTestServiceWithAidl.class), mSc, BIND_AUTO_CREATE);
            }
        });

        ((Button) findViewById(R.id.unbind_service_button6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("flaggg", "serviceBound: " + serviceBound);
                if (serviceBound) {
                    unbindService(mSc);
                    serviceBound = false;
                }
            }
        });

        ((Button) findViewById(R.id.run_linked_list_button7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyLinkedList mll = new MyLinkedList();
                mll.addNodeAtLast("aaa");
                mll.addNodeAtLast("bbb");
                mll.addNodeAtLast("ccc");
                mll.addNodeAtLast("ddd");
                mll.printList();
                Log.d("flaggg", "add done, then remove last");
                mll.removeLastNode();
                mll.printList();
                Log.d("flaggg", "remove last done, insert node at 0");
                mll.insertNodeAt(0, "000");
                mll.printList();
                Log.d("flaggg", "insert node at 2");
                mll.insertNodeAt(2, "111");
                mll.printList();
                Log.d("flaggg", "insert node at 5");
                mll.insertNodeAt(5, "222");
                mll.printList();
                Log.d("flaggg", "delete node at 0");
                mll.deleteNodeAt(0);
                mll.printList();
                Log.d("flaggg", "delete node at 1");
                mll.deleteNodeAt(1);
                mll.printList();
            }
        });

        ((Button) findViewById(R.id.run_socket_button8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ClientActivity.class));
            }
        });

        ((Button) findViewById(R.id.view_test_button9)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ViewTestActivity.class));
            }
        });
    }
}
