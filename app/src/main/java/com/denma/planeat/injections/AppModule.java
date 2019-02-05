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

                ContentValues contentValues = new ContentValues();
                contentValues.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(0));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues);

                ContentValues contentValues1 = new ContentValues();
                contentValues1.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(1));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues1);

                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(2));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues2);

                ContentValues contentValues3 = new ContentValues();
                contentValues3.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(3));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues3);

                ContentValues contentValues4 = new ContentValues();
                contentValues4.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(4));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues4);

                ContentValues contentValues5 = new ContentValues();
                contentValues5.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(5));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues5);

                ContentValues contentValues6 = new ContentValues();
                contentValues6.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(6));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues6);

                ContentValues contentValues7 = new ContentValues();
                contentValues7.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(7));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues7);

                ContentValues contentValues8 = new ContentValues();
                contentValues8.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(8));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues8);

                ContentValues contentValues9 = new ContentValues();
                contentValues9.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(9));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues9);

                ContentValues contentValues10 = new ContentValues();
                contentValues10.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(10));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues10);

                ContentValues contentValues11 = new ContentValues();
                contentValues11.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(11));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues11);

                ContentValues contentValues12 = new ContentValues();
                contentValues12.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(12));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues12);

                ContentValues contentValues13 = new ContentValues();
                contentValues13.put("eatingDate", TimeAndDateUtils.getDateWithGapFromToday(13));
                db.insert("Menu", OnConflictStrategy.IGNORE, contentValues13);
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
