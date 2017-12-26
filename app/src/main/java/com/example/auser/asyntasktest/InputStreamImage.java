package com.example.auser.asyntasktest;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class InputStreamImage extends AppCompatActivity {
    ImageView img,img2;
    TextView textView,textProcess;
    ProgressBar progressBar,progressBarlong;
    Bitmap bmp;
    int sum;
    //要儲存照片進手機
    File imgFile;

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_stream_image);

        img = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView2);
        progressBar=(ProgressBar)findViewById(R.id.progressBar);
        progressBarlong=(ProgressBar)findViewById(R.id.progressBar2) ;
        textProcess=(TextView)findViewById(R.id.textProcess) ;

        textView=(TextView)findViewById(R.id.textView);
        textView.setText("從某天起，好像你跟水瓶沒那麼好了，見面少了，電話也少了；孤單的時候，水瓶忍住沒找你。有些話不知從何說起，不如不說；有些秘密只藏在心底，獨自承擔。水瓶不想對你說謊，更害怕你痛心的責備，於是只好假裝忘了你。其實，你一直在水瓶心裏。 ");
        //設定儲存照片的檔名
        imgFile = new File(getFilesDir() + File.separator + "photo.jpg");
    }

    public void clickdown(View target){
        progressBar.setVisibility(View.VISIBLE);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://ww4.sinaimg.cn/bmiddle/7a4aada8ly1fl1u82yinaj20c80c8dgn.jpg");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    final int fullSize=conn.getContentLength();//start to compute process bar,可以取得圖檔的完整長度,即圖檔大小

                    Log.d("fullSize=",""+fullSize);
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();  //OutputStream是一個抽象類別,不可以直接用new的方
                    byte b[] = new byte[500];  //每次讀進500byte
                    int readSize;
                    sum=0;
                    while ((readSize = is.read(b)) != -1)  //is.read讀出位元組陣列,若沒有資料則傳回-1
                    {
//                        Log.d("readSize=" , ""+readSize);
                        os.write(b, 0, readSize);  //1.將資料寫入ByteArrayOutputStream,依次堆入
                        sum+=readSize;
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                progressBarlong.setMax(100);
                                progressBarlong.setProgress(100*sum/fullSize);
                                textProcess.setText((int)(100*sum/(double)fullSize) +"/" +100);

                            }
                        });

                    }
                    byte result[] = os.toByteArray();//將將資料寫入ByteArrayOutputStream的所有資料全部寫入result[]
                    bmp = BitmapFactory.decodeByteArray(result, 0, result.length); //第一種解析圖片的方法 //要放進內部類別的必需要是fianl,Bitmap是點陣讀,讀多少可以存多少
                    Log.d("NET", "Image Finish");
                    //儲存圖檔進手機
                    FileOutputStream fos = new FileOutputStream(imgFile);
                    fos.write(result);
                    fos.close();
                    Log.d("NET", "Image save Finish");
                    //儲存圖檔進手機結束


                    runOnUiThread(new Runnable() {  //android規定以下不能在主執行緒裏run,要起另一個thread
                        @Override
                        public void run() {
                            img.setImageBitmap(bmp);
                            progressBar.setVisibility(View.INVISIBLE);
//                            saveBitmap(bmp);
                        }
                    });
                    is.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }

    public void readImage(View target){
        Log.d("savebmp", "Image save");
        Bitmap bitmap=BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        img2.setImageBitmap(bitmap);

    }

    public void UseHanlder(View target){
        handler = new Handler();

        progressBar.setVisibility(View.VISIBLE);
        new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    URL url = new URL("http://ww4.sinaimg.cn/bmiddle/7a4aada8ly1fl1u82yinaj20c80c8dgn.jpg");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();

                    final int fullSize=conn.getContentLength();//start to compute process bar,可以取得圖檔的完整長度,即圖檔大小

                    Log.d("fullSize=",""+fullSize);
                    InputStream is = conn.getInputStream();
                    ByteArrayOutputStream os = new ByteArrayOutputStream();  //OutputStream是一個抽象類別,不可以直接用new的方
                    byte b[] = new byte[500];  //每次讀進500byte
                    int readSize;
                    sum=0;
                    while ((readSize = is.read(b)) != -1)  //is.read讀出位元組陣列,若沒有資料則傳回-1
                    {
//                        Log.d("readSize=" , ""+readSize);
                        os.write(b, 0, readSize);  //1.將資料寫入ByteArrayOutputStream,依次堆入
                        sum+=readSize;
                        handler.post(new Runnable() {  //hanler開始應用
                            @Override
                            public void run() {
                                progressBarlong.setMax(100);
                                progressBarlong.setProgress(100*sum/fullSize);
                                textProcess.setText((int)(100*sum/(double)fullSize) +"/" +100);
                            }
                        });
                    }
                    byte result[] = os.toByteArray();//將將資料寫入ByteArrayOutputStream的所有資料全部寫入result[]
                    bmp = BitmapFactory.decodeByteArray(result, 0, result.length); //第一種解析圖片的方法 //要放進內部類別的必需要是fianl,Bitmap是點陣讀,讀多少可以存多少
                    Log.d("NET", "Image Finish");
                    //儲存圖檔進手機
                    FileOutputStream fos = new FileOutputStream(imgFile);
                    fos.write(result);
                    fos.close();
                    Log.d("NET", "Image save Finish");
                    //儲存圖檔進手機結束


                    runOnUiThread(new Runnable() {  //android規定以下不能在主執行緒裏run,要起另一個thread
                        @Override
                        public void run() {
                            img.setImageBitmap(bmp);
                            progressBar.setVisibility(View.INVISIBLE);
//                            saveBitmap(bmp);
                        }
                    });
                    is.close();

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }.start();

    }
    public void saveBitmap(Bitmap bitmap) {
//        ;
        FileOutputStream fOut;
        try {
            File dir = new File("/sdcard/demo/");
            if (!dir.exists()) {
                dir.mkdir();
            }

            String tmp = "/sdcard/demo/takepicture.jpg";
            fOut = new FileOutputStream(tmp);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            Log.d("savebmp", "Image save");
            try {
                Log.d("savebmp", "try");
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                Log.d("savebmp", "catch");
                e.printStackTrace();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}


