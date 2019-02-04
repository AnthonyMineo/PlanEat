package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;

public class MealOfTheDayFragment extends BaseFragment {

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
        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_meal_of_the_day;
    }

}
