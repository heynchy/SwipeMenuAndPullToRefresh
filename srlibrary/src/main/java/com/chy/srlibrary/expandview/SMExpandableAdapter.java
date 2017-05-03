package com.chy.srlibrary.expandview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;

import com.chy.srlibrary.SwipeMenu;
import com.chy.srlibrary.SwipeMenuItem;


/**
 * SMExpandableAdapter
 *
 * @author CHY  2016/7/29.
 *
 * ExpandableListView侧滑布局的适配器
 */
public class SMExpandableAdapter extends BaseExpandableListAdapter implements SMExpandView.OnSwipeItemClickListener {

	private ExpandableListAdapter mAdapter;
	private Context mContext;
	private SMExpandableView.OnMenuItemClickListener onMenuItemClickListener;

	public SMExpandableAdapter(Context context, ExpandableListAdapter adapter) {
		mAdapter = adapter;
		mContext = context;
	}

	public void createMenu(SwipeMenu menu) {
		// Test Code
		SwipeMenuItem item = new SwipeMenuItem(mContext);
		item.setTitle("Item 1");
		item.setBackground(new ColorDrawable(Color.GRAY));
		item.setWidth(300);
		menu.addMenuItem(item);

		item = new SwipeMenuItem(mContext);
		item.setTitle("Item 2");
		item.setBackground(new ColorDrawable(Color.RED));
		item.setWidth(300);
		menu.addMenuItem(item);
	}

	@Override
	public View getGroupView(int position, boolean b, View convertView, ViewGroup parent) {
		SMLayoutView layout = null;
		if (convertView == null) {
			View contentView = mAdapter.getGroupView(position, b, convertView, parent);
			SwipeMenu menu = new SwipeMenu(mContext);
			menu.setViewType(1);
			createMenu(menu);
			SMExpandView menuView = new SMExpandView(menu,
					(SMExpandableView) parent);
			Log.i("heyn", "ssssssss  onItemClsssssick");
			menuView.setOnSwipeItemClickListener(this);
			SMExpandableView listView = (SMExpandableView) parent;
			layout = new SMLayoutView(contentView, menuView,
					listView.getCloseInterpolator(),
					listView.getOpenInterpolator());
			layout.setPosition(position);
		} else {
			layout = (SMLayoutView) convertView;
			layout.closeMenu();
			layout.setPosition(position);
			View view = mAdapter.getGroupView(position, b, layout.getContentView(),
					parent);
		}

		return layout;
	}

	@Override
	public View getChildView(int position, int i1, boolean b, View convertView, ViewGroup parent) {
		convertView = mAdapter.getChildView(position, i1, b, convertView, parent);
		return convertView;
//		 为child添加滑动删除
//		SwipeMenuLayoutView layout = null;
//		if (convertView == null) {
//			View contentView = mAdapter.getChildView(position,i1, b, convertView, parent);
//			SwipeMenu menu = new SwipeMenu(mContext);
//			menu.setViewType(1);
//			createMenu(menu);
//			SwipeMenuView menuView = new SwipeMenuView(menu,
//					(SwipeMenuExpandableListView) parent);
//			menuView.setOnSwipeItemClickListener(this);
//			SwipeMenuExpandableListView listView = (SwipeMenuExpandableListView) parent;
//			layout = new SwipeMenuLayoutView(contentView, menuView,
//					listView.getCloseInterpolator(),
//					listView.getOpenInterpolator());
//			layout.setPosition(i1);
//		} else {
//			layout = (SwipeMenuLayoutView) convertView;
//			layout.closeMenu();
//			layout.setPosition(i1);
//			View view = mAdapter.getChildView(position,i1, b, layout.getContentView(),
//					parent);
//		}
//		return layout;
	}


	public void setOnSwipeItemClickListener(
			SMExpandableView.OnMenuItemClickListener onMenuItemClickListener) {
		this.onMenuItemClickListener = onMenuItemClickListener;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		mAdapter.registerDataSetObserver(observer);
	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		mAdapter.unregisterDataSetObserver(observer);
	}

	@Override
	public int getGroupCount() {
		return mAdapter.getGroupCount();
	}

	@Override
	public int getChildrenCount(int i) {
		return mAdapter.getChildrenCount(i);
	}

	@Override
	public Object getGroup(int i) {
		return mAdapter.getGroup(i);
	}

	@Override
	public Object getChild(int i, int i1) {
		return mAdapter.getChild(i, i1);
	}

	@Override
	public long getGroupId(int i) {
		return i;
	}

	@Override
	public long getChildId(int i, int i1) {
		return i1;
	}

	@Override
	public boolean areAllItemsEnabled() {
		return mAdapter.areAllItemsEnabled();
	}


	@Override
	public boolean hasStableIds() {
		return mAdapter.hasStableIds();
	}

	@Override
	public boolean isChildSelectable(int i, int i1) {
		return true;
	}

	@Override
	public boolean isEmpty() {
		return mAdapter.isEmpty();
	}

	@Override
	public void onItemClick(SMExpandView view, SwipeMenu menu, int index) {
		if (onMenuItemClickListener != null) {
			onMenuItemClickListener.onMenuItemClick(view.getPosition(), menu, index);
		}
	}

}
