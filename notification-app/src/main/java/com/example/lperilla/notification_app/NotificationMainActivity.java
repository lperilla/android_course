package com.example.lperilla.notification_app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.lperilla.notification_app.services.NotificationService;

import java.util.Date;

public class NotificationMainActivity extends Activity implements View.OnClickListener {

    private final String CLASS_TAG = NotificationMainActivity.class.getName();

    private Intent serviceIntent;

    private Button startBtn;

    private Button stopBtn;

    private NumberPicker minutesPkr;

    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(CLASS_TAG, "onCreate");

        setContentView(R.layout.activity_notification_main);

        this.startBtn = (Button) findViewById(R.id.startBtn);
        this.startBtn.setOnClickListener(this);

        this.stopBtn = (Button) findViewById(R.id.stopBtn);
        this.stopBtn.setOnClickListener(this);

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int defaultValue = this.getResources().getInteger(R.integer.saved_minutes_default);
        int minutes = sharedPref.getInt(this.getString(R.string.saved_minutes_key), defaultValue);

        this.minutesPkr = (NumberPicker) findViewById(R.id.numberPicker);
        this.minutesPkr.setMinValue(this.getResources().getInteger(R.integer.min_minutes));
        this.minutesPkr.setMaxValue(this.getResources().getInteger(R.integer.max_minutes));
        this.minutesPkr.setValue(minutes);

        this.serviceIntent = new Intent(NotificationMainActivity.this, NotificationService.class);

        this.running = isMyServiceRunning(NotificationService.class);
        this.setEnabled(this.running);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.startBtn:
                this.running = true;

                this.setEnabled(this.running);

                this.serviceIntent.putExtra(this.getString(R.string.saved_minutes_key), this.minutesPkr.getValue());
                startService(this.serviceIntent);

                SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt(this.getString(R.string.saved_minutes_key), this.minutesPkr.getValue());
                editor.commit();

                break;
            case R.id.stopBtn:
                this.running = false;

                this.setEnabled(this.running);

                stopService(this.serviceIntent);
                break;
        }
    }

    private void setEnabled(boolean value){
        this.minutesPkr.setEnabled(!value);
        this.startBtn.setEnabled(!value);
        this.stopBtn.setEnabled(value);
    }

    private boolean isMyServiceRunning(Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

}
