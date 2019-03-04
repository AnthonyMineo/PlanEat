package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.MenuRepository;
import com.denma.planeat.arch.repositories.ResponseRepository;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.models.remote.Response;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class SearchScreenViewModel extends ViewModel {

    // --- REPOSITORIES ---
    private MenuRepository menuDataSource;
    private ResponseRepository responseDataSource;
    private Executor executor;

    // --- CONSTRUCTOR ---
    @Inject
    SearchScreenViewModel(MenuRepository menuDataSource, ResponseRepository responseDataSource, Executor executor){
        this.menuDataSource = menuDataSource;
        this.responseDataSource = responseDataSource;
        this.executor = executor;
    }

    // FOR MENU
    // --- UPDATE ---
    public void updateMenu(final FoodMenu foodMenu){ executor.execute(() -> SearchScreenViewModel.this.menuDataSource.updateMenu(foodMenu));
    }

    // FOR RESPONSE
    // --- GET ---
    public LiveData<Response> getResponse(){ return this.responseDataSource.getResponse(); }

    // --- REMOTE DATA UPDATE ---
    public void updateResponseFromAPI(String query, String diet, String health){
        this.responseDataSource.updateResponseFromAPI(query, diet, health);
    }

    // FOR CURRENT RECIPE
    // --- CREATE ---
    public void setCurrentRecipe(Recipe recipe){this.responseDataSource.setCurrentRecipe(recipe); }

}
