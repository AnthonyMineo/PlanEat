package com.denma.planeat.models.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;


import com.denma.planeat.models.local.Menu;
import com.denma.planeat.models.remote.Response;

@Database(entities = {Menu.class, Response.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class PlaneatDB extends RoomDatabase {

    // --- SINGLETON ---
    private static volatile PlaneatDB INSTANCE;

    // --- DAO
    public abstract MenuDao menuDao();
    public abstract ResponseDao responseDao();

}
