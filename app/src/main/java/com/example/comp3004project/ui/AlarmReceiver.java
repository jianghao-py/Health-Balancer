package com.example.comp3004project.ui;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.comp3004project.MainActivity;
import com.example.comp3004project.ui.notifications.NotificationsFragment;

import static android.content.Context.NOTIFICATION_SERVICE;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        int notificationId = intent.getIntExtra("notificationId",0);
        String message = intent.getStringExtra("Time to Recode Calories");
        String NOTIFICATION_CHANNEL_ID = "channel_1";

        Intent maninIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,0,maninIntent,0);

        NotificationManager myNotificationManager = (NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder((context));
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("It's Time!")
                .setContentText(message)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent);
        int mNotificationId = (int) System.currentTimeMillis();
        NotificationManager mNotifyMgr =
                (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O)
        {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "NOTIFICATION_CHANNEL_NAME", importance);

            builder.setChannelId(NOTIFICATION_CHANNEL_ID);
            mNotifyMgr.createNotificationChannel(notificationChannel);
        }
        mNotifyMgr.notify(mNotificationId, builder.build());

        myNotificationManager.notify(notificationId, builder.build());
    }

}
