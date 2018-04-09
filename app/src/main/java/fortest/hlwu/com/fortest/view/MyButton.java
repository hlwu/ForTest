package fortest.hlwu.com.fortest.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by hlwu on 4/9/18.
 */

@SuppressLint("AppCompatCustomView")
public class MyButton extends Button {
    public MyButton(Context context) {
        super(context);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d("flaggg", "MyButton.dispatchTouchEvent");
        boolean flag = super.dispatchTouchEvent(event);
        Log.d("flaggg", "MyButton.dispatchTouchEvent, return: " + flag);
        return flag;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d("flaggg", "MyButton.onTouchEvent");
        boolean flag = super.onTouchEvent(event);
        Log.d("flaggg", "MyButton.onTouchEvent, return: " + flag);
        return flag;
    }
}
