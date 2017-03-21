package com.example.nicholassaunderson.scrollexperimentapplication.views;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

import com.example.nicholassaunderson.scrollexperimentapplication.realm.User;
import com.example.nicholassaunderson.scrollexperimentapplication.R;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;


public class GridActivity extends AppCompatActivity {

    /*
    These LinearLayout objects define every row of icons of the screen, where every
    row holds 8 icons(4 on screen 1, and 4 on screen2)
     */
    LinearLayout first_layout;
    LinearLayout second_layout;
    LinearLayout third_layout;
    LinearLayout forth_layout;
    LinearLayout fifth_layout;
    LinearLayout sixth_layout;

    /*
    Retrieves the user id, note that the target id is the same, this is deliberate as the
    target id can be used to reference which icon the user is looking for.
     */

    int targetId = User.getUserId();
    int user_id = User.getUserId();

    //The screen transition point is the exact point between the screens
    int screen_transition_point = 522;
    boolean found = false;

    //These variables are used together to calculate the time to find dependent variable
    long startTime;
    long endTime;
    long totalTime;

    //Initially the default acground colour is white

    String background_colour = "white";
    //This activity is for the grid layout, so ut is name STD
    String layout = "STD";

    //To be used for storing the number of error clicks on the wrong target a user can make
    int error_clicks = 0;
    int screen_found;


    //Flag is defined here to be used later to aid the screen swipes functionality
    int flag = 1;
    int number_of_swipes = -1;
    int counter = 0;

    //This is the arow used to indicated that the user has to scroll right in the beginning
    ImageView rightArrow;

    private ToggleButton toggle;
    RelativeLayout gridBackground;

    String fileName = "output.csv";
    FileWriter writer;

    //This is the horizontal scroll view object, has many uses, such as retrieving screen position
    HorizontalScrollView scroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Intent intent = new Intent(GridActivity.this, MainActivity.class);;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);


        //The scroll object is assigned to the corresponding XML
        scroll = (HorizontalScrollView) findViewById(R.id.HorizontalScroll);

        first_layout = (LinearLayout) findViewById(R.id.first_layout);
        second_layout = (LinearLayout) findViewById(R.id.second_layout);
        third_layout = (LinearLayout) findViewById(R.id.third_layout);
        forth_layout = (LinearLayout) findViewById(R.id.forth_layout);
        fifth_layout = (LinearLayout) findViewById(R.id.fifth_layout);
        sixth_layout = (LinearLayout) findViewById(R.id.sixth_layout);

        rightArrow = (ImageView) findViewById(R.id.imageView2);


        startTime = System.currentTimeMillis();
        endTime = 0;

        //ArrayList where the ImageButtons will be stored(Icons)
        final ArrayList<Button> buttonList = new ArrayList<Button>();




        /*
        This is where most of the logic occurs, the for loop creates a new button and assigns a value to it, which is the
        button id, then the icon image is placed on the button, and finally the button is added to the array list
         */
        for(int i=0; i<48; i++) {
            final Button button = new Button(this);
            button.setGravity(Gravity.CENTER_HORIZONTAL);
            button.setId(i + 1);
            button.setBackgroundResource(getApplicationContext().getResources().getIdentifier("i_"+String.valueOf(i+1),
                    "drawable", getPackageName()));
            buttonList.add(button);

            //This listener is used to calculate if a swipe has occured, and which the state the screen is in
            scroll.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if ((scroll.getScrollX() <= screen_transition_point) && (event.getAction() != 1)) {
                        if(flag == 1){
                            number_of_swipes++;
                            flag = 2;
                        }
                    }
                    if ((scroll.getScrollX() > screen_transition_point) && (event.getAction() != 1)) {
                        if(flag == 2){
                                number_of_swipes++;
                                flag = 1;
                        }
                    }
                    return false;
                }
            });


            /*
            This onClickClistener is used to check multiple elements, first it adds an error click if the incorrect icon
            was selected
             */
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((button.getId()) != targetId) {
                        error_clicks += 1;
                    }

                    //Updates the screen found value, where the button location was found
                    if (scroll.getScrollX() > 400) {
                        screen_found = 2;
                    }
                    else{
                        screen_found = 1;
                    }


                    //If the user has selected the correct button, then many factors can be calculated
                    if((button.getId())== targetId){
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

                        /*
                        This just means that because the button has been found, the reload of the page will set the position to
                        screen 1.
                         */
                        if (scroll.getScrollX() > 1){
                            scroll.setScrollX(0);
                        }
                        counter += 1;

                        //The following calculates the time it took to find the icon
                        endTime = System.currentTimeMillis();
                        totalTime = (endTime-startTime);

                        System.out.println(user_id + "," + background_colour + "," + layout + "," +
                                targetId + "," + (totalTime/1000.0f) + "," + error_clicks + ","
                                + number_of_swipes + "," + screen_found);

                        //All of the variable data is reset for the next trial.
                        startTime = System.currentTimeMillis();
                        endTime = 0;
                        totalTime = 0;
                        error_clicks = 0;

                        number_of_swipes = -1;
                        flag = 1;

                        found = false;

                        /*
                        The shuffle buttons method is then called, this is what creates the next trial, where all of the buttons
                        get shuffled within the list and then re-rendered on screen.
                         */
                        shuffleButtons(buttonList);

                    }

                    /*
                    This is used to change the background colour after a user has found 5 icons on the white background,
                    followed by a further 5 on the black background, once all have been found an intent is executed to direct back to the
                    home screen.
                     */
                    if(counter > 4){
                        gridBackground.setBackgroundResource(R.drawable.blackbackground);
                        background_colour = "black";
                    } if (counter > 9){
                        startActivity(intent);

                    }

                }
            });


        }


        //The button list is shuffle onece upon start up
        shuffleButtons(buttonList);
    }



    /*
    The shuffle buttons method takes the array list of buttons. It first removes all of the views(defined in the
     corresponding xml file) and removes the view i.e emptying anything in them, then loops through 48 times adding
     a button to the corresponding row, where the buttons are retrieved from the newly shuffled array list(line 273)
     */
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

        //Bacground colour gets initiated at on load of the activity
        setBackgroundColor();

    }

    

    public int getId(){
        return targetId;
    }



    //A simple method to toggle the background colour of the screen
    public void setBackgroundColor(){
        toggle = (ToggleButton) findViewById(R.id.setBlackBackground);


        gridBackground = (RelativeLayout) findViewById(R.id.gridBackground1);


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

