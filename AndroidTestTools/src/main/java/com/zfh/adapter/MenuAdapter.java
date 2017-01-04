package com.zfh.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zfh.testtools.R;

/**
 * Created by zfh on 16-10-23.
 * 侧滑栏菜单
 */

public class MenuAdapter extends BaseExpandableListAdapter {
    private Context context;
    private LayoutInflater inflater;
    private String[] titleline;//组视图显示文字
    private String[][] groupline;//子视图显示文字

    public MenuAdapter(Context context) {
        //初始化context,得到MainActivity的Context对象
        this.context = context;
        inflater = LayoutInflater.from(context);
        setString();
    }

    public void setString() {
        //组视图显示文字
        titleline = new String[]{context.getString(R.string.home_page),context.getString(R.string.system_stress_test), context.getString(R.string.battery_info), context.getString(R.string.system_shutdown),
        context.getString(R.string.app_manager),context.getString(R.string.file_manager),context.getString(R.string.system_info), context.getString(R.string.about)
        };
        //子视图显示文字
        groupline = new String[][]{{},{context.getString(R.string.bluetooth), context.getString(R.string.wifi), context.getString(R.string.outline_mode)}, {},{},{},{},{},{}
        };
    }

    @Override
    //返回组列表项的数量
    public int getGroupCount() {
        return titleline.length;
    }

    @Override
    //返回特定组所包含的子列表项的数目
    public int getChildrenCount(int groupPosition) {
        return groupline[groupPosition].length;
    }

    @Override
    //返回组列表名称
    public Object getGroup(int groupPosition) {
        return titleline[groupPosition];
    }

    @Override
    //返回组列表相应子元素
    public Object getChild(int groupPosition, int childPosition) {
        return groupline[groupPosition][childPosition];
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
        return true;
    }

    @Override
    //返回View对象作为组列表项
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        Holder holder = null;
        if (null == convertView) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.left_group_list, null, false);
            holder.groupImage = (ImageView) convertView.findViewById(R.id.groupimage);
            holder.groupName = (TextView) convertView.findViewById(R.id.groupname);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
//        Log.e("group-getGroupView","groupPosition->"+groupPosition+" childrenCount->"+getChildrenCount(groupPosition));
        holder.groupName.setText(getGroup(groupPosition).toString());
        if (getChildrenCount(groupPosition) != 0) {
            if (isExpanded) {
                //展开列表
                holder.groupImage.setImageDrawable(context.getResources().getDrawable(R.drawable.acl_blue));
            } else {
                holder.groupImage.setImageDrawable(context.getResources().getDrawable(R.drawable.acl_green));
            }
        }

        return convertView;
    }

    @Override
    //返回View对象作为子列表布局
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//        Log.e("child-getGroupView","groupPosition->"+groupPosition+" childrenCount->"+getChildrenCount(groupPosition));
        Holder holder = null;
        if (null == convertView) {
            holder = new Holder();
            convertView = inflater.inflate(R.layout.left_child_list, null, false);
            holder.childName = (TextView) convertView.findViewById(R.id.childname);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.childName.setText(getChild(groupPosition,childPosition).toString());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class Holder {
        TextView groupName,childName;
        ImageView groupImage;
    }
}

