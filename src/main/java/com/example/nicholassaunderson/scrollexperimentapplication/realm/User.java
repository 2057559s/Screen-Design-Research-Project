package com.example.nicholassaunderson.scrollexperimentapplication.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * Created by nicholassaunderson on 04/12/2016.
 */



public class User{


    private static int user_id;
//    private float timeToFind;
//    private int screenFound;
//    private int numberOfScreenSwipes;
//    private String backgroundColour;
//    private int targetId;
//    private String layout;
//    private String jumpOrScroll;
//    private int numberOfClicks;



    public User(){

    }

    //Add the rest of the keys in this method
    public User(int user_id){//, float timeToFind, int screenFound, int numberOfScreenSwipes, String backgroundColour) {
        this.user_id = user_id;
//        this.timeToFind = timeToFind;
//        this.screenFound = screenFound;
//        this.numberOfScreenSwipes = numberOfScreenSwipes;
//        this.backgroundColour = backgroundColour;
    }

    public static void setUserId(int userId) {
        user_id = userId;
    }

    public static int getUserId(){
        return user_id;
    }






}

