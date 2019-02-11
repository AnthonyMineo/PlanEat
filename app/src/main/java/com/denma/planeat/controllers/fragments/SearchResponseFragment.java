package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchResponseFragment extends BaseFragment {

    // FOR DESIGN

    // FOR DATA

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public SearchResponseFragment() {
        // Required empty public constructor
    }

    // --------------------
    // ON CREATE VIEW
    // --------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Configuration


        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_search_response;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------



    // --------------------
    // ACTIONS
    // --------------------

}
