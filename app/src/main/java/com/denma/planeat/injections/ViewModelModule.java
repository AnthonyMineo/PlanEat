package com.denma.planeat.injections;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.denma.planeat.arch.viewmodels.MainScreenViewModel;
import com.denma.planeat.arch.viewmodels.RecipeScreenViewModel;
import com.denma.planeat.arch.viewmodels.SearchScreenViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    // Provide a MainScreenViewModel for binding it
    @Binds
    @IntoMap
    @ViewModelKey(MainScreenViewModel.class)
    abstract ViewModel bindMainScreenViewModel(MainScreenViewModel mainScreenViewModel);

    // Provide a SearchScreenViewModel for binding it
    @Binds
    @IntoMap
    @ViewModelKey(SearchScreenViewModel.class)
    abstract ViewModel bindSearchScreenViewModel(SearchScreenViewModel searchScreenViewModel);

    // Provide a RecipeScreenViewModel for binding it
    @Binds
    @IntoMap
    @ViewModelKey(RecipeScreenViewModel.class)
    abstract ViewModel bindRecipeScreenViewModel(RecipeScreenViewModel recipeScreenViewModel);

    // Dagger 2 will provide our ViewModel Factory
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
