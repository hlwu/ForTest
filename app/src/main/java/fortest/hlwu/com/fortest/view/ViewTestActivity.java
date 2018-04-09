package fortest.hlwu.com.fortest.view;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Scroller;
import android.widget.TextView;

import fortest.hlwu.com.fortest.R;

/**
 * Created by hlwu on 4/8/18.
 */

public class ViewTestActivity extends Activity {
    private TextView tv;
    private Button b3, b4, b5, b6, b7;
    private float b5LastX, b5LastY;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_view);
        tv = (TextView) findViewById(R.id.view_test_tv);
        ((Button) findViewById(R.id.view_test_move_btn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.scrollBy(20, 0);
            }
        });
        ((Button) findViewById(R.id.view_test_move_btn_2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.scrollBy(-20, 0);
            }
        });
        b3 = (Button) findViewById(R.id.view_test_move_btn_3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator.ofFloat(b3, "translationX", 0, 100).setDuration(100).start();
            }
        });
        b4 = (Button) findViewById(R.id.view_test_move_btn_4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) b4.getLayoutParams();
                params.width += 100;
                params.leftMargin += 100;
                b4.requestLayout();
            }
        });
        b5 = (Button) findViewById(R.id.view_test_follow_finger_btn_5);
        b5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        float x, y;
                        x = event.getRawX();
                        y = event.getRawY();
                        Log.d("flaggg", "onTouchEvent x: " + x + "; y: " + y);
                        float deltaX = x - b5LastX;
                        float deltaY = y - b5LastY;
                        //TO BE CONTINUE...
                        break;
                    default:
                        break;
                }
                b5LastX = event.getRawX();
                b5LastY = event.getRawY();
                return false;
            }
        });
        b6 = (Button) findViewById(R.id.view_test_valueanimation_move_btn_6);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ValueAnimator animator = ValueAnimator.ofInt(0, 1).setDuration(1000);
                animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float fraction = animation.getAnimatedFraction();
//                        b6.scrollTo(0 + (int) (100 * fraction), 0);
                        ObjectAnimator.ofFloat(b6, "translationX", 0, (int) (100 * fraction)).setDuration(0).start();
                    }
                });
                animator.start();
            }
        });
        ((MyLinearLayout) findViewById(R.id.view_test_ll)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("flaggg", "trigger MyLinearLayout.onClick");
            }
        });
        ((MyLinearLayout) findViewById(R.id.view_test_ll)).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.d("flaggg", "trigger MyLinearLayout.onTouch return true");
                return true;
            }
        });
        ((MyButton) findViewById(R.id.view_test_ll_mybtn_7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("flaggg", "trigger MyButton.onClick");
            }
        });
//        ((MyButton2) findViewById(R.id.view_test_ll_mybtn_8)).setEnabled(false);  //非Enable 可以接收到事件.
//        ((MyButton2) findViewById(R.id.view_test_ll_mybtn_8)).setVisibility(View.INVISIBLE);  //非VISIBLE 无法接收到事件.
//        ((MyButton2) findViewById(R.id.view_test_ll_mybtn_8)).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {   //设置OnTouchListener 后, 如果OnTouch() 中返回true, 那么view 的onTouchEvent()不会被触发
//                Log.d("flaggg", "trigger MyButton2.onTouch return true");
//                return true;
//            }
//        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("flaggg", "ViewTestActivity.dispatchTouchEvent");
        boolean flag = super.dispatchTouchEvent(ev);
        Log.d("flaggg", "ViewTestActivity.dispatchTouchEvent, return: " + flag);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("flaggg", "ViewTestActivity.onTouchEvent");
        boolean flag = super.onTouchEvent(event);
        Log.d("flaggg", "ViewTestActivity.onTouchEvent, return: " + flag);
        return flag;
    }
}
