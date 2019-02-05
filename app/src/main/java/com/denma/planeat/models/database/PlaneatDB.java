package com.denma.planeat.models.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


import com.denma.planeat.models.local.Menu;

@Database(entities = {Menu.class}, version = 1, exportSchema = false)
public abstract class PlaneatDB extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile PlaneatDB INSTANCE;

    // --- DAO
    public abstract MenuDao menuDao();

}
