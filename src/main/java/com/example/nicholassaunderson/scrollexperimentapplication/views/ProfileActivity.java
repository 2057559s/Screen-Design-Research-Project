package com.example.nicholassaunderson.scrollexperimentapplication.views;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.nicholassaunderson.scrollexperimentapplication.realm.User;

import com.example.nicholassaunderson.scrollexperimentapplication.R;

import java.util.ArrayList;

import io.realm.Realm;

import static com.example.nicholassaunderson.scrollexperimentapplication.realm.User.*;

public class ProfileActivity extends AppCompatActivity {

    private ArrayList<Integer> participants;
    int initialId = 1;


    TextView textview;
    TextView textview2;
    TextView textview3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        participants = new ArrayList<Integer>();

        textview = (TextView) findViewById(R.id.textview1);
        textview2 = (TextView) findViewById(R.id.textview2);
        textview3 = (TextView) findViewById(R.id.textview3);
        textview3.setText("User id: " + User.getUserId());


    }

    public void newProfile(View view) {


        //User new_user = new User(initialId);
        //if(participants.contains(initialId)){
            //participants.add(initialId+1);
        initialId++;
        User.setUserId(initialId);

        textview3.setText("User id: " + User.getUserId());




        textview.setText("User Profile Created with id: " + User.getUserId());
        textview2.setText("User Profile with id " + User.getUserId() + " will be used");



    }
}
