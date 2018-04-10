package fortest.hlwu.com.fortest.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fortest.hlwu.com.fortest.R;

public class ViewTestActivityToInterceptTouchEvent extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_view_to_intercept_touch_event);
        initView();
    }

    private void initView() {
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            datas.add("this is " + i + " item");
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item_for_list_view, R.id.list_view_item, datas);
        ((ListView) findViewById(R.id.view_test_list_1)).setAdapter(adapter);
        ((ListView) findViewById(R.id.view_test_list_2)).setAdapter(adapter);
        ((ListView) findViewById(R.id.view_test_list_3)).setAdapter(adapter);

        resetListViewHeight(adapter, (ListView) findViewById(R.id.view_test_list_1));
        resetListViewHeight(adapter, (ListView) findViewById(R.id.view_test_list_2));
        resetListViewHeight(adapter, (ListView) findViewById(R.id.view_test_list_3));

//        ((ListView) findViewById(R.id.view_test_list_1)).setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                Log.d("flaggg", "list 1 onTouch ev: " + event.getAction());
//                return false;
//            }
//        });
    }

    private void resetListViewHeight(ArrayAdapter<String> adapter, ListView listView) {
//        int totalHeight = 0;                                    // 定义、初始化listview总高度值
//        for (int i = 0; i < adapter.getCount(); i++) {
//            View listItem = adapter.getView(i, null, listView);          // 获取单个item
//            listItem.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));// 设置item高度为适应内容
//            listItem.measure(0, 0);                                        // 测量现在item的高度
//            totalHeight += listItem.getMeasuredHeight();                   // 总高度增加一个listitem的高度
//        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1)); // 将分割线高度加上总高度作为最后listview的高度
        params.height = getWindowManager().getDefaultDisplay().getHeight();
        listView.setLayoutParams(params);
    }
}
