package com.chy.swipemenuandpulltorefresh.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.chy.swipemenuandpulltorefresh.Entity.StringEntity;
import com.chy.swipemenuandpulltorefresh.R;

import java.util.List;

/**
 * StringDataExpandAdapter
 *
 * @author lenovo  2017/5/4.
 *         Function Describe
 * @modify lenovo  2017/5/4.
 * Function Describe
 */
public class StringDataExpandAdapter extends BaseExpandableListAdapter {
    private Context context;
    private List<StringEntity> parentList;

    public StringDataExpandAdapter(Context context, List<StringEntity> parentList) {
        this.context = context;
        this.parentList = parentList;
    }


    @Override
    public int getGroupCount() {
        return parentList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return parentList.get(groupPosition).getChildListStr().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return parentList.get(groupPosition).getChildListStr().get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        StringEntity entity = parentList.get(groupPosition);
        ViewHolderParent viewHolderParent;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            viewHolderParent = new ViewHolderParent();
            viewHolderParent.parentTv = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(viewHolderParent);
        } else {
            viewHolderParent = (ViewHolderParent) convertView.getTag();
        }
        viewHolderParent.parentTv.setText(entity.getTitle());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String str = (String) getChild(groupPosition, childPosition);
        ViewHolderChild holderChild;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
            holderChild = new ViewHolderChild();
            holderChild.childTv = (TextView) convertView.findViewById(R.id.text);
            convertView.setTag(holderChild);
        } else {
            holderChild = (ViewHolderChild) convertView.getTag();
        }
        holderChild.childTv.setText(str);
        holderChild.childTv.setTextSize(13);
        holderChild.childTv.setTextColor(context.getResources().getColor(R.color.colorAccent));
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolderParent {
        private TextView parentTv;
    }

    private class ViewHolderChild {
        private TextView childTv;
    }
}
