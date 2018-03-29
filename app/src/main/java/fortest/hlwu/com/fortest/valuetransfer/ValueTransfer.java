package fortest.hlwu.com.fortest.valuetransfer;

import android.util.Log;

/**
 * Created by hlwu on 3/29/18.
 */

public class ValueTransfer {
    public void valueTest() {
        int i = 1;
        setInt(i);
        Log.d("flaggg", "wwwwwwwwwwwwww  i: " + i);

        StringBuffer sb = new StringBuffer("string buffer");
        setStringBuffer1(sb);
        Log.d("flaggg", "qqqqqqqqqqqqqq sb: " + sb);

        setStringBuffer2(sb);
        Log.d("flaggg", "eeeeeeeeeeeeee sb: " + sb);

        String s = new String("hello");
        setString(s);
        Log.d("flaggg", "rrrrrrrrrrrrrr s: " + s);
    }
    public void setString(String s) {
        s = "world";
    }
    public void setStringBuffer1(StringBuffer s) {
        s = new StringBuffer("changed buffer");
    }
    public void setStringBuffer2(StringBuffer s) {
        s = s.append(" append more");
    }
    public void setInt(int i) {
        i = 22;
    }
}
