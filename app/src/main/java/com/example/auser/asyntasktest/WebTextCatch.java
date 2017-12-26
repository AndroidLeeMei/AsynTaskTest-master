package com.example.auser.asyntasktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebTextCatch extends AppCompatActivity {
    TextView tv;

    String str,str1;
    String stri;
    StringBuilder sb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_text_catch);
        tv = (TextView) findViewById(R.id.textView);



        new Thread(){
            @Override
            public void run() {
                super.run();

                try {
                    URL url = new URL("http://rate.bot.com.tw/xrt?Lang=zh-TW");
//                    URL url = new URL("https://www.google.com.tw");

                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);
                    BufferedReader br = new BufferedReader(isr);
                    sb=new StringBuilder();

                    while ((str = br.readLine()) != null) {
                        sb.append(str);
                    }
                    str1=sb.toString();
                    System.out.println("====stri5="+ stri);
                    int log0=str1.indexOf("日圓");
                    int log1=str1.indexOf("本行現金賣出",log0);
                    System.out.println("====log0="+ log0);
                    System.out.println("====log1="+ log1);
                    stri=str1.substring(log1);
                    System.out.println("====stri="+ stri);
                    int log2=stri.indexOf(">");
                    int log3=stri.indexOf("</td>");
                    stri=stri.substring(log2,log3);
                    System.out.println("====stri="+ stri);

                    str1=str1.substring(str1.indexOf("日圓"));
                    System.out.println("str日圓=" + str1);
                    str1=str1.substring(str1.indexOf("本行現金賣出"));
                    str1=str1.substring(str1.indexOf(">"),str1.indexOf("</td> "));

                    System.out.println("str1=" +str1);
                    br.close();
                    isr.close();
                    is.close();
//                    Log.d("MyNET", str);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }


    public void clickTest(View v)
    {
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {  //new Runnable透過runOnUiThread方法丟回主執行緒執行
                        @Override
                        public void run() {
//                            tv.setText("Okay!!!");
                            tv.setText("本行現金賣出匯率=" + str1);
//                            tv.setText("本行現金賣出匯率=" + stri);
                        }
                    });

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

}
