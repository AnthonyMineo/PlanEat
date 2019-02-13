
package com.denma.planeat.models.remote;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity
public class Response {

    @PrimaryKey
    @NonNull
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
