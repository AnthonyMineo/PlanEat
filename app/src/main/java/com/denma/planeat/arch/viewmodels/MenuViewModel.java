package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.models.local.FoodMenu;

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
    public LiveData<List<FoodMenu>> getAllMenu(){ return this.menuDataSource.getAllMenu(); }
    public LiveData<List<FoodMenu>> getMenuFrom2WeeksRange(int todayDate){ return this.menuDataSource.getMenuFrom2WeeksRange(todayDate); }
    public LiveData<FoodMenu> getMenuByDate(int eatingDate){ return this.menuDataSource.getMenuByDate(eatingDate); }

    // --- CREATE ---
    public void createMenu(final FoodMenu foodMenu){ executor.execute(() -> MenuViewModel.this.menuDataSource.createMenu(foodMenu));
    }

    // --- DELETE ---
    public void deleteMenu(final int eatingDate){ executor.execute(() -> MenuViewModel.this.menuDataSource.deleteMenu(eatingDate));
    }

    // --- UPDATE ---
    public void updateMenu(final FoodMenu foodMenu){ executor.execute(() -> MenuViewModel.this.menuDataSource.updateMenu(foodMenu));
    }

    // FOR CURRENT MENU
    // --- GET ---
    public LiveData<FoodMenu> getCurrentMenu(){ return this.menuDataSource.getCurrentMenu(); }

    // --- CREATE ---
    public void setCurrentMenu(FoodMenu foodMenu){ this.menuDataSource.setCurrentMenu(foodMenu); }
}
