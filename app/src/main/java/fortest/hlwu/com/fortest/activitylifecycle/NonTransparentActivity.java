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

public class NonTransparentActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_life_cycle_2);
        getSupportActionBar().setTitle("NonTransparentActivity");
        ((TextView) findViewById(R.id.life_cycle2_text)).setText("NonTransparentActivity's Text");
        Log.d("flaggg", "NonTransparentActivity.onCreate");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("flaggg", "NonTransparentActivity.onRestoreInstanceState");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("flaggg", "NonTransparentActivity.onSaveInstanceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("flaggg", "NonTransparentActivity.onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("flaggg", "NonTransparentActivity.onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("flaggg", "NonTransparentActivity.onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("flaggg", "NonTransparentActivity.onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("flaggg", "NonTransparentActivity.onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("flaggg", "NonTransparentActivity.onStart");
    }
}
