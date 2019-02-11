package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.SearchActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchRequestFragment extends BaseFragment {

    // FOR DESIGN

    // FOR DATA
    public OnSearchClickListener callback;
    public void setOnSearchClickListener(SearchActivity activity) {
        callback = activity;
    }
    public interface OnSearchClickListener {
        void onSearchClick();
    }

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public SearchRequestFragment() {
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
        return R.layout.fragment_search_request;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------


    // --------------------
    // ACTIONS
    // --------------------

    @OnClick(R.id.search_button)
    public void doTheSearch(){
        callback.onSearchClick();
    }

}
