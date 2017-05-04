package com.chy.swipemenuandpulltorefresh;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chy.swipemenuandpulltorefresh.ExpandViewActivity.RExpandListViewActivity;
import com.chy.swipemenuandpulltorefresh.ExpandViewActivity.SMExpandListViewActivity;
import com.chy.swipemenuandpulltorefresh.ExpandViewActivity.SMRExpandListViewActivity;
import com.chy.swipemenuandpulltorefresh.ListViewAcitvity.RListViewActivity;
import com.chy.swipemenuandpulltorefresh.ListViewAcitvity.SMListViewActivity;
import com.chy.swipemenuandpulltorefresh.ListViewAcitvity.SMRListViewActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lsr_tv:
                //  listView的侧滑删除，上拉加载和下拉刷新
                Intent intent0 = new Intent(MainActivity.this, SMRListViewActivity.class);
                startActivity(intent0);
                break;
            case R.id.ls_tv:
                //  listView的侧滑删除
                Intent intent1 = new Intent(MainActivity.this, SMListViewActivity.class);
                startActivity(intent1);
                break;
            case R.id.lr_tv:
                //  listView的上拉加载和下拉刷新
                Intent intent2 = new Intent(MainActivity.this, RListViewActivity.class);
                startActivity(intent2);
                break;
            case R.id.esr_tv:
                //  expandablelistView的侧滑删除，上拉加载和下拉刷新
                Intent intent3 = new Intent(MainActivity.this, SMRExpandListViewActivity.class);
                startActivity(intent3);
                break;
            case R.id.es_tv:
                //  expandablelistView的侧滑删除
                Intent intent4 = new Intent(MainActivity.this, SMExpandListViewActivity.class);
                startActivity(intent4);
                break;
            case R.id.er_tv:
                //  expandablelistView的上拉加载和下拉刷新
                Intent intent5 = new Intent(MainActivity.this, RExpandListViewActivity.class);
                startActivity(intent5);
                break;
        }
    }


    private void initView() {
        mTv1 = (TextView) findViewById(R.id.lsr_tv);
        mTv2 = (TextView) findViewById(R.id.ls_tv);
        mTv3 = (TextView) findViewById(R.id.lr_tv);
        mTv4 = (TextView) findViewById(R.id.esr_tv);
        mTv5 = (TextView) findViewById(R.id.er_tv);
        mTv6 = (TextView) findViewById(R.id.es_tv);
        mTv1.setOnClickListener(this);
        mTv2.setOnClickListener(this);
        mTv3.setOnClickListener(this);
        mTv4.setOnClickListener(this);
        mTv5.setOnClickListener(this);
        mTv6.setOnClickListener(this);
    }
}
