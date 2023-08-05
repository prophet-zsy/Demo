package com.example.a024_expandablelistview;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * ExpandableListView  继承了ListView， 列表中的每一项 均可以点击展开， 对应的 BaseExpandableListAdapter 需要维护二维的数据
 */

public class MainActivity extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private BaseExpandableListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expandableListView = findViewById(R.id.expandableListView);
        adapter = new BaseExpandableListAdapter() {
//            数据
            int[] logos = new int[] {
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher,
                    R.mipmap.ic_launcher,
            };
            String[] armTypes = new String[] {
                    "神族兵种",
                    "虫族兵种",
                    "人族兵种",
            };
            String[][] arms = new String[][] {
                    new String[] {"狂战士", "龙骑士", "黑暗圣堂", "电兵"},
                    new String[] {"小狗", "刺蛇", "飞龙", "自爆飞机"},
                    new String[] {"机枪兵", "护士MM", "幽灵"},
            };

            @Override
            public int getGroupCount() {
                return armTypes.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return arms[i].length;
            }

            @Override
            public Object getGroup(int i) {
                return arms[i];
            }

            @Override
            public Object getChild(int i, int i1) {
                return arms[i][i1];
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
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
                if (view == null) {
//                    todo 可见直接用代码布局，没有xml写起来整洁方便
                    LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                    linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    linearLayout.setLayoutParams(layoutParams);
                    ViewHolder viewHolder = new ViewHolder();
                    viewHolder.imageView = new ImageView(MainActivity.this);
                    viewHolder.textView = new TextView(MainActivity.this);
                    linearLayout.addView(viewHolder.imageView);
                    linearLayout.addView(viewHolder.textView);
                    view = linearLayout;
                    view.setTag(viewHolder);
                }
                ViewHolder holder = (ViewHolder) view.getTag();
                holder.imageView.setImageResource(logos[i]);
                holder.textView.setText(armTypes[i]);
                return view;
            }

            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = new TextView(MainActivity.this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    view.setLayoutParams(layoutParams);
                    view.setPadding(36, 10, 0, 10);
                }
                ((TextView) view).setText(arms[i][i1]);
                return view;
            }

            @Override
            public boolean isChildSelectable(int i, int i1) {
                return true;
            }
        };
        expandableListView.setAdapter(adapter);
//        设置只展开一项
//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int i) {
//                for (int j = 0; j < adapter.getGroupCount(); j++) {
//                    if (j != i) {
//                        expandableListView.collapseGroup(j);
//                    }
//                }
//            }
//        });
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView;
    }
}
