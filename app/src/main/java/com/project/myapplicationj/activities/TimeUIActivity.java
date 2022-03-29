package com.project.myapplicationj.activities;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.project.myapplicationj.R;

import java.text.DecimalFormat;

public class TimeUIActivity extends AppCompatActivity {

    private static final int INTERVAL = 15;
    private static final DecimalFormat FORMATTER = new DecimalFormat("00");

    private TimePicker timePicker1; // set in onCreate
    private NumberPicker minutePicker;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_ui);


//       // NumberPicker   numPick1 = (NumberPicker) findViewById(R.id.numPick1);
//        numPick1.setMinValue(0);
//        numPick1.setMaxValue(3);
//        numPick1.setDisplayedValues(new String[]{"0", "15", "30", "45"});
//        numPick1.setOnLongPressUpdateInterval(100);



        timePicker1 = findViewById(R.id.timePicker1);
//        numPick1.setMinValue(0);
//        numPick1.setMaxValue(3);
//        numPick1.setDisplayedValues(new String[]{"0", "15", "30", "45"});
//        numPick1.setOnLongPressUpdateInterval(100);


        Button btnShow =findViewById(R.id.btnShow);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int pickedValue = numPick1.getValue();
//
//                Toast.makeText(getApplicationContext(),""+   Integer.toString(pickedValue),Toast.LENGTH_LONG).show();

                setMinutePicker();
            }
        });


    }


    public void setMinutePicker() {
        int numValues = 60 / INTERVAL;
        String[] displayedValues = new String[numValues];
        for (int i = 0; i < numValues; i++) {
            displayedValues[i] = FORMATTER.format(i * INTERVAL);
        }

        View minute = timePicker1.findViewById(Resources.getSystem().getIdentifier("minute", "id", "android"));
        if ((minute != null) && (minute instanceof NumberPicker)) {
            minutePicker = (NumberPicker) minute;
            minutePicker.setMinValue(0);
            minutePicker.setMaxValue(numValues - 1);
            minutePicker.setDisplayedValues(displayedValues);
        }
    }


    public int getMinute() {
        if (minutePicker != null) {
            return (minutePicker.getValue() * INTERVAL);
        } else {
            return timePicker1.getCurrentMinute();
        }
    }




}
