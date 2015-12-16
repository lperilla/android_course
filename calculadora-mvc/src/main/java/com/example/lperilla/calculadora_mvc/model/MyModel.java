package com.example.lperilla.calculadora_mvc.model;

/**
 * Created by lperilla on 15/12/15.
 */
public class MyModel {

    public double acumulador;

    public double operacion;

    private char op;

    public MyModel() {
        this.acumulador = 0.0;
    }

    public String getModel() {
        return acumulador + "";
    }

    public void setModel(char operator) {
        switch (operator) {
            case 'C':
                this.operacion = 0.0;
                this.acumulador = 0.0;
                break;
            case '+':
                this.operacion = acumulador;
                this.acumulador = 0.0;
                break;
            case '=':
                this.acumulador += operacion;
                break;
            default:
                acumulador = acumulador * 10.0 + (operator - '0');
                break;
        }
    }

}
