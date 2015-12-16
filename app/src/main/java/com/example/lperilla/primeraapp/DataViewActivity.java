package com.example.lperilla.primeraapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.logging.Logger;

public class DataViewActivity extends AppCompatActivity {

    private Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_data_view);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        logger.info("Pase por onCreate Actividad 2");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_data_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        logger.info("id: " + id + "  " + R.id.atras);
        switch (id) {
            case R.id.atras:
                boolean error = false;
                EditText userName = (EditText) findViewById(R.id.userNameTxf);
                EditText lastName = (EditText) findViewById(R.id.lastNameTxf);

                if (userName.getText().toString() == null || userName.getText().toString().isEmpty()) {
                    Toast.makeText(this.getApplicationContext(), "Digite el nombre por favor", Toast.LENGTH_LONG).show();
                    break;
                }

                if (lastName.getText().toString() == null || lastName.getText().toString().isEmpty()) {
                    Toast.makeText(this.getApplicationContext(), "Digite el apellido por favor", Toast.LENGTH_LONG).show();
                    break;
                }
                //Creamos el intent
                Intent regreso = new Intent();
                // Almacenamos los datos de los campos en el intent por medio del metodo putExtra
                regreso.putExtra(MainActivity.USER_NAME_PARAM, userName.getText().toString());
                regreso.putExtra(MainActivity.LAST_NAME_PARAM, lastName.getText().toString());

                setResult(Activity.RESULT_OK, regreso);
                DataViewActivity.this.finish();

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logger.info("Pase por onStart Actividad 2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logger.info("Pase por onResume Actividad 2");
    }
}
