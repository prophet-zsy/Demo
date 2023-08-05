package com.example.webview;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


/**
 * 验证H5混合开发中js和原生之间的交互
 */

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    public static final String baiduURL = "https://www.baidu.com";
    private WebView webView;

    @SuppressLint("JavascriptInterface")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.web_view);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(String.valueOf(request.getUrl()));
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webView.loadUrl(baiduURL);  // 加载网络上的url
        webView.loadUrl("file:///android_asset/local.html");  // 加载本地预先存储的html文件
        webView.addJavascriptInterface(this, "android");  // 给 Webview 添加Js调用接口，这里自定义别名为android，Js通过该别名来调用java方法
    }

//    todo  通过JavascriptInterface注解来修饰的方法，可以被js调用
//   js调用java方法，java方法不运行在ui线程中，而是运行在名叫JavaBridge的子线程中
    @JavascriptInterface
    public void showToastAndroid(String args) {
        Log.i(TAG, "方法 showToastAndroid  当前运行线程 " + Thread.currentThread().getName());
        Toast.makeText(this, "from Js to java, and the args is : " + args, Toast.LENGTH_SHORT).show();
//        webView.post(new Runnable() {
//            @Override
//            public void run() {
////                 可以通过webView.loadUrl或webView.evaluateJavascript两个方法来调用js中定义的方法
////                webView.loadUrl("javascript:sum(" + 1 + "," + 2 + ")");
//                webView.evaluateJavascript("sum(" + 1 + "," + 2 + ")", new ValueCallback<String>() {
//                    @Override
//                    public void onReceiveValue(String value) {
//                        Toast.makeText(MainActivity.this, "js返回的结果是=" + value, Toast.LENGTH_LONG).show();
//                    }
//                });
//            }
//        });
    }

//    todo  可以通过webView.loadUrl或webView.evaluateJavascript两个方法来调用js中定义的方法
    public void changeTextInWebView(View view) {
//        webView.loadUrl("javascript:sum(" + 1 + "," + 2 + ")");  // 无返回值调用
        webView.evaluateJavascript("sum(" + 1 + "," + 2 + ")", new ValueCallback<String>() {  // 带回调函数的调用
            @Override
            public void onReceiveValue(String value) {
                Toast.makeText(MainActivity.this, "js返回的结果是=" + value, Toast.LENGTH_LONG).show();
            }
        });
    }
}
