package com.denma.planeat.arch.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel

import com.denma.planeat.arch.repositories.MenuRepository
import com.denma.planeat.arch.repositories.ResponseRepository
import com.denma.planeat.models.local.FoodMenu
import com.denma.planeat.models.remote.Recipe

import java.util.concurrent.Executor

import javax.inject.Inject

class RecipeScreenViewModel
@Inject
internal constructor(private val menuDataSource: MenuRepository, private val responseDataSource: ResponseRepository, private val executor: Executor) : ViewModel() {

    // FOR CURRENT MENU
    // --- GET ---
    fun getCurrentMenu(): LiveData<FoodMenu> = this.menuDataSource.currentMenu

    // FOR CURRENT RECIPE
    // --- GET ---
    fun getCurrentRecipe(): LiveData<Recipe> = this.responseDataSource.currentRecipe

    // FOR MENU
    // --- GET ---
    fun getMenuByDate(eatingDate: Int): LiveData<FoodMenu> = this.menuDataSource.getMenuByDate(eatingDate)

    // --- UPDATE ---
    fun updateMenu(foodMenu: FoodMenu) = executor.execute { this.menuDataSource.updateMenu(foodMenu) }
}
