
package com.denma.planeat.models.remote;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("people_number")
    @Expose
    private int peopleNumber;
    @SerializedName("difficulty")
    @Expose
    private int difficulty;
    @SerializedName("picture")
    @Expose
    private String picture;
    @SerializedName("video")
    @Expose
    @Nullable
    private String video;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("cooking_time")
    @Expose
    private int cookingTime;
    @SerializedName("preparing_time")
    @Expose
    private int preparingTime;
    @SerializedName("total_time")
    @Expose
    private Double totalTime;

    // --------------------
    // GETTERS
    // --------------------

    public int getId() { return id; }
    public int getPeopleNumber() { return peopleNumber; }
    public int getDifficulty() { return difficulty; }
    public String getPicture() { return picture; }
    @Nullable
    public String getVideo() { return video; }
    public String getTitle() { return title; }
    public int getCookingTime() { return cookingTime; }
    public int getPreparingTime() { return preparingTime; }
    public Double getTotalTime() { return totalTime; }

    // --------------------
    // SETTERS
    // --------------------

    public void setId(int id) { this.id = id; }
    public void setPeopleNumber(int peopleNumber) { this.peopleNumber = peopleNumber; }
    public void setDifficulty(int difficulty) { this.difficulty = difficulty; }
    public void setPicture(String picture) { this.picture = picture; }
    public void setVideo(@Nullable String video) { this.video = video; }
    public void setTitle(String title) { this.title = title; }
    public void setCookingTime(int cookingTime) { this.cookingTime = cookingTime; }
    public void setPreparingTime(int preparingTime) { this.preparingTime = preparingTime; }
    public void setTotalTime(Double totalTime) { this.totalTime = totalTime; }
}
