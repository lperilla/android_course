package com.example.lperilla.services.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/**
 * Created by lperilla on 16/12/15.
 */
public class Utils {

    public static void msgToast(Context context, String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }

    public static AlertDialog errorDialog(Context context, String message) {
        AlertDialog.Builder errorDialog = new AlertDialog.Builder(context);
        errorDialog.setCancelable(true);
        errorDialog.setTitle("Error");
        errorDialog.setMessage(message);
        return errorDialog.show();
    }
}
