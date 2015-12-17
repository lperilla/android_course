package com.example.lperilla.broadcast_receiver;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BroadcastMainActivity extends Activity implements View.OnClickListener{

    private Button sendSmsBtn;

    private TextView phoneTxf;

    private TextView messageTxf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_main);

        this.phoneTxf = (TextView) findViewById(R.id.phoneTxf);
        this.messageTxf = (TextView) findViewById(R.id.messageTxf);
        this.sendSmsBtn = (Button) findViewById(R.id.sendSMSBtn);
        this.sendSmsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendSMSBtn){
            if (TextUtils.isEmpty(phoneTxf.getText())){
                return;
            }

            if (TextUtils.isEmpty(messageTxf.getText())){
                return;
            }

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneTxf.getText().toString(), null, this.messageTxf.getText().toString(), null, null);
        }
    }
}
