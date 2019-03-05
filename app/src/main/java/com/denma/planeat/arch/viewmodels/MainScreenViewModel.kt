package com.denma.planeat.arch.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import com.denma.planeat.arch.repositories.MenuRepository
import com.denma.planeat.arch.repositories.ResponseRepository
import com.denma.planeat.models.local.FoodMenu
import com.denma.planeat.models.remote.Recipe
import java.util.concurrent.Executor
import javax.inject.Inject

class MainScreenViewModel
@Inject
internal constructor(private val menuDataSource: MenuRepository, private val responseDataSource: ResponseRepository, private val executor: Executor ) : ViewModel () {

    // FOR MENU
    // --- GET ---
    fun getMenuFrom2WeeksRange(todayDate: Int): LiveData<List<FoodMenu>> = this.menuDataSource.getMenuFrom2WeeksRange(todayDate)
    fun getMenuByDate(eatingDate: Int): LiveData<FoodMenu> = this.menuDataSource.getMenuByDate(eatingDate)

    // --- UPDATE ---
    fun updateMenu(foodMenu: FoodMenu) = executor.execute { this.menuDataSource.updateMenu(foodMenu)}

    // FOR CURRENT MENU
    // --- GET ---
    fun getCurrentMenu(): LiveData<FoodMenu> = this.menuDataSource.currentMenu

    // --- CREATE ---
    fun setCurrentMenu(foodMenu: FoodMenu) = this.menuDataSource.setCurrentMenu(foodMenu)

    // FOR CURRENT RECIPE
    // --- CREATE ---
    fun setCurrentRecipe(recipe: Recipe) = this.responseDataSource.setCurrentRecipe(recipe)

}