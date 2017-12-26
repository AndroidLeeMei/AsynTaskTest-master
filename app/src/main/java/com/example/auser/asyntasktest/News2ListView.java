package com.example.auser.asyntasktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class News2ListView extends AppCompatActivity {
    WebView wv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news2_list_view);
//        //將在android內部插入外部網站
//        wv=(WebView)findViewById(R.id.webView);
//        String url=getIntent().getStringExtra("url");
//        wv.getSettings().setJavaScriptEnabled(true);//現在網站都很多使用JavaScript,所以打開webview功能
//        wv.setWebViewClient(new WebViewClient());
//        wv.loadUrl(url);
    }
}
