package com.example.a024_expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.idunnololz.widgets.AnimatedExpandableListView;

import java.util.ArrayList;
import java.util.List;


/**
 *  因为安卓原生的ExpandableListView只支持二级列表，支持点击扩展等交互功能，但不支持动画，因此有三方库为其添加动画功能
 *  https://github.com/idunnololz/AnimatedExpandableListView
 *
 *  经测试，该三方实现，动画不稳定，各个列表项动画有时不能同步进行
 */

public class SecondActivity extends AppCompatActivity {

    private AnimatedExpandableListView animatedExpandableListView;
//    private AnimatedExpandableListView.AnimatedExpandableListAdapter adapter;
    private ExampleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        List<GroupItem> items = new ArrayList<GroupItem>();

        // Populate our list with groups and it's children
        for(int i = 1; i < 100; i++) {
            GroupItem item = new GroupItem();

            item.title = "Group " + i;

            for(int j = 0; j < i; j++) {
                ChildItem child = new ChildItem();
                child.title = "Awesome item " + j;
                child.hint = "Too awesome";

                item.items.add(child);
            }

            items.add(item);
        }

        adapter = new ExampleAdapter(this);



//        List<String> armTypes = new ArrayList<String>() {{
//            add("神族兵种");add("神族兵种");add("神族兵种");add("神族兵种");add("神族兵种");
//        }};
//        List<List<String>> arms = new ArrayList<List<String>>() {{
//            add(new ArrayList<String>(){{add("狂战士");add("龙骑士");add("黑暗圣堂");add("电兵");}});
//            add(new ArrayList<String>(){{add("狂战士");add("龙骑士");add("黑暗圣堂");add("电兵");}});
//            add(new ArrayList<String>(){{add("狂战士");add("龙骑士");add("黑暗圣堂");add("电兵");}});
//            add(new ArrayList<String>(){{add("狂战士");add("龙骑士");add("黑暗圣堂");add("电兵");}});
//            add(new ArrayList<String>(){{add("狂战士");add("龙骑士");add("黑暗圣堂");add("电兵");}});
//        }};


        adapter.setData(items);

//        adapter.setData(armTypes, arms);





        animatedExpandableListView = findViewById(R.id.expandableListView);


