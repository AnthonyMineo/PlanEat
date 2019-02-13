package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.SearchActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchRequestFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.search_request_edit_text)
    EditText keyWord;
    @BindView(R.id.radio_balanced)
    RadioButton balanced;
    @BindView(R.id.radio_high_protein)
    RadioButton highProtein;
    @BindView(R.id.radio_low_fat)
    RadioButton lowFat;
    @BindView(R.id.radio_low_carbs)
    RadioButton lowCarbs;
    @BindView(R.id.radio_vegetarian)
    RadioButton vegetarian;
    @BindView(R.id.radio_vegan)
    RadioButton vegan;
    @BindView(R.id.check_box_sugar_conscious)
    CheckBox sugarConscious;
    @BindView(R.id.check_box_alcohol_free)
    CheckBox alcoholFree;
    @BindView(R.id.check_box_peanut_free)
    CheckBox peanutFree;
    @BindView(R.id.check_box_tree_nut_free)
    CheckBox treeNutFree;

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
