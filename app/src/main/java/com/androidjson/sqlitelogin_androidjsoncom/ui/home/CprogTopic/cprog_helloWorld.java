package com.androidjson.sqlitelogin_androidjsoncom.ui.home.CprogTopic;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import com.androidjson.sqlitelogin_androidjsoncom.R;

import android.os.Build;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class cprog_helloWorld extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cprog_hello_world);
        String url = "file:///android_asset/cprog/first_program.html";
        WebView view = (WebView) findViewById(R.id.webview);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        view.loadUrl(url);
        view.setWebViewClient(new cprog_helloWorld.MyBrowser());
    }
    private  class MyBrowser extends WebViewClient {
        public boolean shouldOverrideURLLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
