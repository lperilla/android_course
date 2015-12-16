package com.example.lperilla.calculadora_mvc.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.lperilla.calculadora_mvc.R;

/**
 * Created by lperilla on 15/12/15.
 */
public class MyView extends LinearLayout {

    private EditText pantalla;

    public MyView(Context context) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.display, this);
        pantalla = (EditText) findViewById(R.id.editString);
    }

    public void setView(String value) {
        this.pantalla.setText(value);
    }
}
