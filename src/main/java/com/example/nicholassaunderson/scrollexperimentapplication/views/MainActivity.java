package com.example.nicholassaunderson.scrollexperimentapplication.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.nicholassaunderson.scrollexperimentapplication.R;

public class MainActivity extends AppCompatActivity {

    private ToggleButton toggleButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    


    public void openGrid(View view) {
        Intent intent = new Intent(MainActivity.this, InstructionsActivity.class);
        startActivity(intent);
    }


    public void openGridSize(View view) {
        Intent intent = new Intent(MainActivity.this, GridSizeActivity.class);
        startActivity(intent);
    }

    public void openTile(View view) {
        Intent intent = new Intent(MainActivity.this, TileActivity.class);
        startActivity(intent);
    }

    public void openTileNoSpace(View view) {
        Intent intent = new Intent(MainActivity.this, TileSpaceActivity.class);
        startActivity(intent);
    }

    public void openSettings(View view) {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(intent);
    }

    public void openProfile(View view) {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void openGridFam(View view) {
        Intent intent = new Intent(MainActivity.this, GridFamActivity.class);
        startActivity(intent);
    }





}