        animatedExpandableListView.setAdapter(adapter);



//        adapter = new AnimatedExpandableListView.AnimatedExpandableListAdapter() {
////            数据
//            int[] logos = new int[] {
//                    R.mipmap.ic_launcher,
//                    R.mipmap.ic_launcher,
//                    R.mipmap.ic_launcher,
//            };
//            String[] armTypes = new String[] {
//                    "神族兵种",
//                    "虫族兵种",
//                    "虫族兵种",
//                    "虫族兵种",
//                    "虫族兵种",
//                    "人族兵种",
//            };
//            String[][] arms = new String[][] {
//                    new String[] {"狂战士", "龙骑士", "黑暗圣堂", "电兵"},
//                    new String[] {"小狗", "刺蛇", "飞龙", "自爆飞机"},
//                    new String[] {"小狗", "刺蛇", "飞龙", "自爆飞机"},
//                    new String[] {"小狗", "刺蛇", "飞龙", "自爆飞机"},
//                    new String[] {"小狗", "刺蛇", "飞龙", "自爆飞机"},
//                    new String[] {"机枪兵", "护士MM", "幽灵"},
//            };
//
//            @Override
//            public int getGroupCount() {
//                return armTypes.length;
//            }
//
//            @Override
//            public Object getGroup(int i) {  // 返回String呢
//                return arms[i];
//            }
//
//            @Override
//            public Object getChild(int i, int i1) {  // 返回String呢
//                return arms[i][i1];
//            }
//
//            @Override
//            public long getGroupId(int i) {
//                return i;
//            }
//
//            @Override
//            public long getChildId(int i, int i1) {
//                return i1;
//            }
//
//            @Override
//            public boolean hasStableIds() {
//                return true;
//            }
//
//            @Override
//            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
//                if (view == null) {
//                    LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(SecondActivity.this).inflate(R.layout.group_item, viewGroup, false);
//                    ViewHolder viewHolder = new ViewHolder();
////                    viewHolder.imageView = linearLayout.findViewById(R.id.image);
//                    viewHolder.textView = linearLayout.findViewById(R.id.text);
//                    view = linearLayout;
//                    view.setTag(viewHolder);
//                }
//                ViewHolder holder = (ViewHolder) view.getTag();
////                holder.imageView.setImageResource(logos[i]);
//                holder.textView.setText(armTypes[i]);
//                return view;
//            }
//
//            @Override
//            public boolean isChildSelectable(int i, int i1) {
//                return true;
//            }
//
//            @Override
//            public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//                if (convertView == null) {
//                    convertView = LayoutInflater.from(SecondActivity.this).inflate(R.layout.sublist_item, parent, false);
//                }
//                ((TextView) convertView).setText(arms[groupPosition][childPosition]);
//                return convertView;
//            }
//
//            @Override
//            public int getRealChildrenCount(int groupPosition) {
//                return arms[groupPosition].length;
//            }
//        };
//        animatedExpandableListView.setAdapter(adapter);
//        设置监听，实现只展开其中一项的逻辑
        animatedExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (animatedExpandableListView.isGroupExpanded(i)) {
                    animatedExpandableListView.collapseGroupWithAnimation(i);
                } else {
                    animatedExpandableListView.expandGroupWithAnimation(i);
                    for (int j = 0; j < adapter.getGroupCount(); j++) {
                        if (animatedExpandableListView.isGroupExpanded(j) && j != i) {
                            animatedExpandableListView.collapseGroupWithAnimation(j);
                        }
                    }
                }
                return true;
            }
        });

    }

    static class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }





    /**
     * Adapter for our list of {@link GroupItem}s.
     */
    private class ExampleAdapter extends AnimatedExpandableListView.AnimatedExpandableListAdapter {
//
//        List<String> armTypes;
//        List<List<String>> arms;

        private LayoutInflater inflater;

        private List<GroupItem> items;

        public ExampleAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void setData(List<GroupItem> items) {
            this.items = items;
        }
//        private void setData(List<String> armTypes, List<List<String>> arms) {
//            this.armTypes = armTypes;
//            this.arms = arms;
//        }

        @Override
        public ChildItem getChild(int groupPosition, int childPosition) {
//            return arms.get(groupPosition).get(childPosition);
            return items.get(groupPosition).items.get(childPosition);
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public View getRealChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.sublist_item, parent, false);
            }
//            ((TextView)convertView).setText((String)getChild(groupPosition, childPosition));
            ((TextView)convertView).setText(((ChildItem)getChild(groupPosition, childPosition)).title);
            return convertView;
        }

        @Override
        public int getRealChildrenCount(int groupPosition) {
//            return arms.get(groupPosition).size();
            return items.get(groupPosition).items.size();
        }

        @Override
        public GroupItem getGroup(int groupPosition) {
//            return armTypes.get(groupPosition);
            return items.get(groupPosition);
        }

        @Override
        public int getGroupCount() {
//            return armTypes.size();
            return items.size();
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();
                convertView = inflater.inflate(R.layout.group_item, parent, false);
                holder.textView = convertView.findViewById(R.id.text);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

//            holder.textView.setText((String)getGroup(groupPosition));
            holder.textView.setText(((GroupItem)getGroup(groupPosition)).title);

            return convertView;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        public boolean isChildSelectable(int arg0, int arg1) {
            return true;
        }

    }




    private static class GroupItem {
        String title;
        List<ChildItem> items = new ArrayList<ChildItem>();
    }

    private static class ChildItem {
        String title;
        String hint;
    }
}