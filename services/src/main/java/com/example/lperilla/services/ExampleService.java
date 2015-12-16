package com.example.lperilla.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.lperilla.services.utils.Utils;

/**
 * Created by lperilla on 16/12/15.
 */
public class ExampleService extends Service {

    public static final String CLASS_TAG = ExampleService.class.getName();

    // Retardo
    private final int DELAY = 10000;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(CLASS_TAG, "Ejecutando metodo onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(CLASS_TAG, "Ejecutando metodo onCreate");
        try {
            //Se crea una rutina de retardo
            Utils.msgToast(this.getApplicationContext(), this.getString(R.string.example_service_msg_001));
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            Log.e(CLASS_TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(CLASS_TAG, "Ejecutando metodo onStartCommand");

        Utils.msgToast(this.getApplicationContext(), this.getString(R.string.example_service_msg_002));

        Intent alarmIntent = new Intent(getBaseContext(), AlarmActivity.class);
        alarmIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED +
                WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD +
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON +
                WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

        this.getApplication().startActivity(alarmIntent);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i(CLASS_TAG, "Ejecutando metodo onDestroy");

        Utils.msgToast(getApplicationContext(), this.getString(R.string.example_service_msg_003));

        Intent intent = new Intent(getBaseContext(), ServicesMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        this.getApplication().startActivity(intent);
    }
}
