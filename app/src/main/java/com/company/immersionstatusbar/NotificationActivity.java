package com.company.immersionstatusbar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Button notification = findViewById(R.id.notification);
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNotificationChannel(NotificationActivity.this, 0);
            }
        });
    }


    public void createNotificationChannel(Context context, int notifactionId) {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder notification = null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = String.valueOf(notifactionId);
            CharSequence channelName = "channelName";
            String channelDescription = "channelDescription";
            int channelImportance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, channelImportance);
            // 设置描述 最长30字符
            notificationChannel.setDescription(channelDescription);
            // 该渠道的通知是否使用震动
            notificationChannel.enableVibration(true);
            // 设置显示模式
            notificationChannel.setLockscreenVisibility(NotificationCompat.VISIBILITY_SECRET);
//            notificationChannel.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order_tishi), null);
            Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/itachi85/"));
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);

            notificationManager.createNotificationChannel(notificationChannel);
            notification = new Notification.Builder(context);
            notification.setChannelId(channelId);
            notification.setContentIntent(pendingIntent);
            notification.setContentTitle("活动");
            notification.setContentText("您有一项新活动");
//            notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order_tishi));
            notification.setSmallIcon(R.drawable.guide2).build();

        } else {
            notification = new Notification.Builder(context);
            notification.setAutoCancel(true)
                    .setContentText("自定义推送声音111")
                    .setContentTitle("111")
                    .setSmallIcon(R.drawable.splash)
                    .setDefaults(Notification.DEFAULT_ALL);
//            notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.order_tishi));
        }
        notificationManager.notify(1024, notification.getNotification());

    }
}
