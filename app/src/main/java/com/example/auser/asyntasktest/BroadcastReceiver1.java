package com.example.auser.asyntasktest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by auser on 2017/11/21.
 */

public class BroadcastReceiver1 extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int count;
        long rowId;
        AlertDialog dialog; //讓自定Layout可有關閉功能
        View root;
        EditText etClass,etOrder;
        //當有其它app安裝,或移除到手機時,會發出系統廣播
        String event =intent.getAction();
        //檢查自定廣播會有反應
//                        <action android:name="android.intent.action.CUSTOM_BROADCAST"></action>…
        if (event.equals("android.intent.action.CUSTOM_BROADCAST")) {
            Toast.makeText(context, "外送訂單AsnyTaskTest: " + event, Toast.LENGTH_SHORT).show();

//            root = getLayoutInflater().inflate(R.layout.dialog_layout, null);//找出根源樹,
//            etClass = (EditText) root.findViewById(R.id.et_class);  //若不使用root,則它會去找主畫面的layout的元件
//            Button confirm = (Button) root.findViewById(R.id.btn_confirm);
//            Button cancel = (Button) root.findViewById(R.id.btn_cancel);
//            confirm.setOnClickListener(dialoglistener);
//            cancel.setOnClickListener(dialoglistener);
//            openOptionsDialog();

//            AlertDialog.Builder builer=new AlertDialog.Builder(context);
//            builer.setTitle("訂單資料");
//            builer.setMessage("訂單明細");
//            builer.setNeutralButton("首頁",dialog_listener);
//            builer.show();


            showNotification();
        }
        else
        //BroadcastReceiver1e沒有繼承activity的類型,所以Toast裏第一個參數不能填this,會有問題
        Toast.makeText(context,"自定廣播偵測到AsnyTaskTest: "+event, Toast.LENGTH_SHORT).show();


    }

    protected void showNotification(){
//        NotificationManager barManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//        PendingIntent conentTntent=PendingIntent.getActivity(
//                this,
//                0,
//                new Intent(this,),
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        Notification barMsg=new Notification.Builder(BroadcastReceiver1.this)  //新版本建立方法
//                .setContentTitle("您的BMI值超過")
//                .setContentText("通知監護人")
//                .setSmallIcon(R.drawable.pikachu)
//                .setContentIntent(conentTntent)
//                .build();
//        barMsg.sound= Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.ios_notification);
//        barManager.notify(0,barMsg);
//
//        Notification barMsg1=new Notification.Builder(this)  //新版本建立方法
//                .setContentTitle("您的BMI值超過標準")
//                .setContentText("通知監護人")
//                .setSmallIcon(R.drawable.pikachu)
//                .setContentIntent(conentTntent)
//                .build();
//        barMsg1.sound= Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.ios_notification);
//        barManager.notify(1,barMsg1);


//        Notification barMsg=new Notification(R.drawable.pikachu,"過重囉",System.currentTimeMillis());
//
//        barMsg.setLatestEventInfo(
//                Report.this,
//                "您的BMI值過"高,
//                "通知監護人",
//                contentIntent
//        );


    }


    DialogInterface.OnClickListener dialog_listener=new DialogInterface.OnClickListener(){
        @Override
        public void onClick(DialogInterface dialog,int which) {
//            Uri uri= Uri.parse("geo:24.930739,121.172424");
//            Uri mapUri = Uri.parse("geo:38.899533,-77.036476");
//            Intent intent = new Intent(Intent.ACTION_VIEW, mapUri);
//            startActivity(intent);

//                Uri uri= Uri.parse("https://sites.google.com./site/gasodroid/");
//                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
//            startActivity(intent);
//            Uri telUri = Uri.parse("tel:100861");
//            Intent intent = new Intent(Intent.ACTION_DIAL, telUri);
            //     startActivity(intent);

            //音樂//取得根路徑
            //     String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            //     Intent intent = new Intent();
            //     intent.setAction(android.content.Intent.ACTION_VIEW);
            //代表一個檔案的物件
            //     File file = new File(baseDir+"/Music/bgm.mp3");//在sdcard/music,使用模擬器時,不支援中文檔名
            //     intent.setDataAndType(Uri.fromFile(file), "audio/*");  //資料類型
            //     startActivity(intent);

//            //打電話,會有權限問題
//            Uri callUri = Uri.parse("tel:100861");
//            Intent intent = new Intent(Intent.ACTION_CALL, callUri);
        }
    };
}
