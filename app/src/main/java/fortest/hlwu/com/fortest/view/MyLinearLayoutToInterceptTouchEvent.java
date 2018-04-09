package fortest.hlwu.com.fortest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class MyLinearLayoutToInterceptTouchEvent extends HorizontalScrollView {

    private float mLastX, mLastY;

    public MyLinearLayoutToInterceptTouchEvent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("flaggg", "MyLinearLayoutToInterceptTouchEvent.onTouchEvent");
        boolean flag = super.onTouchEvent(event);
        Log.d("flaggg", "MyLinearLayoutToInterceptTouchEvent.onTouchEvent, return: " + flag);
        return flag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("flaggg", "MyLinearLayoutToInterceptTouchEvent.onInterceptTouchEvent");
        boolean intercept = false;
        float x = ev.getRawX();
        float y = ev.getRawY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d("flaggg", "deltaX: " + Math.abs(x - mLastX) + "; deltaY: " + Math.abs(y - mLastY));
                if (Math.abs(x - mLastX) > Math.abs(y - mLastY)) {
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        mLastX = x;
        mLastY = y;
        boolean flag = super.onInterceptTouchEvent(ev) && intercept;
        Log.d("flaggg", "MyLinearLayoutToInterceptTouchEvent.onInterceptTouchEvent, return: " + flag);
        return flag;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("flaggg", "MyLinearLayoutToInterceptTouchEvent.dispatchTouchEvent");
        boolean flag = super.dispatchTouchEvent(ev);
        Log.d("flaggg", "MyLinearLayoutToInterceptTouchEvent.dispatchTouchEvent, return: " + flag);
        return flag;
    }
}
