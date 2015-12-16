package com.example.lperilla.app2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.lperilla.app2.util.Util;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button callBtn;

    private Button contactsBtn;

    private Button googleBtn;

    private Button photoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();
    }

    private void init() {
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);*/

        this.callBtn = (Button) findViewById(R.id.callBtn);
        this.contactsBtn = (Button) findViewById(R.id.contactsBtn);
        this.googleBtn = (Button) findViewById(R.id.googleBtn);
        this.photoBtn = (Button) findViewById(R.id.photoBtn);

        //this.callBtn.setOnClickListener(this);
        this.contactsBtn.setOnClickListener(this);
        this.googleBtn.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            switch (v.getId()) {
                case R.id.callBtn:
                    this.callAction();
                    break;
                case R.id.contactsBtn:
                    this.contactsAction();
                    break;
                case R.id.googleBtn:
                    this.browserAction();
                    break;
                case R.id.photoBtn:
                    this.browserAction();
                    break;
            }
        }
    }

    public void photoOnClickAction(View v) {
        Util.errorDialog(this, new Exception("Error no valido"));
    }

    public void closeOnClickAction(View v) {
        this.finish();
    }

    private void callAction() {
        try {
            Intent callIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(this.getString(R.string.tel_number)));
            this.startActivity(callIntent);
        } catch (SecurityException ex) {
            Util.errorDialog(this, ex);
            ex.printStackTrace();
        }
    }

    private void contactsAction() {
        try {
            Intent contactsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.getString(R.string.contacts)));
            startActivity(contactsIntent);
        } catch (SecurityException ex) {
            Util.errorDialog(this, ex);
            ex.printStackTrace();
        }
    }

    private void browserAction() {
        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(this.getString(R.string.url)));
            startActivity(browserIntent);
        } catch (SecurityException ex) {
            Util.errorDialog(this, ex);
            ex.printStackTrace();
        }
    }


}