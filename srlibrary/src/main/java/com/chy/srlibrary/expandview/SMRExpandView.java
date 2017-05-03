package com.chy.srlibrary.expandview;

import android.content.Context;
import android.util.AttributeSet;

import com.chy.srlibrary.interfaceutil.PullableUtil;


/**
 * PullToRefreshAndSwipeMenuListView
 *
 * @author   2016/7/27.
 *         融合下拉刷新，上拉加载和侧滑
 */
public class SMRExpandView extends SMExpandableView implements PullableUtil {

	public SMRExpandView(Context context) {
		super(context);
	}

	public SMRExpandView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SMRExpandView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean canPullDown() {
		if (getCount() == 0) {
			// 没有item的时候也可以下拉刷新
			return true;
		} else if (getFirstVisiblePosition() == 0
				&& (null != getChildAt(0)) && (getChildAt(0).getTop() >= 0)) {
			// 滑到ListView的顶部了
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean canPullUp() {
		if (getLastVisiblePosition() == (getCount() - 1)) {
			// 滑到底部了
			if ((null != getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()))
					&& (getChildAt(getLastVisiblePosition() - getFirstVisiblePosition()).getBottom()
					== getMeasuredHeight()))
				return true;
		}
		return false;
	}
}
