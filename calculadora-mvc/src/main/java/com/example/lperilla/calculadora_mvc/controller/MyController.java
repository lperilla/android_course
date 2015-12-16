package com.example.lperilla.calculadora_mvc.controller;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.lperilla.calculadora_mvc.R;
import com.example.lperilla.calculadora_mvc.model.MyModel;
import com.example.lperilla.calculadora_mvc.view.MyView;

/**
 * Created by lperilla on 15/12/15.
 */
public class MyController extends LinearLayout implements View.OnClickListener {

    private MyModel myModel;

    private MyView myView;

    private View keyboardView;

    public MyController(Context context, final MyModel model, final MyView view) {
        super(context);
        this.setMyModel(model);
        this.setMyView(view);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.setKeyboardView(inflater.inflate(R.layout.keyboard_layout, this));

        ((Button) findViewById(R.id.button0)).setOnClickListener(this);
        ((Button) findViewById(R.id.button1)).setOnClickListener(this);
        ((Button) findViewById(R.id.button2)).setOnClickListener(this);
        ((Button) findViewById(R.id.button3)).setOnClickListener(this);
        ((Button) findViewById(R.id.button4)).setOnClickListener(this);
        ((Button) findViewById(R.id.button5)).setOnClickListener(this);
        ((Button) findViewById(R.id.button6)).setOnClickListener(this);
        ((Button) findViewById(R.id.button7)).setOnClickListener(this);
        ((Button) findViewById(R.id.button8)).setOnClickListener(this);
        ((Button) findViewById(R.id.button9)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonPlus)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonEqual)).setOnClickListener(this);
        ((Button) findViewById(R.id.buttonC)).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            Button button = (Button) v;
            char value = button.getText().charAt(0);

            this.getMyModel().setModel(value);
            Log.i("Controller", "Model: " + value);
            switch (button.getId()) {
                case R.id.button0:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button1:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button2:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button3:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button4:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button5:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button6:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button7:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button8:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.button9:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.buttonPlus:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.buttonEqual:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
                case R.id.buttonC:
                    this.getMyView().setView(this.getMyModel().getModel());
                    break;
            }
        }
    }


    public MyView getMyView() {
        return myView;
    }

    public void setMyView(MyView myView) {
        this.myView = myView;
    }

    public MyModel getMyModel() {
        return myModel;
    }

    public void setMyModel(MyModel myModel) {
        this.myModel = myModel;
    }

    public View getKeyboardView() {
        return keyboardView;
    }

    public void setKeyboardView(View keyboardView) {
        this.keyboardView = keyboardView;
    }
}
