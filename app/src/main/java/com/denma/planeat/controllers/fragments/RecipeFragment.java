package com.denma.planeat.controllers.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.RecipeScreenViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.models.remote.Recipe;

import butterknife.BindView;

public class RecipeFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.recipe_web_view)
    WebView webView;

    // FOR DATA
    private RecipeScreenViewModel recipeScreenViewModel;

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public RecipeFragment() {
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
        this.configureWebView();

        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_recipe;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    @Override
    public void configureViewModel(){
        recipeScreenViewModel = ViewModelProviders.of(getActivity()).get(RecipeScreenViewModel.class);
        recipeScreenViewModel.getCurrentRecipe().observe(this, this::updateUI);
    }

    private void configureWebView(){
        // - Configure WebView
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateUI(Recipe recipe){
        this.webView.loadUrl(recipe.getUrlSource());
    }

}
