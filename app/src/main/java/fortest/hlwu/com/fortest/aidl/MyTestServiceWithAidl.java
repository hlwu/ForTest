package fortest.hlwu.com.fortest.aidl;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

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
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return aidlStub;
    }
}
