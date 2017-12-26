package com.example.auser.asyntasktest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

//AsyncTask適用於撈取網路大量資料時,完整4步驟
//    1.MyTask extends AsyncTask<>泛型資料型態及個數視情況而定
//    2.doInBackground()在背景撈取資料
//    3.onProgressUpdate在取資料同時可以同時更新進度
//    4.onPostExecute()取完資料竹女戈水竹
public class AsynTaskCountBack extends AppCompatActivity {
    TextView textView;
    Button btnShowImage;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.asyn_task_count_back);
        textView=(TextView)findViewById(R.id.textView);


        btnShowImage=(Button)findViewById(R.id.btnShowImage);
        iv=(ImageView)findViewById(R.id.imageView);

        MyTask task = new MyTask();
        task.execute(10);

    }


    class MyTask extends AsyncTask<Integer,Integer,String>{//網址,數字,點陣址
        //只有doInBackground在背景執行,其它皆在主行緒執行

        @Override
        protected String doInBackground(Integer... params) {  //回傳的值會傳入onpAUSE
            int n=params[0];
            for (int i=n;i>=0;i--){
                //更新進度, 會傳入ProgressUpdate
                publishProgress(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("Task",String.valueOf(i));
            }
            return "倒數計時完畢";
        }

        @Override  //do inBackGround跑完時會執行
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }



        @Override
        protected void onProgressUpdate(Integer... values) {//當進度更新時會傳入這個方法,方法在前景跑
            super.onProgressUpdate(values);
            textView.setText(values[0]+"");
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }
    }
}
