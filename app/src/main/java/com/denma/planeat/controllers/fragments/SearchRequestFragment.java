package com.denma.planeat.controllers.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
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
import com.denma.planeat.arch.repositories.ResponseRepository;
import com.denma.planeat.arch.viewmodels.MenuViewModel;
import com.denma.planeat.arch.viewmodels.ResponseViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.SearchActivity;
import com.denma.planeat.utils.TimeAndDateUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.support.AndroidSupportInjection;


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
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ResponseViewModel responseViewModel;

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
        this.configureDagger();
        this.configureViewModel();

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

    // - Configure Dagger2
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        responseViewModel = ViewModelProviders.of(this, viewModelFactory).get(ResponseViewModel.class);
    }


    // --------------------
    // ACTIONS
    // --------------------

    @OnClick(R.id.search_button)
    public void doTheSearch(){
        String query = "";
        String diet = "";
        String health = "";
        responseViewModel.updateResponseFromAPI(query, diet, health);
        callback.onSearchClick();
    }

}
