package com.chy.swipemenuandpulltorefresh.ListViewAcitvity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.chy.srlibrary.SwipeMenu;
import com.chy.srlibrary.SwipeMenuItem;
import com.chy.srlibrary.interfaceutil.SwipeMenuCreatorInterfaceUtil;
import com.chy.srlibrary.slistview.SMListView;
import com.chy.srlibrary.slistview.SMRListView;
import com.chy.swipemenuandpulltorefresh.R;
import com.chy.swipemenuandpulltorefresh.adapter.StringDataAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * listView 的侧滑删除(可设置条件令某一项不滑动)
 */
public class SMListViewActivity2 extends AppCompatActivity {

    private SMRListView mSWRListView;     // 侧滑listView
    private StringDataAdapter mAdapter;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm);
        initView();
        mDataList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            mDataList.add("测试数据：" + i);
        }
        mAdapter = new StringDataAdapter(this, mDataList);
        mSWRListView.setAdapter(mAdapter);

        // 设置侧滑的选项
        SwipeMenuCreatorInterfaceUtil creator = new SwipeMenuCreatorInterfaceUtil() {
            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                deleteItem.setWidth(dp2px(60));
                deleteItem.setTitle("删除");
                deleteItem.setTitleColor(Color.rgb(255, 255, 255));
                deleteItem.setTitleSize(15);
                menu.addMenuItem(deleteItem);
            }
        };
        mSWRListView.setMenuCreator(creator);

        // 侧滑的监听事件
        mSWRListView.setOnMenuItemClickListener(new SMListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int i1) {
                mDataList.remove(i);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
        mSWRListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "点击了"+ position + "项！！！", Toast.LENGTH_SHORT).show();
            }
        });

        mSWRListView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        /**
                         * oldPos  滑动的所处位置的position
                         * setSwipeEnable() 是否进行侧滑：
                         *           设置为false则不会发生侧滑；
                         *           设置为true则会侧滑
                         *           默认值为true
                         *
                         * 这里可根据具体的条件来判定是否可以进行滑动
                         */
                        int oldPos = mSWRListView.pointToPosition((int) event.getX(), (int) event.getY());
                        if (oldPos < 5) {
                            // 根据具体条件设置不滑动项（例如： position小于5时不滑动）
                            mSWRListView.setSwipeEnable(false);
                            Toast.makeText(getBaseContext(), "前5项不滑动！！", Toast.LENGTH_SHORT).show();
                        } else {
                            mSWRListView.setSwipeEnable(true);
                        }
                }
                return false;
            }
        });
    }


    private void initView() {
        mSWRListView = (SMRListView) findViewById(R.id.lv_swipe_menu);

    }

    /**
     * dp与px之间的转换
     *
     * @param dp
     * @return
     */
    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
