package com.example.uddd_project.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.uddd_project.DAO_DTO.DAO;
import com.example.uddd_project.R;
import com.example.uddd_project.ThongBao;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView loichao;
    LottieAnimationView lottie;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        loichao = findViewById(R.id.loichao);
        lottie = findViewById(R.id.lottie);

        Thongbao();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(),TrangChu.class);
                startActivity(intent);
                finish();
            }
        },4000);
    }

    private void Thongbao() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        Uri url = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent intent = new Intent(this,ChiTietSanPham.class);
        intent.putExtra("IDSP_CTSP",1);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,getThongbaoID(),intent, PendingIntent.FLAG_UPDATE_CURRENT);


        Notification notification = new NotificationCompat.Builder(this, ThongBao.Chanel_id)
                .setContentTitle("Khuyến Mãi")
                .setContentText("Buổi sáng tràn đầy năng lượng")
                .setSmallIcon(R.drawable.disu)
                .setSound(url)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setColor(getResources().getColor(R.color.mau3))
                .build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if(notificationManager != null){
            notificationManager.notify(getThongbaoID(),notification);
        }
    }

    private int getThongbaoID(){
        return (int) new Date().getTime();
    }
}