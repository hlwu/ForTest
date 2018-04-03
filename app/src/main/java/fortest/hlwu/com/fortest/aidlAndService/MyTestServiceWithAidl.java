package fortest.hlwu.com.fortest.aidlAndService;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Process;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import fortest.hlwu.com.fortest.aidl.IMyTestAidlInterface;

/**
 * Created by hlwu on 4/2/18.
 */

public class MyTestServiceWithAidl extends Service {
    private IMyTestAidlInterface.Stub aidlStub = new IMyTestAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            Log.d("flaggg", "aidlStub.basicTypes");
        }

        @Override
        public void updateSomething() throws RemoteException {
            Log.d("flaggg", "aidlStub.updateSomething");
        }
    };

    public class MyTestBinder extends Binder {
        public Service getService() {
            return MyTestServiceWithAidl.this;
        }
    }
    private Binder mbinder = new MyTestBinder();

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.d("flaggg", msg.getData().getString("msg", "default string at service"));
                    Messenger client = msg.replyTo;
                    if (client != null) {
                        Message msg1 = Message.obtain(null, 2);
                        Bundle b = new Bundle();
                        b.putString("msg", "this is server msg");
                        msg1.setData(b);
                        try {
                            Thread.sleep(3000);
                            client.send(msg1);
                        } catch (RemoteException e) {
                            Log.d("flaggg", "got RemoteException at service");
                        } catch (InterruptedException e) {
                            Log.d("flaggg", "got InterruptedException when sleep");
                        }
                    }

            }
        }
    };

    private Messenger mMessenger = new Messenger(mHandler);

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d("flaggg", "MyTestServiceWithAidl.onUnbind");
        return super.onUnbind(intent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d("flaggg", "MyTestServiceWithAidl.onBind");
//        mHandler.sendMessageDelayed(mHandler.obtainMessage(1), 5000);
//        return aidlStub;  //1. 返回AIDL 的stub.
//        return mbinder;   //2. 返回自己的Binder
        return mMessenger.getBinder();  //3. 返回通过handler 创建的messenger 的binder.
    }
}
