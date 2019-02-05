package com.denma.planeat.models.local;


import java.util.List;

public class Menu {

    private List<Meal> mealList;
    private String eatingDate;

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public Menu() { }

    public Menu(List<Meal> mealList, String eatingDate) {
        this.mealList = mealList;
        this.eatingDate = eatingDate;
    }

    // --------------------
    // GETTERS
    // --------------------

    public List<Meal> getMealList() { return mealList; }
    public String getEatingDate() { return eatingDate; }

    // --------------------
    // SETTERS
    // --------------------

    public void setEatingDate(String eatingDate) { this.eatingDate = eatingDate; }
    public void setMealList(List<Meal> mealList) { this.mealList = mealList; }
}
