package com.denma.planeat.utils.api;

import com.denma.planeat.BuildConfig;
import com.denma.planeat.models.remote.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EdamamService {

    String apiKey = BuildConfig.Edamam_ApiKey;
    String appId = BuildConfig.Edamam_AppId;

    @GET("search?app_id="+ appId +"&app_key=" + apiKey)
    Call<Response> getRecipes(@Query("q") String query, @Query("diet") String diet, @Query("health") String health);
}
