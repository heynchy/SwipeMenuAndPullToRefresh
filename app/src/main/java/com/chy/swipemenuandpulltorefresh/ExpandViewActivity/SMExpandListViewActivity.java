package com.chy.swipemenuandpulltorefresh.ExpandViewActivity;

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
import com.chy.srlibrary.expandview.SMExpandableView;
import com.chy.srlibrary.expandview.SMRExpandView;
import com.chy.srlibrary.interfaceutil.SwipeMenuCreatorInterfaceUtil;
import com.chy.swipemenuandpulltorefresh.Entity.StringEntity;
import com.chy.swipemenuandpulltorefresh.R;
import com.chy.swipemenuandpulltorefresh.adapter.StringDataExpandAdapter;

import java.util.ArrayList;
import java.util.List;

public class SMExpandListViewActivity extends AppCompatActivity  {

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

    }


    private void initView() {
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
