package com.denma.planeat.models.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.denma.planeat.models.local.Menu;

import java.util.Date;
import java.util.List;

@Dao
public interface MenuDao {

    @Query("SELECT * FROM Menu")
    LiveData<List<Menu>> getAllMenu();

    @Query(("SELECT * FROM Menu WHERE eatingDate >= :todayDate"))
    LiveData<List<Menu>> getMenuFrom2WeeksRange(int todayDate);

    @Query("SELECT * FROM Menu WHERE eatingDate = :eatingDate")
    LiveData<Menu> getMenuByDate(int eatingDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMenu(Menu menu);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateMenu(Menu menu);

    @Query("DELETE FROM Menu WHERE eatingDate = :eatingDate")
    int deleteMenu(int eatingDate);

}
