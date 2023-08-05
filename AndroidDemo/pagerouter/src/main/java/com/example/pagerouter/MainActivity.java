package com.example.pagerouter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;


/**
 *  当项目比较庞大时（页面较多），业务变化比较频繁时（页面跳转关系不固定），可以使用页面路由，也可以使用在组件化的场景下
 *
 * 【Android-页面路由Route】https://www.jianshu.com/p/de95f91754c8
 * 【Android路由框架ARouter的基本使用】https://juejin.cn/post/6974659377819025421
 * 【Android路由框架】https://blog.csdn.net/weixin_42345592/article/details/117067895
 * 【Android主流Router库对比（ARouter、ActivityRouter）】https://blog.csdn.net/u013541140/article/details/94406545
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void jump(View view) {
        ARouter.getInstance().build("/app/second").navigation();
    }
}
