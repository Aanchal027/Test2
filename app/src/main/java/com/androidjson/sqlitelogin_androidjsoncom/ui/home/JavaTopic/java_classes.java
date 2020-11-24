package com.androidjson.sqlitelogin_androidjsoncom.ui.home.JavaTopic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidjson.sqlitelogin_androidjsoncom.R;

public class java_classes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_classes);
        String url = "file:///android_asset/java_classes.html";
        WebView view = (WebView) findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        }
        view.loadUrl(url);
        view.setWebViewClient(new java_classes.MyBrowser());
    }

    private  class MyBrowser extends WebViewClient {
        public boolean shouldOverrideURLLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
