package com.example.uddd_project;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class ThongBao extends Application {

    public static final String Chanel_id = "Che";

    @Override
    public void onCreate(){
        super.onCreate();

        TaoKenhTB();
    }
    
    private void TaoKenhTB(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence tentb = getString(R.string.thongbao);
            String noidungtb = getString(R.string.thongbao);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel kenh = new NotificationChannel(Chanel_id, tentb, importance);

            kenh.setDescription(noidungtb);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if(notificationManager != null){
                notificationManager.createNotificationChannel(kenh);
            }

        }
    }
}
