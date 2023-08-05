package com.example.a049_popupmenu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

/**
 * PopupMenu 弹出式菜单
 * 默认情况下，PopupMenu在锚定的组件上方或下方弹出
 */

public class MainActivity extends AppCompatActivity {

    private Button showPopupMenu;

    private PopupMenu popupMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showPopupMenu = findViewById(R.id.show_popup_menu);
        popupMenu = new PopupMenu(this, showPopupMenu);
        getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "您点击了  【" + menuItem.getTitle() + "】  菜单项", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    public void openPopupMenu(View view) {
        popupMenu.show();
    }
}
