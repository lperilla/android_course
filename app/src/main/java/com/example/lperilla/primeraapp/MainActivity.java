package com.example.lperilla.primeraapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    private static final int INTENT_GET_MSG = 1;
    public static String USER_NAME_PARAM = "userName";
    public static String LAST_NAME_PARAM = "lastName";
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            onClickAction_Button(v);
        }
    };
    private Button buttonIr;
    private TextView mensajeBottonTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.buttonIr = (Button) findViewById(R.id.irBtn);
        buttonIr.setOnClickListener(this.onClickListener);

        mensajeBottonTxt = (TextView) findViewById(R.id.mensajeBotonTxt);

        logger.info("Pase por onCreate Actividad 1");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MainActivity.INTENT_GET_MSG) {
            if (resultCode == Activity.RESULT_OK) {
                String userName = data.getStringExtra(MainActivity.USER_NAME_PARAM);
                String lastName = data.getStringExtra(MainActivity.LAST_NAME_PARAM);
                Toast.makeText(getApplicationContext(), "Usuario: " + userName + " Apellido: " + lastName, Toast.LENGTH_LONG);
                this.mensajeBottonTxt.setText("Usuario: " + userName + " Apellido: " + lastName);
            } else {
                Toast.makeText(getApplicationContext(), "Error de mensaje", Toast.LENGTH_LONG);
                this.mensajeBottonTxt.setText("Error!");
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.info("Pase por onStart Actividad 1");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.info("Pase por onResume Actividad 1");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logger.info("Pase por onPause Actividad 1");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logger.info("Pase por onStop Actividad 1");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logger.info("Pase por onRestart Actividad 1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickAction_Button(View v) {
        logger.info("Ejecutando onClickAction_Button");
        Intent intent = new Intent(MainActivity.this, DataViewActivity.class);
        startActivityForResult(intent, INTENT_GET_MSG);

        //startActivity(intent);
    }
}
