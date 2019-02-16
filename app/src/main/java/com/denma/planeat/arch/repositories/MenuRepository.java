package com.denma.planeat.arch.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.denma.planeat.models.database.MenuDao;
import com.denma.planeat.models.local.Menu;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

public class MenuRepository {

    private final MenuDao menuDao;
    private final MutableLiveData<Menu> currentMenu = new MutableLiveData<Menu>();

    // --- CONSTRUCTOR ---
    @Inject
    public MenuRepository(MenuDao menuDao) { this.menuDao = menuDao; }

    // DAO
    // --- GET ---
    public LiveData<List<Menu>> getAllMenu(){ return this.menuDao.getAllMenu(); }
    public LiveData<List<Menu>> getMenuFrom2WeeksRange(int todayDate){ return this.menuDao.getMenuFrom2WeeksRange(todayDate); }
    public LiveData<Menu> getMenuByDate(int eatingDate){ return this.menuDao.getMenuByDate(eatingDate); }

    // --- CREATE ---
    public void createMenu(Menu menu){ this.menuDao.insertMenu(menu); }

    // --- DELETE ---
    public void deleteMenu(int eatingDate){ this.menuDao.deleteMenu(eatingDate); }

    // --- UPDATE ---
    public void updateMenu(Menu menu){ this.menuDao.updateMenu(menu); }


    // FOR CURRENT MENU
    // --- GET ---
    public LiveData<Menu> getCurrentMenu(){ return this.currentMenu; }

    // --- CREATE ---
    public void setCurrentMenu(Menu menu){ this.currentMenu.setValue(menu); }
}
