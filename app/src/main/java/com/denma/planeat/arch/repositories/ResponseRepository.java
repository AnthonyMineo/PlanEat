package com.denma.planeat.arch.repositories;

import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.denma.planeat.models.database.ResponseDao;
import com.denma.planeat.models.remote.Response;
import com.denma.planeat.utils.api.EdamamService;

import java.util.concurrent.Executor;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;

public class ResponseRepository {

    private final ResponseDao responseDao;
    private final Executor executor;
    private EdamamService edamamService;

    // --- CONSTRUCTOR ---
    @Inject
    public ResponseRepository(ResponseDao responseDao, Executor executor, EdamamService edamamService){
        this.responseDao = responseDao;
        this.executor = executor;
        this.edamamService = edamamService;
    }

    // --- GET ---
    public LiveData<Response> getResponse(){ return this.responseDao.getResponse(); }

    // --- CREATE ---
    public void createResponse(Response response){ this.responseDao.insertResponse(response); }

    // --- DELETE ---
    public void deleteResponse(){ this.responseDao.deleteResponse(); }

    // --- UPDATE ---
    public void updateResponse(Response response){ this.responseDao.updateResponse(response); }

    // --- REMOTE DATA UPDATE ---
    public void updateResponseFromAPI(final String query, final String diet, final String health) {
        executor.execute(() -> {
            edamamService.getRecipes(query, diet, health).enqueue(new Callback<Response>() {
                @Override
                public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                    if(response.isSuccessful() && response.body() != null){
                        if (response.body().getHits() != null){
                            // the request return true results
                            executor.execute(() -> {
                                responseDao.insertResponse(response.body());
                            });
                        } else {
                            // Error, params are incorrect and the response is hits empty
                            executor.execute(() -> {
                                responseDao.insertResponse(new Response());
                            });
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

}
