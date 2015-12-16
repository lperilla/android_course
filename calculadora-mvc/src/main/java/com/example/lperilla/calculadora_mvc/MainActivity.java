package com.example.lperilla.calculadora_mvc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.lperilla.calculadora_mvc.controller.MyController;
import com.example.lperilla.calculadora_mvc.model.MyModel;
import com.example.lperilla.calculadora_mvc.view.MyView;

public class MainActivity extends AppCompatActivity {

    private final String CLASS_NAME = MainActivity.class.getName();

    private MyModel myModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                }
            });

            this.myModel = new MyModel();

            MyView myView = new MyView(this);
            MyController controller = new MyController(this, myModel, myView);

            ViewGroup contenedor = (ViewGroup) findViewById(R.id.content_main);
            contenedor.addView(myView);
            contenedor.addView(controller);
        } catch (Exception ex) {
            Log.i(CLASS_NAME, "Error al iniciar MVC");
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
}
