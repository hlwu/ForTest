package fortest.hlwu.com.fortest.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by hlwu on 4/9/18.
 */

public class MyLinearLayout extends LinearLayout {
    public MyLinearLayout(Context context) {
        super(context);
    }

    public MyLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.d("flaggg", "MyLinearLayout.dispatchTouchEvent                            action: " + ev.getAction());
        boolean flag = super.dispatchTouchEvent(ev);
        Log.d("flaggg", "MyLinearLayout.dispatchTouchEvent  return: " + flag);
        return flag;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.d("flaggg", "MyLinearLayout.onInterceptTouchEvent");
        boolean flag = super.onInterceptTouchEvent(ev)/* || true*/;
        Log.d("flaggg", "MyLinearLayout.onInterceptTouchEvent, return: " + flag);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("flaggg", "MyLinearLayout.onTouchEvent");
        boolean flag = super.onTouchEvent(event);
        Log.d("flaggg", "MyLinearLayout.onTouchEvent, return: " + flag);
        return flag;
    }
}
