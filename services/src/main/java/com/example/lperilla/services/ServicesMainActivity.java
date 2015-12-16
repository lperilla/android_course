package com.example.lperilla.services;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ServicesMainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button startBtn;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_main);

        this.startBtn = (Button) findViewById(R.id.startBtn);
        this.startBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.startBtn){
            this.intent = new Intent(ServicesMainActivity.this, ExampleService.class);
            startService(this.intent);
            ServicesMainActivity.this.finish();
        }
    }
}
