package com.project.myapplicationj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.project.myapplicationj.activities.DashboardAvtivity;

public class MainActivity extends AppCompatActivity {

    private Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), DashboardAvtivity.class);
                startActivity(intent);
            }
        });



    }

    private void init()
    {
        btnShow=findViewById(R.id.btnShow);


    }

}