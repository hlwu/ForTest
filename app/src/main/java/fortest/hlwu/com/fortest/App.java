package fortest.hlwu.com.fortest;

import android.app.Application;
import android.os.Process;
import android.util.Log;

/**
 * Created by hlwu on 4/1/18.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("flaggg", "create Application, Pid: " + Process.myPid());
    }
}
