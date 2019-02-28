package com.denma.planeat.controllers.fragments;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MenuViewModel;
import com.denma.planeat.arch.viewmodels.ResponseViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.RecipeActivity;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.views.adapter.MealOfTheDayAdapter;

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
    private ResponseViewModel responseViewModel;
    private MealOfTheDayAdapter mealOfTheDayAdapter;
    // necessary to stay with the same menu when upload in order to trigger the observer
    private FoodMenu mCurrentFoodMenu;

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
        this.mealOfTheDayAdapter = new MealOfTheDayAdapter(this.getContext(), this::showDialog);
        // - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.mealOfTheDayAdapter);
        // - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        ItemClickSupport.addTo(recyclerView, R.layout.plannig_recycle_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    responseViewModel.setCurrentRecipe(mealOfTheDayAdapter.getItem(position).getRecipe());
                    // - Launch RecipeActivity
                    Intent intentAdd = new Intent(getContext() , RecipeActivity.class);
                    startActivity(intentAdd);
                });
    }

    private void configureViewModel(){
        responseViewModel = ViewModelProviders.of(this, viewModelFactory).get(ResponseViewModel.class);
        menuViewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel.class);
        menuViewModel.getCurrentMenu().observe(this, this::updateMealFromDB);
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateMealFromDB(FoodMenu currentFoodMenu){
        menuViewModel.getMenuByDate(currentFoodMenu.getEatingDate()).observe(this, this::updateMeal);
    }

    private void updateMeal(FoodMenu currentFoodMenu){
        mealOfTheDayAdapter.updateData(currentFoodMenu.getMealList());
        this.mCurrentFoodMenu = currentFoodMenu;
    }

    private void showDialog(Meal meal){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(getResources().getString(R.string.delete_meal_dialog_title))
                .setMessage(getResources().getString(R.string.delete_meal_dialog_text))
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    deleteMealFromCurrentMenu(meal);
                })
                .setNegativeButton("No", (dialog, id) -> {
                    dialog.cancel();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void deleteMealFromCurrentMenu(Meal meal){
        this.mCurrentFoodMenu.getMealList().remove(meal);
        menuViewModel.updateMenu(mCurrentFoodMenu);
    }

}
