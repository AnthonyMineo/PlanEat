package com.denma.planeat.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.denma.planeat.arch.viewmodels.MenuViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    // Provide a MenuViewModel for binding it
    @Binds
    @IntoMap
    @ViewModelKey(MenuViewModel.class)
    abstract ViewModel bindMenuViewModel(MenuViewModel menuViewModel);

    // Dagger 2 will provide our ViewModel Factory
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}