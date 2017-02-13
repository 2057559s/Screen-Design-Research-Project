package com.example.nicholassaunderson.scrollexperimentapplication.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.nicholassaunderson.scrollexperimentapplication.R;
import com.example.nicholassaunderson.scrollexperimentapplication.realm.User;

public class TargetIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_icon);
        //int id = GridActivity.getId();

        ImageButton target = (ImageButton) findViewById(R.id.targetIcon);
        target.setBackgroundResource(getApplicationContext().getResources().getIdentifier("i_"+User.getUserId(),
                "drawable", getPackageName()));
    }

    public void openGrid(View view) {
        Intent intent = new Intent(TargetIconActivity.this, GridActivity.class);
        startActivity(intent);
    }


}
