package com.denma.planeat.models.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.denma.planeat.models.local.FoodMenu;

@Database(entities = {FoodMenu.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PlaneatDB extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile PlaneatDB INSTANCE;

    // --- DAO
    public abstract MenuDao menuDao();

}
