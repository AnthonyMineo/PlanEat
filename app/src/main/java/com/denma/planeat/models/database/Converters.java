package com.denma.planeat.models.database;

import android.arch.persistence.room.TypeConverter;

import com.denma.planeat.models.local.Meal;
import com.denma.planeat.models.remote.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;


public class Converters {
    static Gson gson = new Gson();

    // --- FOR LIST MEAL ---
    @TypeConverter
    public static List<Meal> stringToListMeal(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type locationType = new TypeToken<List<Meal>>() {}.getType();
        return gson.fromJson(data, locationType);
    }

    @TypeConverter
    public static String ListMealToString(List<Meal> meals) {
        return gson.toJson(meals);
    }

    // --- FOR MEAL  ---
    @TypeConverter
    public static Meal stringToMeal(String data) {
        if (data == null) {
            return new Meal();
        }
        Type locationType = new TypeToken<Meal>() {}.getType();
        return gson.fromJson(data, locationType);
    }

    @TypeConverter
    public static String MealToString(Meal meal) {
        return gson.toJson(meal);
    }

    // --- FOR RECIPE ---
    @TypeConverter
    public static Recipe stringToRecipe(String data) {
        if (data == null) {
            return new Recipe();
        }
        Type locationType = new TypeToken<Recipe>() {}.getType();
        return gson.fromJson(data, locationType);
    }

    @TypeConverter
    public static String RecipeToString(Recipe recipe) {
        return gson.toJson(recipe);
    }

    // --- FOR LIST ---
    @TypeConverter
    public static List<String> stringToListString(String data) {
        if (data == null) {
            return Collections.emptyList();
        }
        Type listType = new TypeToken<List<String>>() {}.getType();
        return gson.fromJson(data, listType);
    }

    @TypeConverter
    public static String ListStringToString(List<String> stringList) {
        return gson.toJson(stringList);
    }
}
