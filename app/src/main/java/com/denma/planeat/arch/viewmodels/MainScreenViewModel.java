package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.arch.repositories.ResponseRepository;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.remote.Recipe;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class MainScreenViewModel extends ViewModel {

    // --- REPOSITORIES ---
    private MenuRepository menuDataSource;
    private ResponseRepository responseDataSource;
    private Executor executor;

    // --- CONSTRUCTOR ---
    @Inject
    MainScreenViewModel(MenuRepository menuDataSource, ResponseRepository responseDataSource, Executor executor){
        this.menuDataSource = menuDataSource;
        this.responseDataSource = responseDataSource;
        this.executor = executor;
    }

    // FOR MENU
    // --- GET ---
    public LiveData<List<FoodMenu>> getMenuFrom2WeeksRange(int todayDate){ return this.menuDataSource.getMenuFrom2WeeksRange(todayDate); }
    public LiveData<FoodMenu> getMenuByDate(int eatingDate){ return this.menuDataSource.getMenuByDate(eatingDate); }

    // --- UPDATE ---
    public void updateMenu(final FoodMenu foodMenu){ executor.execute(() ->  MainScreenViewModel.this.menuDataSource.updateMenu(foodMenu));
    }

    // FOR CURRENT MENU
    // --- GET ---
    public LiveData<FoodMenu> getCurrentMenu(){ return this.menuDataSource.getCurrentMenu(); }

    // --- CREATE ---
    public void setCurrentMenu(FoodMenu foodMenu){ this.menuDataSource.setCurrentMenu(foodMenu); }

    // FOR CURRENT RECIPE
    // --- CREATE ---
    public void setCurrentRecipe(Recipe recipe){this.responseDataSource.setCurrentRecipe(recipe); }

}
