package com.xyrality.gmbh.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by yarik on 26.09.15.
 */
public class GameWorldsAdapter extends BaseExpandableListAdapter {

    private Context context;

    private List<GameWorld> gameWorldList;

    public GameWorldsAdapter(Context context, List<GameWorld> gameWorldList) {
        this.context = context;
        this.gameWorldList = gameWorldList;
    }

    @Override
    public int getGroupCount() {
        if (gameWorldList != null) {
            return gameWorldList.size();
        }
        return 0;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if (gameWorldList != null && gameWorldList.size() > 0) {
            gameWorldList.get(groupPosition);
        }
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if (gameWorldList != null && gameWorldList.size() > 0) {
            gameWorldList.get(groupPosition);
        }
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupViewHolder holder;
        if (view == null) {
            holder = new GroupViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.worlds_list_group, parent, false);
            holder.titleTextView = (TextView) view.findViewById(R.id.groupTextView);
            view.setTag(holder);
        }
        holder = (GroupViewHolder) view.getTag();
        holder.titleTextView.setText(gameWorldList.get(groupPosition).getName());

        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        View view = convertView;
        GroupViewHolder holder;
        if (view == null) {
            holder = new GroupViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.worlds_list_child, parent, false);
            holder.titleTextView = (TextView) view.findViewById(R.id.childTextView);
            view.setTag(holder);
        }
        holder = (GroupViewHolder) view.getTag();
        GameWorld world = gameWorldList.get(groupPosition);
        holder.titleTextView.setText(new StringBuffer("country = ").append(world.getCountry()).append("\n").
                        append("world id = ").append(world.getId()).append("\n").
                        append("language = ").append(world.getLanguage()).append("\n").
                        append("mapUrl = ").append(world.getMapUrl()).append("\n").
                        append("url = ").append(world.getUrl()).append("\n").
                        append("world status: id = ").append(world.getWorldStatus().getId()).
                        append(", description = ").append(world.getWorldStatus().getDescription()).append("\n").
                        toString());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ExpandableListView) parent).collapseGroup(groupPosition);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private static class GroupViewHolder {
        public TextView titleTextView;
    }
}
