package com.example.lperilla.services;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.lperilla.services.utils.Utils;

public class AlarmActivity extends Activity {

    private Button closeBtn;

    private Vibrator vibrator;

    private Ringtone ringtone;

    private int mode;

    //Patron de sonido
    private long pattern[] = {0,300,200,100,500,300,100,200,500};

    private View.OnClickListener closeBtn_onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (mode){
                case AudioManager.RINGER_MODE_SILENT:
                    break;
                case AudioManager.RINGER_MODE_NORMAL:
                    ringtone.stop();
                    break;
                case AudioManager.RINGER_MODE_VIBRATE:
                    vibrator.cancel();
                    break;
            }
            setResult(RESULT_OK);
            AlarmActivity.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        this.closeBtn = (Button) findViewById(R.id.closeBtn);
        this.closeBtn.setOnClickListener(closeBtn_onClickListener);

        AudioManager audioManager = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        mode = audioManager.getRingerMode();

        //Verificando el modo de sonido del movil
        switch (audioManager.getRingerMode()){
            case AudioManager.RINGER_MODE_SILENT:
                break;
            case AudioManager.RINGER_MODE_NORMAL:
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
                this.ringtone = RingtoneManager.getRingtone(this, uri);
                this.ringtone.play();
                break;
            case AudioManager.RINGER_MODE_VIBRATE:
                this.vibrator = (Vibrator) this.getSystemService(VIBRATOR_SERVICE);
                this.vibrator.vibrate(pattern, 1);
                break;
        }

        @SuppressWarnings("deprecation")
        KeyguardManager.KeyguardLock lock = ((KeyguardManager) getSystemService(Activity.KEYGUARD_SERVICE)).newKeyguardLock(KEYGUARD_SERVICE);
        PowerManager powerManager = ((PowerManager) getSystemService(Context.POWER_SERVICE));
        PowerManager.WakeLock wake = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "TAG");

        // lock.disableKeyguard();

        //para trabajar con la pantalla bloqueada

        wake.acquire();

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Utils.msgToast(getApplicationContext(), "ondestroy");
        Intent finalizar = new Intent();
        finalizar.setClass(AlarmActivity.this, ExampleService.class);
        AlarmActivity.this.stopService(finalizar);
    }
}
