package fortest.hlwu.com.fortest.activitylifecycle;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import fortest.hlwu.com.fortest.R;

/**
 * Created by hlwu on 3/29/18.
 */

public class TransparentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_life_cycle_2);
        getSupportActionBar().setTitle("TransparentActivity");
        ((TextView) findViewById(R.id.life_cycle2_text)).setText("TransparentActivity's Text");
        Log.d("flaggg", "TransparentActivity.onCreate");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("flaggg", "TransparentActivity.onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("flaggg", "TransparentActivity.onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("flaggg", "TransparentActivity.onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("flaggg", "TransparentActivity.onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("flaggg", "TransparentActivity.onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("flaggg", "TransparentActivity.onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("flaggg", "TransparentActivity.onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("flaggg", "TransparentActivity.onStart");
    }
}
