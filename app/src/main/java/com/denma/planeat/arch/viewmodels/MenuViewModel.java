package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.models.local.Menu;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MenuViewModel extends ViewModel {

    // --- REPOSITORIES ---
    private MenuRepository menuDataSource;
    private Executor executor;

    // --- CONSTRUCTOR ---
    @Inject
    public MenuViewModel(MenuRepository menuDataSource, Executor executor){
        this.menuDataSource = menuDataSource;
        this.executor = executor;
    }

    // FOR MENU
    // --- GET ---
    public LiveData<List<Menu>> getAllMenu(){ return this.menuDataSource.getAllMenu(); }
    public LiveData<List<Menu>> getMenuFrom2WeeksRange(int todayDate){ return this.menuDataSource.getMenuFrom2WeeksRange(todayDate); }
    public LiveData<Menu> getMenuByDate(int eatingDate){ return this.menuDataSource.getMenuByDate(eatingDate); }

    // --- CREATE ---
    public void createMenu(final Menu menu){ executor.execute(() -> MenuViewModel.this.menuDataSource.createMenu(menu));
    }

    // --- DELETE ---
    public void deleteMenu(final int eatingDate){ executor.execute(() -> MenuViewModel.this.menuDataSource.deleteMenu(eatingDate));
    }

    // --- UPDATE ---
    public void updateMenu(final Menu menu){ executor.execute(() -> MenuViewModel.this.menuDataSource.updateMenu(menu));
    }

    // FOR CURRENT MENU
    // --- GET ---
    public LiveData<Menu> getCurrentMenu(){ return this.menuDataSource.getCurrentMenu(); }

    // --- CREATE ---
    public void setCurrentMenu(Menu menu){ this.menuDataSource.setCurrentMenu(menu); }
}
