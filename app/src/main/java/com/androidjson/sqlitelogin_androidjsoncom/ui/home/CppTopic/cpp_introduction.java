package com.androidjson.sqlitelogin_androidjsoncom.ui.home.CppTopic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidjson.sqlitelogin_androidjsoncom.R;
import com.androidjson.sqlitelogin_androidjsoncom.ui.home.JavaTopic.javaIntroduction;

public class cpp_introduction extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp_introduction);
        String url = "file:///android_asset/cpp/introduction_cpp.html";
        WebView view = (WebView) findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        view.loadUrl(url);
        view.setWebViewClient(new cpp_introduction.MyBrowser());
    }
    private  class MyBrowser extends WebViewClient {
        public boolean shouldOverrideURLLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
