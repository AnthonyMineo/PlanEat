package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.models.local.Menu;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MenuViewModel {

    // --- REPOSITORIES ---
    private MenuRepository menuDataSource;
    private Executor executor;

    // --- CONSTRUCTOR ---
    @Inject
    public MenuViewModel(MenuRepository menuDataSource, Executor executor){
        this.menuDataSource = menuDataSource;
        this.executor = executor;
    }

    // --- GET ---
    public LiveData<List<Menu>> getAllMenu(){ return menuDataSource.getAllMenu(); }
    public LiveData<Menu> getMenuByDate(String eatingDate){ return menuDataSource.getMenuByDate(eatingDate); }

    // --- CREATE ---
    public void createMenu(Menu menu){ menuDataSource.createMenu(menu);}

    // --- DELETE ---
    public void deleteMenu(String eatingDate){ menuDataSource.deleteMenu(eatingDate); }

    // --- UPDATE ---
    public void updateMenu(Menu menu){ menuDataSource.updateMenu(menu); }
}
