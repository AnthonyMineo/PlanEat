package com.denma.planeat.models.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.denma.planeat.models.local.FoodMenu;

import java.util.List;

@Dao
public interface MenuDao {

    @Query("SELECT * FROM FoodMenu")
    LiveData<List<FoodMenu>> getAllMenu();

    @Query(("SELECT * FROM FoodMenu WHERE eatingDate >= :todayDate"))
    LiveData<List<FoodMenu>> getMenuFrom2WeeksRange(int todayDate);

    @Query("SELECT * FROM FoodMenu WHERE eatingDate = :eatingDate")
    LiveData<FoodMenu> getMenuByDate(int eatingDate);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertMenu(FoodMenu foodMenu);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateMenu(FoodMenu foodMenu);

    @Query("DELETE FROM FoodMenu WHERE eatingDate = :eatingDate")
    int deleteMenu(int eatingDate);

}
