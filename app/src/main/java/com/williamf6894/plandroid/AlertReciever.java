package com.williamf6894.plandroid;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

/**
 * Created by will on 01/05/16.
 */
public class AlertReciever extends BroadcastReceiver {
    int IdNumber;
    @Override
    public void onReceive(Context context, Intent intent){

        Bundle idData = intent.getExtras();
        IdNumber = idData.getInt("id");
        String titlemsg = idData.getString("title");
        String descmsg = idData.getString("description");

        createNotification(context, titlemsg, descmsg,titlemsg);
    }

    public void createNotification(Context context, String msg, String msgText, String msgAlert){


        PendingIntent notificIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context)
                .setContentTitle(msg)
                .setTicker(msgAlert)
                .setContentText(msgText);

        nBuilder.setContentIntent(notificIntent);
        nBuilder.setDefaults(NotificationCompat.DEFAULT_VIBRATE);
        nBuilder.setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(IdNumber, nBuilder.build());

    }
}
