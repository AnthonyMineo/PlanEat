package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.ResponseRepository;
import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.models.remote.Response;

import java.util.concurrent.Executor;

import javax.inject.Inject;

public class ResponseViewModel extends ViewModel {

    // --- REPOSITORIES ---
    private ResponseRepository responseDataSource;
    private Executor executor;

    // --- CONSTRUCTOR ---
    @Inject
    public ResponseViewModel(ResponseRepository responseDataSource, Executor executor){
        this.responseDataSource = responseDataSource;
        this.executor = executor;
    }

    // FOR RESPONSE

    // --- GET ---
    public LiveData<Response> getResponse(){ return this.responseDataSource.getResponse(); }

    // --- CREATE ---
    public void setResponse(Response response){ this.responseDataSource.setResponse(response); }

    // --- REMOTE DATA UPDATE ---
    public void updateResponseFromAPI(String query, String diet, String health){ this.responseDataSource.updateResponseFromAPI(query, diet, health); }

    // FOR RECIPE

    // --- GET ---
    public LiveData<Recipe> getRecipe(){ return this.responseDataSource.getRecipe(); }

    // --- CREATE ---
    public void setRecipe(Recipe recipe){this.responseDataSource.setRecipe(recipe); }

}
