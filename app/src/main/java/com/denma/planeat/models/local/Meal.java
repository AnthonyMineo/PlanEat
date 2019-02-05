package com.denma.planeat.models.local;

import com.denma.planeat.models.remote.Recipe;

public class Meal {

    private Recipe recipe;
    private int dayTiming;

    // --------------------
    // CONSTRUCTOR
    // --------------------

    public Meal(Recipe recipe, int dayTiming) {
        this.recipe = recipe;
        this.dayTiming = dayTiming;
    }

    // --------------------
    // GETTERS
    // --------------------

    public Recipe getRecipe() { return recipe; }
    public int getDayTiming() { return dayTiming; }

    // --------------------
    // SETTERS
    // --------------------

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }
    public void setDayTiming(int dayTiming) { this.dayTiming = dayTiming; }
}
