package com.example.nicholassaunderson.scrollexperimentapplication.views;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.ToggleButton;

import com.example.nicholassaunderson.scrollexperimentapplication.realm.User;
import com.example.nicholassaunderson.scrollexperimentapplication.views.CustomHorizontalScrollView;


import com.example.nicholassaunderson.scrollexperimentapplication.R;

import java.util.ArrayList;
import java.util.Collections;


public class GridActivity extends AppCompatActivity {
    LinearLayout first_layout;
    LinearLayout second_layout;
    LinearLayout third_layout;
    LinearLayout forth_layout;
    LinearLayout fifth_layout;
    LinearLayout sixth_layout;
    LinearLayout.LayoutParams params;
    ArrayList<String> buttonIdentifiers;
    int targetId = User.getUserId();
    int user_id = User.getUserId();

    int screen_transition_point = 522;
    boolean found = false;

    long startTime;
    long endTime;

    float upX;
    float downX;

    long testStartTime;
    long testEndTime;

    long totalTime;
    String background_colour = "white";
    String layout = "STD";
    int error_clicks = 0;
    int screen_found;

    int flag = 1;
    int number_of_swipes = -1;

    int counter = 0;

    ImageView rightArrow;

    HorizontalScrollView scroll;

    private ToggleButton toggle;
    RelativeLayout gridBackground;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(GridActivity.this, MainActivity.class);;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);



        scroll = (HorizontalScrollView) findViewById(R.id.HorizontalScroll);

        first_layout = (LinearLayout) findViewById(R.id.first_layout);
        second_layout = (LinearLayout) findViewById(R.id.second_layout);
        third_layout = (LinearLayout) findViewById(R.id.third_layout);
        forth_layout = (LinearLayout) findViewById(R.id.forth_layout);
        fifth_layout = (LinearLayout) findViewById(R.id.fifth_layout);
        sixth_layout = (LinearLayout) findViewById(R.id.sixth_layout);

        startTime = System.currentTimeMillis();
        endTime = 0;

        //ArrayList of Buttons
        final ArrayList<Button> buttonList = new ArrayList<Button>();



        for(int i=0; i<48; i++) {
            final Button b = new Button(this);
            //b.setText("" + (i + 1));
            b.setGravity(Gravity.CENTER_HORIZONTAL);
            b.setId(i + 1);
            b.setBackgroundResource(getApplicationContext().getResources().getIdentifier("i_"+String.valueOf(i+1),
                    "drawable", getPackageName()));
            buttonList.add(b);

            scroll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    if ((scroll.getScrollX() <= screen_transition_point) && (event.getAction() != 1)) {
                        //System.out.println("Screen1");
                        if(flag == 1){
                            number_of_swipes++;
                            flag = 2;
                        }
                    }

                    if ((scroll.getScrollX() > screen_transition_point) && (event.getAction() != 1)) {
                        //System.out.println("Screen 2");
                        if(flag == 2){
                                number_of_swipes++;
                                flag = 1;
                        }

                    }



                    return false;
                }
            });



            b.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((b.getId()) != targetId) {
                        error_clicks += 1;
                    }
                    if (scroll.getScrollX() > 400) {
                        screen_found = 2;
                    }
                    else{
                        screen_found = 1;
                    }


                    if((b.getId())== targetId){
                        found = true;

                        if(screen_found == 1){
                            if (number_of_swipes%2 == 1){
                                number_of_swipes++;

                            }
                        }

                        if(screen_found == 2){
                            if (number_of_swipes%2 == 0){
                                number_of_swipes++;

                            }
                        }

                        if(number_of_swipes == -1){
                            number_of_swipes = 0;
                        }

                        if (scroll.getScrollX() > 1){
                            scroll.setScrollX(0);
                        }



                        counter += 1;
                        endTime = System.currentTimeMillis();
                        totalTime = (endTime-startTime);
                        System.out.println(user_id + "," + background_colour + "," + layout + "," +
                                targetId + "," + (totalTime/1000.0f) + "," + error_clicks + ","
                                + number_of_swipes + "," + screen_found);
                        startTime = System.currentTimeMillis();
                        endTime = 0;
                        totalTime = 0;
                        error_clicks = 0;

                        //System.out.println("Swipes: " + number_of_swipes);
                        //System.out.println("doing something");
                        number_of_swipes = -1;
                        flag = 1;

                        found = false;
                        shuffleButtons(buttonList);

                    }
                    //shuffleButtons(buttonList);
                    //System.out.println(b.getId());
                    if(counter > 4){
                        gridBackground.setBackgroundResource(R.drawable.blackbackground);
                        background_colour = "black";
                    } if (counter > 9){
                        startActivity(intent);
                        //clear screen with information page
                        //gridBackground1.removeAllViews();
                    }

                }
            });


        }


        shuffleButtons(buttonList);

        rightArrow = (ImageView) findViewById(R.id.imageView2);





    }

    private void shuffleButtons(ArrayList<Button> buttonList){

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params.setMargins(40, 0, 40, 0);


        first_layout.removeAllViews();
        second_layout.removeAllViews();
        third_layout.removeAllViews();
        forth_layout.removeAllViews();
        fifth_layout.removeAllViews();
        sixth_layout.removeAllViews();
        Collections.shuffle(buttonList);

        for (int i = 0; i<48; i++) {
            if (i <8) {
                first_layout.addView(buttonList.get(i), params);
            } else if (i >=8 && i<16) {
                second_layout.addView(buttonList.get(i), params);
            } else if (i>=16 && i<24){
                third_layout.addView(buttonList.get(i), params);
            } else if (i>=24 && i<32){
                forth_layout.addView(buttonList.get(i), params);
            } else if (i>=32 && i<40){
                fifth_layout.addView(buttonList.get(i), params);
            } else if (i>=40 && i<48){
                sixth_layout.addView(buttonList.get(i), params);
            }
        }


    }

    private View.OnClickListener clickListener = new View.OnClickListener(){
        public void onClick(View v){
            Log.d("clicked", "true");
        }
    };

    private View.OnTouchListener touchListener = new View.OnTouchListener(){
        public boolean onTouch(View v, MotionEvent m){
            Log.d("Touched", "true");
            return false;

        }
    };


    @Override
    protected void onStart() {
        super.onStart();

        setBackgroundColor();

    }

    

    public int getId(){
        return targetId;
    }

    public void setBackgroundColor(){
        toggle = (ToggleButton) findViewById(R.id.setBlackBackground);


        gridBackground = (RelativeLayout) findViewById(R.id.gridBackground1);

        //set the switch to ON

        //attach a listener to check for changes in state
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

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


/**
 * if(i<=25){
 b.setBackgroundResource(getApplicationContext().getResources().getIdentifier("i_"+String.valueOf(i+1),
 "drawable", getPackageName()));
 } else{
 b.setBackgroundResource(getApplicationContext().getResources().getIdentifier("t_i_"+String.valueOf(i+1),
 "drawable", getPackageName()));
 }
 **/