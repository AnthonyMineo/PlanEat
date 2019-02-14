package com.denma.planeat.arch.repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.models.remote.Response;
import com.denma.planeat.utils.api.EdamamService;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class ResponseRepository {

    private final Executor executor;
    private EdamamService edamamService;
    private final MutableLiveData<Response> currentResponse = new MutableLiveData<Response>();
    private final MutableLiveData<Recipe> currentRecipe = new MutableLiveData<Recipe>();

    // --- CONSTRUCTOR ---
    @Inject
    public ResponseRepository(Executor executor, EdamamService edamamService){
        this.executor = executor;
        this.edamamService = edamamService;
    }

    // FOR RESPONSE

    // --- GET ---
    public LiveData<Response> getResponse(){ return this.currentResponse; }

    // --- CREATE ---
    public void setResponse(Response response){ this.currentResponse.setValue(response); }

    // --- REMOTE DATA UPDATE ---
    public void updateResponseFromAPI(final String query, final String diet, final String health) {
        executor.execute(() -> {
            edamamService.getRecipes(query, diet, health).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if(response.isSuccessful() && response.body() != null){
                        if (response.body().getHits() != null){
                            // the request return true results
                            setResponse(response.body());
                        } else {
                            // Error, params are incorrect and the response is hits empty
                            setResponse(new Response());
                        }
                    }
                }

                @Override
                public void onFailure(Call<Response> call, Throwable t) {
                    Log.e("TAG", "FAILED from network !");
                }
            });
        });
    }

    // FOR RECIPE

    // --- GET ---
    public LiveData<Recipe> getRecipe(){ return this.currentRecipe; }

    // --- CREATE ---
    public void setRecipe(Recipe recipe){ this.currentRecipe.setValue(recipe); }

}
