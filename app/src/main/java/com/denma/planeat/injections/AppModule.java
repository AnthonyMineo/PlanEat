package com.denma.planeat.injections;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.models.database.MenuDao;
import com.denma.planeat.models.database.PlaneatDB;
import com.denma.planeat.utils.TimeAndDateUtils;


import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    // --- CONTEXT INJECTION ---
    @Provides
    @Singleton
    Context provideAppContext(Application application){
        return application;
    }

    // --- INTERN STORAGE INJECTION ---
    @Provides
    File provideDestination(Context context){
        return context.getFilesDir();
    }

    // --- DB INJECTION ---
    @Provides
    @Singleton
    PlaneatDB provideDatabase(Application application){
        return Room.databaseBuilder(application,
                PlaneatDB.class, "PlaneatDB.db")
                .addCallback(prepopulateDatabase())
                .build();
    }

    private static RoomDatabase.Callback prepopulateDatabase(){
        return new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                for(int i=0; i<14; i++){
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("eatingDate", TimeAndDateUtils.formatDateToInt_yyyyMMdd(TimeAndDateUtils.getDateWithGapFromToday(i)));
                    contentValues.put("eatingDateString", TimeAndDateUtils.formatDateToString_EEEdd(TimeAndDateUtils.getDateWithGapFromToday(i)));
                    db.insert("Menu", OnConflictStrategy.IGNORE, contentValues);
                }
            }
        };
    }

    @Provides
    @Singleton
    MenuDao provideMenuDao(PlaneatDB db) { return db.menuDao(); }

    // --- REPOSITORY INJECTION ---
    @Provides
    Executor provideExecutor(){
        return Executors.newSingleThreadExecutor();
    }

    @Provides
    @Singleton
    MenuRepository provideMenuRepository(MenuDao menuDao){
        return new MenuRepository(menuDao);
    }
}
