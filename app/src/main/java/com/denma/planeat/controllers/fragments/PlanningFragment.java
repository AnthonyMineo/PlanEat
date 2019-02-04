package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;

public class PlanningFragment extends BaseFragment {

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public PlanningFragment() {
        // Required empty public constructor
    }

    public static PlanningFragment newInstance() {
        Bundle args = new Bundle();
        PlanningFragment fragment = new PlanningFragment();
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
        return R.layout.fragment_planning;
    }


}
