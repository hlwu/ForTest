package fortest.hlwu.com.fortest.multi_process;

import android.app.Activity;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.Nullable;
import android.util.Log;

import fortest.hlwu.com.fortest.R;

/**
 * Created by hlwu on 4/1/18.
 */

public class NewProcessActivity extends Activity {

    public static int staticInt = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_life_cycle);
        Log.d("flaggg", "NewProcessActivity.onCreate  staticInt: " + staticInt + "; Pid: " + Process.myPid());
    }
}
