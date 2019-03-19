package com.denma.planeat.views.adapter

import android.support.v7.util.DiffUtil
import com.denma.planeat.models.local.FoodMenu
import com.denma.planeat.models.local.Meal
import com.denma.planeat.models.remote.Recipe

class GenericListDiffCallback(private val oldList: List<Any>, private val newList: List<Any>): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return if(oldList.isEmpty()){
            0
        } else {
            oldList.size
        }
    }

    override fun getNewListSize(): Int {
        return if(newList.isEmpty()){
            0
        } else {
            newList.size
        }
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition] is FoodMenu -> {
                val old = oldList[oldItemPosition] as FoodMenu
                val new = newList[newItemPosition] as FoodMenu
                old.eatingDate == new.eatingDate
            }
            oldList[oldItemPosition] is Recipe -> {
                val old = oldList[oldItemPosition] as Recipe
                val new = newList[newItemPosition] as Recipe
                old.label == new.label
            }
            oldList[oldItemPosition] is Meal -> {
                val old = oldList[oldItemPosition] as Meal
                val new = newList[newItemPosition] as Meal
                old.dayTiming == new.dayTiming && old.recipe.label == new.recipe.label
            }
            else -> oldList[oldItemPosition] == newList[newItemPosition]
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}