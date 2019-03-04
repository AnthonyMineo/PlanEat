package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.arch.repositories.ResponseRepository;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.remote.Recipe;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class RecipeScreenViewModel extends ViewModel {

    // --- REPOSITORIES ---
    private MenuRepository menuDataSource;
    private ResponseRepository responseDataSource;
    private Executor executor;

    // --- CONSTRUCTOR ---
    @Inject
    RecipeScreenViewModel(MenuRepository menuDataSource, ResponseRepository responseDataSource, Executor executor){
        this.menuDataSource = menuDataSource;
        this.responseDataSource = responseDataSource;
        this.executor = executor;
    }

    // FOR MENU
    // --- GET ---
    public LiveData<FoodMenu> getMenuByDate(int eatingDate){ return this.menuDataSource.getMenuByDate(eatingDate); }

    // --- UPDATE ---
    public void updateMenu(final FoodMenu foodMenu){ executor.execute(() -> RecipeScreenViewModel.this.menuDataSource.updateMenu(foodMenu));
    }

    // FOR CURRENT MENU
    // --- GET ---
    public LiveData<FoodMenu> getCurrentMenu(){ return this.menuDataSource.getCurrentMenu(); }

    // FOR CURRENT RECIPE
    // --- GET ---
    public LiveData<Recipe> getCurrentRecipe(){ return this.responseDataSource.getCurrentRecipe(); }


}
