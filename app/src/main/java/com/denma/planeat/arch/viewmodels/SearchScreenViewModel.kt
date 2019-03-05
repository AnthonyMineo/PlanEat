package com.denma.planeat.arch.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import com.denma.planeat.arch.repositories.MenuRepository
import com.denma.planeat.arch.repositories.ResponseRepository
import com.denma.planeat.models.local.FoodMenu
import com.denma.planeat.models.remote.Recipe
import com.denma.planeat.models.remote.Response

import java.util.concurrent.Executor

import javax.inject.Inject

class SearchScreenViewModel
@Inject
internal constructor(private val menuDataSource: MenuRepository, private val responseDataSource: ResponseRepository, private val executor: Executor) : ViewModel() {

    // FOR RESPONSE
    // --- GET ---
    fun getResponse(): LiveData<Response> = this.responseDataSource.response

    // FOR MENU
    // --- UPDATE ---
    fun updateMenu(foodMenu: FoodMenu) = executor.execute { this.menuDataSource.updateMenu(foodMenu) }

    // --- REMOTE DATA UPDATE ---
    fun updateResponseFromAPI(query: String, diet: String?, health: String?) = this.responseDataSource.updateResponseFromAPI(query, diet, health)

    // FOR CURRENT RECIPE
    // --- CREATE ---
    fun setCurrentRecipe(recipe: Recipe) = this.responseDataSource.setCurrentRecipe(recipe)
}
