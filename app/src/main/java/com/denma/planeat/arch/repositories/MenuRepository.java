package com.denma.planeat.arch.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.denma.planeat.models.database.MenuDao;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.utils.StorageHelper;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

public class MenuRepository {

    private final MenuDao menuDao;
    private final MutableLiveData<FoodMenu> currentMenu = new MutableLiveData<FoodMenu>();

    // --- CONSTRUCTOR ---
    @Inject
    public MenuRepository(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    // DAO
    // --- GET ---
    public LiveData<List<FoodMenu>> getAllMenu(){ return this.menuDao.getAllMenu(); }
    public LiveData<List<FoodMenu>> getMenuFrom2WeeksRange(int todayDate){ return this.menuDao.getMenuFrom2WeeksRange(todayDate); }
    public LiveData<FoodMenu> getMenuByDate(int eatingDate){ return this.menuDao.getMenuByDate(eatingDate); }

    // --- CREATE ---
    public void createMenu(FoodMenu foodMenu){ this.menuDao.insertMenu(foodMenu); }

    // --- DELETE ---
    public void deleteMenu(int eatingDate){ this.menuDao.deleteMenu(eatingDate); }

    // --- UPDATE ---
    public void updateMenu(FoodMenu foodMenu){ this.menuDao.updateMenu(foodMenu); }


    // FOR CURRENT MENU
    // --- GET ---
    public LiveData<FoodMenu> getCurrentMenu(){ return this.currentMenu; }

    // --- CREATE ---
    public void setCurrentMenu(FoodMenu foodMenu){ this.currentMenu.setValue(foodMenu); }

}
