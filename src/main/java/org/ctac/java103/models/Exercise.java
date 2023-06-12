package org.ctac.java103.models;

import java.util.Date;
import java.util.Scanner;

public class Exercise {
    private String name;
    private int duration;
    private int calsBurned;
    private Date date;

    public Exercise(String name, int duration, int calsBurned) {
        this.name = name;
        this.duration = duration;
        this.calsBurned = calsBurned;
        this.date = new Date();
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public int getCalsBurned() {
        return calsBurned;
    }

    public Date getDate() {
        return date;
    }



}