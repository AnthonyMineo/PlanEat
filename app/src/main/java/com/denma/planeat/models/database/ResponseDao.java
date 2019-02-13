package com.denma.planeat.models.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.denma.planeat.models.remote.Response;

@Dao
public interface ResponseDao {

    @Query("SELECT * FROM Response WHERE id = 1")
    LiveData<Response> getResponse();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertResponse(Response response);

    @Update
    int updateResponse(Response response);

    @Query("DELETE FROM Response WHERE id = 1")
    int deleteResponse();

}
