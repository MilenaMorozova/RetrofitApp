package com.app.retrofitapp;

public class DateMessage {
    private String date;
    private String milliseconds_since_epoch;
    private String time;

    public String getDate(){return date + " " + time;}
}
