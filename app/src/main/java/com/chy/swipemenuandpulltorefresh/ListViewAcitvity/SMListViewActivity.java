package com.chy.swipemenuandpulltorefresh.ListViewAcitvity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chy.srlibrary.PTRLayoutView;
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
 * listView 的侧滑删除
 */
public class SMListViewActivity extends AppCompatActivity {

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
        });;
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
