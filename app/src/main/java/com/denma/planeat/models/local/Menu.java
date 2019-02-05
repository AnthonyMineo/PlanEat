package com.denma.planeat.models.local;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.Nullable;

import java.util.List;

@Entity
public class Menu {

    @PrimaryKey
    private String eatingDate;
    @Nullable
    private List<Meal> mealList;

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public Menu() { }

    public Menu(@Nullable List<Meal> mealList, String eatingDate) {
        this.mealList = mealList;
        this.eatingDate = eatingDate;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Nullable
    public List<Meal> getMealList() { return mealList; }
    public String getEatingDate() { return eatingDate; }

    // --------------------
    // SETTERS
    // --------------------

    public void setMealList(@Nullable List<Meal> mealList) { this.mealList = mealList; }
    public void setEatingDate(String eatingDate) { this.eatingDate = eatingDate; }
}
