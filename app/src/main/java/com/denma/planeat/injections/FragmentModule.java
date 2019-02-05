package com.denma.planeat.injections;
/*
Fragment Builder
 */
import com.denma.planeat.controllers.fragments.MealOfTheDayFragment;
import com.denma.planeat.controllers.fragments.PlanningFragment;
import com.denma.planeat.controllers.fragments.ShoppingListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract ShoppingListFragment contributeShoppingListFragment();

    @ContributesAndroidInjector
    abstract PlanningFragment contributePlanningFragment();

    @ContributesAndroidInjector
    abstract MealOfTheDayFragment contributeMealOfTheDayFragment();
}
