package com.codegene.femicodes.lambert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Vehicle Licensing System");


    }

    public void openLogin(View view) {
            Intent i = new Intent(LandingActivity.this, CustomerLoginActivity.class);
            startActivity(i);
    }

    public void openAdminLogin(View view) {
        Intent i = new Intent(LandingActivity.this, AdminLoginActivity.class);
        startActivity(i);
    }
}
