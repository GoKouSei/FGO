package com.gokousei.fgo_tools;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ServantInfoActivity extends AppCompatActivity {
    private String servantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servant_info);
        servantName=getIntent().getExtras().getString("servant_name");

    }
}
