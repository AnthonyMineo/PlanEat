package com.denma.planeat.arch.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.models.remote.Response;
import com.denma.planeat.utils.api.ApiService;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class ResponseRepository {

    private final Executor executor;
    private ApiService apiService;
    private final MutableLiveData<Response> currentResponse = new MutableLiveData<Response>();
    private final MutableLiveData<Recipe> currentRecipe = new MutableLiveData<Recipe>();

    // --- CONSTRUCTOR ---
    @Inject
    public ResponseRepository(Executor executor, ApiService apiService){
        this.executor = executor;
        this.apiService = apiService;
    }

    // FOR RESPONSE
    // --- GET ---
    public LiveData<Response> getResponse(){ return this.currentResponse; }

    // --- CREATE ---
    public void setResponse(Response response){ this.currentResponse.setValue(response); }

    // --- REMOTE DATA UPDATE ---
    public void updateResponseFromAPI(final String query, final String diet, final String health) {
        executor.execute(() -> {
            apiService.getRecipes().enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if(response.isSuccessful()){
                        if (response.body().getSuccess() == 1){
                            // the request return true results
                            setResponse(response.body());
                        } else {
                            setResponse(new Response());
                        }
                    }
                }
                @Override
                public void onFailure(Call<Response> call, Throwable t) { }
            });
        });
    }

    // FOR RECIPE
    // --- GET ---
    public LiveData<Recipe> getCurrentRecipe(){ return this.currentRecipe; }

    // --- CREATE ---
    public void setCurrentRecipe(Recipe recipe){ this.currentRecipe.setValue(recipe); }

}
