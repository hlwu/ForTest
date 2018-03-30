package fortest.hlwu.com.fortest.activitylifecycle;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
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
 * onCreate, 加载布局, 初始化等操作; onStart, activity可见; onResume, activity位于前台, 拿到focus, 可交互;
 * onRestart, 重新从后台到前台, 如按Home 再进入; onSavedInstanceState, 被意外杀掉, 在onDestroy 前调用,
 * 在重新onCreate 后调用 onRestoreInstanceState.
 *
 * 正常生命周期：onCreate->onStart->onResume->onPause->onStop->onDestroy
 * 由A 启动一个普通activity B: A.onPause->B.onCreate->B.onStart->B.onResume->A.onStop
 * 由A 启动一个透明activity B: A.onPause->B.onCreate->B.onStart->B.onResume
 * 横竖屏切换(未配置android:configChanges)：onSavedInstanceState->onDestroy->onCreate->onRestoreInstanceState
 * 横竖屏切换(配置android:configChanges)：onConfigurationChanged
 * 按Home 然后重新进入: onPause->onStop->onRestart->onStart->onResume
 *
 * standard:
 * 每次都创建一个新的activity.
 * singleTop(始终保持栈顶只有一个activity):
 * 如果不在栈顶: 创建新的activity 压入栈顶.
 * 如果在栈顶: 复用现在的activity, onPause->onNewIntent->onResume
 * singleTask:
 * ①要定义android:taskAffinity 属性(非包名), 才会创建新栈并放入activity. 如果没定义taskAffinity(或和包名相同),
 * 表示复用现在的栈, 不会创建新栈.
 * ②要新创建一个activity时, 先看指定栈是否存在, 如不存在则先创建指定栈再创建新activity.
 * ③如果再次启动activity, 如果此activity 位于栈顶, 则和singleTop 一样复用; 如果没有位于栈顶, 则清空相同栈内此activity
 * 之上的其余activity, 达到将目标activity 置顶的效果. (比如站内目前有ABCDEF 这6个activities, C 是singleTask,
 * 此时再次启动C 之后, 栈内为ABC.)
 * singleInstance:
 * 单例模式, 如果之前没创建过, 必然会创建一个新栈, 切栈内只会有这一个activity; 如果之前创建过, 复用那个activity.
 * onPause->onNewIntent->onResume
 *
 */

public class LifeCycleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_life_cycle);
        Log.d("flaggg", "LifeCycleActivity.onCreate stack: " + getTaskId());
        getSupportActionBar().setTitle("LifeCycleActivity taskId: " + getTaskId());
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
        ((Button) findViewById(R.id.life_cycle_button3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeCycleActivity.this, LifeCycleActivity.class));
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d("flaggg", "LifeCycleActivity.onNewIntent");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.d("flaggg", "LifeCycleActivity.onConfigurationChanged");
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
