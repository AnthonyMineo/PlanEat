package com.denma.planeat.arch.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.denma.planeat.arch.repositories.ResponseRepository;
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

    // --- GET ---
   public LiveData<Response> getResponse(){ return this.responseDataSource.getResponse(); }

    // --- CREATE ---
    public void createResponse(Response response){ executor.execute(() -> this.responseDataSource.createResponse(response)); }

    // --- DELETE ---
    public void deleteResponse(){ executor.execute(() -> this.responseDataSource.deleteResponse()); }

    // --- UPDATE ---
    public void updateResponse(Response response){ executor.execute(() -> this.responseDataSource.updateResponse(response)); }

    // --- REMOTE DATA UPDATE ---
    public void updateResponseFromAPI(String query, String diet, String health){ this.responseDataSource.updateResponseFromAPI(query, diet, health); }
}
