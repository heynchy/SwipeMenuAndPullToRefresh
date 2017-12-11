package com.chy.swipemenuandpulltorefresh.ExpandViewActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.chy.srlibrary.PTRLayoutView;
import com.chy.srlibrary.SwipeMenu;
import com.chy.srlibrary.SwipeMenuItem;
import com.chy.srlibrary.expandview.SMExpandableView;
import com.chy.srlibrary.expandview.SMRExpandView;
import com.chy.srlibrary.interfaceutil.SwipeMenuCreatorInterfaceUtil;
import com.chy.swipemenuandpulltorefresh.Entity.StringEntity;
import com.chy.swipemenuandpulltorefresh.R;
import com.chy.swipemenuandpulltorefresh.adapter.StringDataExpandAdapter;

import java.util.ArrayList;
import java.util.List;
/**
 * ExpandListView 父条目侧滑删除和上拉刷新以及下拉加载 （可设置特定项不予滑动删除）
 */
public class SMRExpandListViewActivity2 extends AppCompatActivity {

    private PTRLayoutView mPTRLayoutView; // 刷新控制器
    private SMRExpandView mSMRExpandView; // 侧滑ExpandablelistView
    private StringDataExpandAdapter mAdapter;
    private List<StringEntity> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smre);
        initView();
        mAdapter = new StringDataExpandAdapter(this, mDataList);
        mSMRExpandView.setAdapter(mAdapter);

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
        mSMRExpandView.setMenuCreator(creator);

        // 侧滑的监听事件
        mSMRExpandView.setOnMenuItemClickListener(new SMExpandableView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int i, SwipeMenu swipeMenu, int i1) {
                mDataList.remove(i);
                mAdapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "删除成功！", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // 上拉加载和下拉刷新的监听事件
        mPTRLayoutView.setOnRefreshListener(new PTRLayoutView.OnRefreshListener() {
            @Override
            public void onRefresh(PTRLayoutView ptrLayoutView) {
                // 处理下拉刷新
                mAdapter.notifyDataSetChanged();
                mPTRLayoutView.refreshFinish(PTRLayoutView.SUCCEED); // 此句话必须有（以结束加载）
                Toast.makeText(getApplicationContext(), "刷新成功！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoadMore(PTRLayoutView ptrLayoutView) {
                // 处理上拉加载
                mAdapter.notifyDataSetChanged();
                mPTRLayoutView.loadmoreFinish(PTRLayoutView.SUCCEED); // 此句话必须有（以结束加载）
                Toast.makeText(getApplicationContext(), "加载成功！", Toast.LENGTH_SHORT).show();
            }
        });

        // 设置条件可以另特定的项不滑动
        mSMRExpandView.setOnTouchListener(new View.OnTouchListener() {
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
                        int oldPos = mSMRExpandView.pointToPosition((int) event.getX(), (int) event.getY());
                        if (oldPos < 5) {
                            // 根据具体条件设置不滑动项（例如： position小于5时不滑动）
                            Toast.makeText(getBaseContext(), "前5项不滑动！！", Toast.LENGTH_SHORT).show();
                            mSMRExpandView.setSwipeEnable(false);
                        } else {
                            mSMRExpandView.setSwipeEnable(true);
                        }
                }
                return false;
            }
        });
    }


    private void initView() {
        mPTRLayoutView = (PTRLayoutView) findViewById(R.id.refresh_view);
        mSMRExpandView = (SMRExpandView) findViewById(R.id.elv_swipe_menu);

        // 初始化测试数据
        mDataList = new ArrayList<>();
        List<String> childList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            childList.add("小标题： " + i);
        }
        for (int i = 0; i < 20; i++) {
            mDataList.add(new StringEntity("大标题：" + i, childList));
        }
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
