package com.denma.planeat.injections;

/*
Activity Builder
 */

import com.denma.planeat.controllers.activities.MainActivity;
import com.denma.planeat.controllers.activities.RecipeActivity;
import com.denma.planeat.controllers.activities.SearchActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract SearchActivity contributeSearchActivity();

    @ContributesAndroidInjector
    abstract RecipeActivity contributeRecipeActivity();
}

