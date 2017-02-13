package com.example.nicholassaunderson.scrollexperimentapplication.views;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.HorizontalScrollView;

/**
 * Created by nicholassaunderson on 23/01/2017.
 */

public class CustomHorizontalScrollView extends HorizontalScrollView {


    public CustomHorizontalScrollView(Context context) {
        super(context);
    }




    @Override
    public void fling (int velocityY)
    {
    /*Scroll view is no longer gonna handle scroll velocity.
    * super.fling(velocityY);
     */
    }
}
