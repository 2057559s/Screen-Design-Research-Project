package com.example.nicholassaunderson.scrollexperimentapplication.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.nicholassaunderson.scrollexperimentapplication.R;

public class InstructionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
    }

    public void openMain(View view) {
        Intent intent = new Intent(InstructionsActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openGrid(View view) {
        Intent intent = new Intent(InstructionsActivity.this, GridActivity.class);
        startActivity(intent);
    }

    public void openGridSize(View view) {
        Intent intent = new Intent(InstructionsActivity.this, GridSizeActivity.class);
        startActivity(intent);
    }

    public void openIconTargetInstructions(View view) {
        Intent intent = new Intent(InstructionsActivity.this, TargetIconActivity.class);
        startActivity(intent);
    }

//    public void openTile(View view) {
//        Intent intent = new Intent(InstructionsActivity.this, GridActivity.class);
//        startActivity(intent);
//    }
//
//    public void openTileSpace(View view) {
//        Intent intent = new Intent(InstructionsActivity.this, GridActivity.class);
//        startActivity(intent);
//    }
}
