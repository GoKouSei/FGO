package com.gokousei.fgo_tools;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BaseActivity extends AppCompatActivity {

    protected static final String LOG_TAG="com.gokousei.fgo_tools";
    protected static final String BASE_URL="http://fgowiki.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
