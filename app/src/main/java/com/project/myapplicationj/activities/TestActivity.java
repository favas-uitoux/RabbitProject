package com.project.myapplicationj.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.project.myapplicationj.R;
import com.project.myapplicationj.Utils;
import com.project.myapplicationj.interfac.TestActivityInterfac;
import com.project.myapplicationj.services.MyAccessibilityService;

public class TestActivity extends AppCompatActivity implements TestActivityInterfac {

    private Button btn1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        Utils.setTestActivityInterfac(TestActivity.this);

        btn1=findViewById(R.id.btn1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     startService();


                startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));
            }
        });

    }

    @Override
    public void showDialog(String msg) {


        if(msg.toLowerCase().contains("bds"))
        {
            Log.d("reached","show warning");
        }

    }


    public void startService() {
        Intent serviceIntent = new Intent(this, MyAccessibilityService.class);
        serviceIntent.putExtra("inputExtra", "Foreground Service Example in Android");
        ContextCompat.startForegroundService(this, serviceIntent);
    }
//    public void stopService() {
//        Intent serviceIntent = new Intent(this, ForegroundService.class);
//        stopService(serviceIntent);
//    }
}
