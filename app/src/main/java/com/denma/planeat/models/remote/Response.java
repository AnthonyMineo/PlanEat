
package com.denma.planeat.models.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    @SerializedName("success")
    @Expose
    private int success;

    @SerializedName("data")
    @Expose
    private List<Recipe> data;

    // --------------------
    // GETTERS
    // --------------------

    public int getSuccess() { return success; }
    public List<Recipe> getData() { return data; }

    // --------------------
    // SETTERS
    // --------------------

    public void setSuccess(int success) { this.success = success; }
    public void setData(List<Recipe> data) { this.data = data; }
}
