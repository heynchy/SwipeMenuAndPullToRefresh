package com.chy.srlibrary.expandview;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.chy.srlibrary.SwipeMenu;
import com.chy.srlibrary.SwipeMenuItem;

import java.util.List;


/**
 * SwipeMenuLayoutView
 *
 * @author CHY  2016/7/29.
 *         处理侧滑菜单的选项
 */
public class SMExpandView extends LinearLayout implements OnClickListener {

	private SMExpandableView mListView;
	private SMLayoutView mLayout;
	private SwipeMenu mMenu;
	private OnSwipeItemClickListener onItemClickListener;
	private int mPosition;

	public int getPosition() {
		return mPosition;
	}

	public void setPosition(int mPosition) {
		this.mPosition = mPosition;
	}

	public SMExpandView(SwipeMenu menu, SMExpandableView listView) {
		super(menu.getContext());
		mListView = listView;
		mMenu = menu;
		List<SwipeMenuItem> items = menu.getMenuItems();
		int id = 0;
		for (SwipeMenuItem item : items) {
			addItem(item, id++);
		}
	}

	private void addItem(SwipeMenuItem item, int id) {
		LayoutParams params = new LayoutParams(item.getWidth(),
				LayoutParams.MATCH_PARENT);
		LinearLayout parent = new LinearLayout(getContext());
		parent.setId(id);
		parent.setGravity(Gravity.CENTER);
		parent.setOrientation(LinearLayout.VERTICAL);
		parent.setLayoutParams(params);
		parent.setBackgroundDrawable(item.getBackground());
		parent.setOnClickListener(this);

		addView(parent);

		if (item.getIcon() != null) {
			parent.addView(createIcon(item));
		}
		if (!TextUtils.isEmpty(item.getTitle())) {
			parent.addView(createTitle(item));
		}

	}

	private ImageView createIcon(SwipeMenuItem item) {
		ImageView iv = new ImageView(getContext());
		iv.setImageDrawable(item.getIcon());
		return iv;
	}

	private TextView createTitle(SwipeMenuItem item) {
		TextView tv = new TextView(getContext());
		tv.setText(item.getTitle());
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(item.getTitleSize());
		tv.setTextColor(item.getTitleColor());
		return tv;
	}

	@Override
	public void onClick(View v) {
		if (onItemClickListener != null && mLayout.isOpen()) {
			onItemClickListener.onItemClick(this, mMenu, v.getId());
		}
	}

	public OnSwipeItemClickListener getOnSwipeItemClickListener() {
		return onItemClickListener;
	}

	public void setOnSwipeItemClickListener(OnSwipeItemClickListener onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	public void setLayout(SMLayoutView mLayout) {
		this.mLayout = mLayout;
	}


	public static interface OnSwipeItemClickListener {
		void onItemClick(SMExpandView view, SwipeMenu menu, int index);
	}
}
