package com.example.nicholassaunderson.scrollexperimentapplication.views;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.nicholassaunderson.scrollexperimentapplication.R;


public class SettingsActivity extends AppCompatActivity {


    private Switch mySwitch;
    RelativeLayout background;
    RelativeLayout gridBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mySwitch = (Switch) findViewById(R.id.setBlackBackground);
        background = (RelativeLayout) findViewById(R.id.settingsBackground);


        //gridBackground = (RelativeLayout) findViewById(R.id.gridBackground1);




        //set the switch to ON
        mySwitch.setChecked(false);
        //attach a listener to check for changes in state
        mySwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    gridBackground.setBackgroundResource(R.drawable.blackbackground);

                } else {
                    gridBackground.setBackgroundResource(R.drawable.whitebackground);
                }

            }
        });

    }

}