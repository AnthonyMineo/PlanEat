package com.denma.planeat.controllers.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MenuViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.models.local.Menu;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.views.adapter.MealOfTheDayAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;

public class MealOfTheDayFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_meal_of_the_day_recycler_view)
    RecyclerView recyclerView;

    // FOR DATA
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MenuViewModel menuViewModel;
    private MealOfTheDayAdapter mealOfTheDayAdapter;

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public MealOfTheDayFragment() {
        // Required empty public constructor
    }

    public static MealOfTheDayFragment newInstance() {
        Bundle args = new Bundle();
        MealOfTheDayFragment fragment = new MealOfTheDayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // --------------------
    // ON CREATE VIEW
    // --------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Configuration
        this.configureDagger();
        this.configureRecyclerView();
        this.configureViewModel();

        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_meal_of_the_day;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure Dagger2
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    // - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // - Create adapter
        this.mealOfTheDayAdapter = new MealOfTheDayAdapter();
        // - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.mealOfTheDayAdapter);
        // - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        ItemClickSupport.addTo(recyclerView, R.layout.plannig_recycle_item)
                .setOnItemClickListener((recyclerView, position, v) -> {

                });
    }

    private void configureViewModel(){
        menuViewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel.class);
        menuViewModel.getCurrentMenu().observe(this, this::updateMeal);
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateMeal(Menu currentMenu){
        mealOfTheDayAdapter.updateData(currentMenu.getMealList());
    }

}
