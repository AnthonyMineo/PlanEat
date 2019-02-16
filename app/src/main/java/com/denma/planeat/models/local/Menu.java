package com.denma.planeat.models.local;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Menu {

    @PrimaryKey
    @NonNull
    private int eatingDate;
    private String eatingDateString;
    @Nullable
    private List<Meal> mealList;

    // --------------------
    // CONSTRUCTOR
    // --------------------

    public Menu(@Nullable List<Meal> mealList, int eatingDate, String eatingDateString) {
        this.mealList = mealList;
        this.eatingDate = eatingDate;
        this.eatingDateString = eatingDateString;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Nullable
    public List<Meal> getMealList() { return mealList; }
    public int getEatingDate() { return eatingDate; }
    public String getEatingDateString() { return eatingDateString; }
    // --------------------
    // SETTERS
    // --------------------

    public void setMealList(@Nullable List<Meal> mealList) { this.mealList = mealList; }
    public void setEatingDate(int eatingDate) { this.eatingDate = eatingDate; }
    public void setEatingDateString(String eatingDateString) { this.eatingDateString = eatingDateString; }
}
