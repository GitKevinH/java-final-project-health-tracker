package org.ctac.java103.models;

import java.util.Date;

public class CalorieIntake {
    private String food;
    private int calories;
    private Date date;

    public CalorieIntake(String food, int calories) {
        this.food = food;
        this.calories = calories;
        this.date = new Date();
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}

