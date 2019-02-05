package com.denma.planeat.injections;

/*
Activity Builder
 */

import com.denma.planeat.controllers.activities.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();
}

