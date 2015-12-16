package com.example.lperilla.app2.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by lperilla on 15/12/15.
 */
public class Util {

    public static AlertDialog errorDialog(Context context, Exception ex) {
        AlertDialog.Builder errorDialog = new AlertDialog.Builder(context);
        errorDialog.setCancelable(true);
        errorDialog.setTitle("Error: " + ex.getClass());
        errorDialog.setMessage(ex.getMessage());
        return errorDialog.show();
    }
}
