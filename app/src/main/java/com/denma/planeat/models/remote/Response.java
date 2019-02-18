
package com.denma.planeat.models.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {

    long id = 1;

    @SerializedName("hits")
    @Expose
    private List<Hit> hits = null;

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public List<Hit> getHits() {
        return hits;
    }

    public void setHits(List<Hit> hits) {
        this.hits = hits;
    }


}
