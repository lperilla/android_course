package com.example.lperilla.notification_app.services;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import com.example.lperilla.notification_app.NotificationMainActivity;
import com.example.lperilla.notification_app.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by lperilla on 21/12/15.
 */
public class NotificationService extends Service implements Runnable {

    private final String CLASS_TAG = NotificationService.class.getName();

    private Thread thread;

    private boolean running;

    private int minutes;

    private long timeStart;

    private DateFormat dateFormat;

    private NotificationCompat.Builder notificationBuilder;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CLASS_TAG, "onCreate");

        this.dateFormat = new SimpleDateFormat("HH:mm:ss");

        this.notificationBuilder = new NotificationCompat.Builder(getApplicationContext());
        this.notificationBuilder.setCategory(Notification.CATEGORY_MESSAGE);
        this.notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        this.notificationBuilder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        this.notificationBuilder.setAutoCancel(true);
        this.notificationBuilder.setContentTitle("Aplicacion de Notificaciones");

        if (this.getThread() == null || !this.getThread().isAlive()) {
            this.timeStart = System.currentTimeMillis();
            this.setThread(new Thread(this));
            Log.i(CLASS_TAG, "Starting thread: " + this.getThread());
            this.getThread().start();
            running = true;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.i(CLASS_TAG, "onStartCommand");

        Bundle extras = intent.getExtras();
        if (extras != null){
            String value = extras.get(this.getString(R.string.saved_minutes_key)).toString();
            this.minutes =  Integer.parseInt(value);
        }
        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(CLASS_TAG, "onBind");
        return null;
    }

    @Override
    public void run() {
        Log.i(CLASS_TAG, "run");
        while (running){
            try{
                long timeCurrent = System.currentTimeMillis() - this.timeStart;
                long minutes = TimeUnit.MILLISECONDS.toMinutes(timeCurrent);
                long seconds = TimeUnit.MILLISECONDS.toSeconds(timeCurrent);

                Log.i(CLASS_TAG, "Minutes: " + minutes + ":"+(seconds%60)+"seg");
                if (minutes == this.minutes){
                    String dateStr = dateFormat.format(new Date());
                    notificationBuilder.setContentText(String.format("Notificación ejecutada a las %s", dateStr));

                    Intent notificationIntent = new Intent(this, NotificationMainActivity.class);
                    PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    notificationBuilder.setContentIntent(resultPendingIntent);

                    NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                    notificationManager.notify((int) this.timeStart, notificationBuilder.build());

                    this.timeStart = System.currentTimeMillis();
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                this.running = false;
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(CLASS_TAG, "onDestroy");
        Log.i(CLASS_TAG, "Stopping thread: " + this.getThread());
        if (this.getThread() != null) {
            this.running = false;
            //this.getThread().interrupt();
            Log.i(CLASS_TAG, "Thread successfully stopped.");
        }
    }

    public Thread getThread() {
        return thread;
    }

    public void setThread(Thread thread) { this.thread = thread; }
}
