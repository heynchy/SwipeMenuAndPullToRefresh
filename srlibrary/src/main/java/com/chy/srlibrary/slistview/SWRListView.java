package com.chy.srlibrary.slistview;

import android.content.Context;
import android.util.AttributeSet;

import com.chy.srlibrary.interfaceutil.PullableUtil;


/**
 * PullToRefreshAndSwipListView
 *
 * @author CHY  2016/11/14.
 */
public class SWRListView extends SMListView implements PullableUtil {
	public SWRListView(Context context) {
		super(context);
	}

	public SWRListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public SWRListView(Context context, AttributeSet attrs) {
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
	//	return false;
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
