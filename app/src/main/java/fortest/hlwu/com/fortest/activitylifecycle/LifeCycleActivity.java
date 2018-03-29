package fortest.hlwu.com.fortest.activitylifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fortest.hlwu.com.fortest.R;

/**
 * Created by hlwu on 3/29/18.
 *
 * onStart,activity可见; onResume, acitivyt位于前台, 拿到focus, 可交互.
 *
 * 正常：onCreate->onStart->onResume->onPause->onStop->onDestory
 * 启动一个普通activity A.onPause->B.onCreate->B.onStart->B.onResume->A.onStop
 * 启动一个透明activity A.onPause->B.onCreate->B.onStart->B.onResume
 * 横竖屏切换：onSavedInstanceState->onDestory->onCreate->onRestoreInstanceState
 */

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_life_cycle);
        Log.d("flaggg", "LifeCycleActivity.onCreate");
        getSupportActionBar().setTitle("LifeCycleActivity");
        ((Button) findViewById(R.id.life_cycle_button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeCycleActivity.this, TransparentActivity.class));
            }
        });
        ((Button) findViewById(R.id.life_cycle_button2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeCycleActivity.this, NonTransparentActivity.class));
            }
        });
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d("flaggg", "LifeCycleActivity.onRestoreInstanceState");
        String s = savedInstanceState.getString("string");
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("flaggg", "LifeCycleActivity.onSaveInstanceState");
        outState.putString("string", "saved string..........");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("flaggg", "LifeCycleActivity.onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("flaggg", "LifeCycleActivity.onStop");
//        Log.d("flaggg", "sleep 10s");
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            Log.d("flaggg", "sleep got exception");
//        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("flaggg", "LifeCycleActivity.onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("flaggg", "LifeCycleActivity.onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("flaggg", "LifeCycleActivity.onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("flaggg", "LifeCycleActivity.onStart");
//        Log.d("flaggg", "sleep 10s");
//        try {
//            Thread.sleep(10000);
//        } catch (Exception e) {
//            Log.d("flaggg", "sleep got exception");
//        }
    }
}
