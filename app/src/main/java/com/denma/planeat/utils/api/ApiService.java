package com.denma.planeat.utils.api;

import com.denma.planeat.models.remote.Response;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("get_all_recipe.php")
    Call<Response> getRecipes();
}
