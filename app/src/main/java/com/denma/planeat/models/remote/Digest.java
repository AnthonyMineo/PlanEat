
package com.denma.planeat.models.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Digest {

    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("total")
    @Expose
    private Double total;
    @SerializedName("unit")
    @Expose
    private String unit;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
