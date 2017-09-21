package com.gokousei.fgo_tools;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity {

    protected static final String LOG_TAG="com.gokousei.fgo_tools";
    protected static final String BASE_URL="http://fgowiki.com";
    private static Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void makeToast(String message){
        if (toast==null){
            toast=Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT);
        }else {
            toast.setText(message);
        }
        toast.show();
    }
}
