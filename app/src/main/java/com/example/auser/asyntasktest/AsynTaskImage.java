package com.example.auser.asyntasktest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AsynTaskImage extends AppCompatActivity {
    ImageView img, img2;
    ProgressBar pb, pb2;
    TextView tv, tv2;
    File imgFile;
    int readSum = 0;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyn_task_image);
//        handler = new Handler();
        img = (ImageView) findViewById(R.id.imageView);
        img2 = (ImageView) findViewById(R.id.imageView2);
        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb2 = (ProgressBar) findViewById(R.id.progressBar2);
        tv = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView);

        imgFile = new File(getFilesDir() + File.separator + "photo.jpg");
    }
    public void clickDown(View v)
    {
        pb.setVisibility(View.VISIBLE);
        readSum = 0;
        MyTask task = new MyTask();
        //excute會將參數傳入doInBackground的方法裏
        task.execute("http://images.all-free-download.com/images/graphiclarge/butterfly_flower_01_hd_pictures_166973.jpg");
        pb.setVisibility(View.INVISIBLE);
    }

    public void clickRead(View v)
    {
        Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        img2.setImageBitmap(bitmap);
    }

    class MyTask extends AsyncTask<String, Integer, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... params) {//return的值會變onPostExecute的方參數
            Bitmap bmp = null;
            try {
                URL url = new URL(params[0]); //參數由task.execute的參數傳入
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                final int fullSize = conn.getContentLength();

                InputStream is = conn.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte b[] = new byte[1024];
                int readSize;
                while ((readSize = is.read(b)) != -1)
                {
                    os.write(b, 0, readSize);
                    readSum += readSize;
                    publishProgress(readSum, 100 * readSum / fullSize);  //更新進度時,呼叫onProgressUpdate,傳入兩個參數
                }
                byte result[] = os.toByteArray();
                bmp = BitmapFactory.decodeByteArray(result, 0, result.length);
                FileOutputStream fos = new FileOutputStream(imgFile);
                fos.write(result);
                fos.close();
                is.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tv.setText(String.valueOf(values[0]));
            tv2.setText(String.valueOf(values[1]));
            pb2.setProgress(values[1]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
        }
    }
}
