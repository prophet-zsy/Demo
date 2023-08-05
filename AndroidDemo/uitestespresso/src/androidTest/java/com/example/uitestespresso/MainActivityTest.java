package com.example.uitestespresso;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ThreadPoolExecutor;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule  // rule注解 修饰属性 类似于 before注解
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void loginTest() {
//        清空内容，输入内容，关闭键盘，校验内容
        onView(withId(R.id.et_01))
                .perform(clearText(), replaceText("你好 username"), closeSoftKeyboard())
                .check(matches(withText("你好 username")));
//        点击登录
        onView(withId(R.id.btn02))
                .perform(click());
//        校验登录是否成功
        onView(withId(R.id.tv_content))
                .check(matches(withText("success")))
                .check(matches(isDisplayed()));
        onView(withId(R.id.et_01))
                .check(matches(withText("")))
                .check(matches(withHint("请输入账户名")));
        try {
            Thread.sleep(3000);  //  暂停一下，可以在手机上看到界面
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void changeContentTest() {
//        检查是否不可见
        onView(withId(R.id.tv_content))
                .check(matches(not(isDisplayed())));
//        点击修改
        onView(withId(R.id.btn01))
                .check(matches(withText("修改内容")))
                .perform(click());
//        检查修改后内容，检查是否可见
        onView(withId(R.id.tv_content))
                .check(matches(withText("hello espresso!")))
                .check(matches(isDisplayed()));
    }
}