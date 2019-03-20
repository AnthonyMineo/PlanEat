package com.denma.planeat.controllers.fragments;


import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MainScreenViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.RecipeActivity;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.views.adapter.MealOfTheDayAdapter;

import butterknife.BindView;

public class MealOfTheDayFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_meal_of_the_day_recycler_view)
    RecyclerView recyclerView;

    // FOR DATA
    private MainScreenViewModel mainScreenViewModel;
    private MealOfTheDayAdapter mealOfTheDayAdapter;
    // necessary to stay with the same menu when upload in order to trigger the observer
    private FoodMenu mCurrentFoodMenu;

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public static MealOfTheDayFragment newInstance() {
        Bundle args = new Bundle();
        MealOfTheDayFragment fragment = new MealOfTheDayFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // --------------------
    // LIFE CYCLE
    // --------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Configuration
        this.configureRecyclerView();

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
                    mainScreenViewModel.setCurrentRecipe(mealOfTheDayAdapter.getItem(position).getRecipe());
                    // - Launch RecipeActivity
                    Intent intent = new Intent(getActivity(), RecipeActivity.class);
                    intent.putExtra("parent", "MainActivity");
                    startActivity(intent);
                });
    }

    @Override
    public void configureViewModel(){
        mainScreenViewModel = ViewModelProviders.of(getActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getCurrentMenu().observe(this, this::updateMealFromDB);
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateMealFromDB(FoodMenu currentFoodMenu){
        mainScreenViewModel.getMenuByDate(currentFoodMenu.getEatingDate()).observe(this, this::updateMeal);
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
        mainScreenViewModel.updateMenu(mCurrentFoodMenu);
    }

}
