package com.example.a045_menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;
import android.widget.Toast;

/**
 *  菜单在桌面应用中的使用比较多，在手机上因为屏幕大小的限制，菜单的使用减少了很多
 *  安卓的菜单一般隐藏在actionBar中的右上角
 *  这种菜单一般叫 选项菜单， 二级、三级菜单 叫做子菜单
 *  使用选项菜单，一般重写onCreateOptionsMenu方法和onOptionsItemSelected方法
 */

public class MainActivity extends AppCompatActivity {
//   "字体大小"菜单项的标识
    private static final int FONT_10 = 0x111;
    private static final int FONT_12 = 0x112;
    private static final int FONT_14 = 0x113;
    private static final int FONT_16 = 0x114;
    private static final int FONT_18 = 0x115;
//    "普通菜单项"的标识
    private static final int PLAIN_ITEM = 0x11b;
//    "字体颜色"菜单项的标识
    private static final int FONT_RED = 0x116;
    private static final int FONT_BLUE = 0x117;
    private static final int FONT_GREEN = 0x118;
//    上述定义的标识，在菜单项被选择的时候 匹配 用

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        SubMenu fontMenu = menu.addSubMenu("字体大小");
        fontMenu.setIcon(R.drawable.ic_font_download_black_24dp);
        fontMenu.setHeaderIcon(R.drawable.ic_font_download_black_24dp);
        fontMenu.add(0, FONT_10, 0, "10号字体");
        fontMenu.add(0, FONT_12, 0, "12号字体");
        fontMenu.add(0, FONT_14, 0, "14号字体");
        fontMenu.add(0, FONT_16, 0, "16号字体");
        fontMenu.add(0, FONT_18, 0, "18号字体");
        menu.add(0, PLAIN_ITEM, 0, "普通菜单项");
        SubMenu colorMenu = menu.addSubMenu("字体颜色");
        colorMenu.setIcon(R.drawable.ic_color_lens_black_24dp);
        colorMenu.setHeaderIcon(R.drawable.ic_color_lens_black_24dp);
        colorMenu.add(0, FONT_RED, 0, "红色");
        colorMenu.add(0, FONT_GREEN, 0, "绿色");
        colorMenu.add(0, FONT_BLUE, 0, "蓝色");
        return super.onCreateOptionsMenu(menu);
    }

//     监听菜单项的事件，除了重写onOptionsItemSelected方法，
//     还可以给菜单项分别绑定监听器，通过menuItem.setOnMenuItemClickListener方法（绑定也要在onOptionsItemSelected方法中）
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case FONT_10: text.setTextSize(10 * 2); break;
            case FONT_12: text.setTextSize(12 * 2); break;
            case FONT_14: text.setTextSize(14 * 2); break;
            case FONT_16: text.setTextSize(16 * 2); break;
            case FONT_18: text.setTextSize(18 * 2); break;
            case FONT_RED: text.setTextColor(Color.RED); break;
            case FONT_GREEN: text.setTextColor(Color.GREEN); break;
            case FONT_BLUE: text.setTextColor(Color.BLUE); break;
            case PLAIN_ITEM:
                Toast.makeText(this, "您点击了普通菜单项", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
