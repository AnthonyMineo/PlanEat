package com.denma.planeat.controllers.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.SearchScreenViewModel;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.controllers.fragments.RecipeFragment;
import com.denma.planeat.controllers.fragments.SearchRequestFragment;
import com.denma.planeat.controllers.fragments.SearchResponseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;

import static com.denma.planeat.utils.ConstantsKt.RECIPE_ACTIVITY_REQUEST_CODE;
import static com.denma.planeat.utils.ConstantsKt.USER_CHOOSE_A_MEAL;

public class SearchActivity extends BaseActivity implements SearchRequestFragment.OnSearchClickListener, SearchResponseFragment.OnRecipeClickListener {

    // FOR DESIGN
    @BindView(R.id.activity_search_layout)
    LinearLayout mainLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;


    // FOR DATA
    private SearchRequestFragment requestFragment;
    private SearchResponseFragment responseFragment;

    // FOR INJECTION
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private SearchScreenViewModel searchScreenViewModel;

    // --------------------
    // ON CREATE
    // --------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuration
        this.configureDagger();
        this.configureToolBar();
        this.configureViewModel();
        this.configureFragments();

        // Actions
        this.alphaViewAnimation(mainLayout, 100);
        this.showRequestFragment();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RECIPE_ACTIVITY_REQUEST_CODE){
            if(resultCode == USER_CHOOSE_A_MEAL){
                finish();
            }
        }
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_search;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure Dagger2
    private void configureDagger(){
        AndroidInjection.inject(this);
    }

    // - Configure Toolbar
    private void configureToolBar() {
        this.toolbar.setTitle(getResources().getString(R.string.toolbar_search_title));
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureViewModel(){
        searchScreenViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchScreenViewModel.class);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof SearchRequestFragment) {
            SearchRequestFragment searchRequestFragment = (SearchRequestFragment) fragment;
            searchRequestFragment.setOnSearchClickListener(this);
        }
        if (fragment instanceof SearchResponseFragment) {
            SearchResponseFragment searchResponseFragment = (SearchResponseFragment) fragment;
            searchResponseFragment.setOnRecipeClickListener(this);
        }
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void alphaViewAnimation(View view, int startDelay){
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(500);
        animation.setStartOffset(startDelay);
        view.startAnimation(animation);
    }

    private void configureFragments(){
        if(requestFragment == null) {
            requestFragment = new SearchRequestFragment();
            // Add the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_fragment_layout, requestFragment)
                    .commit();
        }

        if(responseFragment == null) {
            responseFragment = new SearchResponseFragment();
            // replace the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_fragment_layout, responseFragment)
                    .addToBackStack("response")
                    .commit();
        }
    }

    private void showRequestFragment(){
        // Add the fragment to the FrameLayout container
        getSupportFragmentManager().beginTransaction().hide(responseFragment).commit();
        getSupportFragmentManager().beginTransaction().show(requestFragment).commit();
        this.toolbar.setTitle(getResources().getString(R.string.toolbar_search_title));
    }

    private void showResponseFragment(){
        // replace the fragment to the FrameLayout container
        getSupportFragmentManager().beginTransaction().hide(requestFragment).commit();
        getSupportFragmentManager().beginTransaction().show(responseFragment).commit();
        this.toolbar.setTitle(getResources().getString(R.string.toolbar_response_title));
    }

    private void showRecipeActivity(){
        Intent intent = new Intent(this, RecipeActivity.class);
        intent.putExtra("parent", "RecipeActivity");
        startActivityForResult(intent, RECIPE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void onSearchClick() {
        showResponseFragment();
    }

    @Override
    public void onRecipeClick() {
        showRecipeActivity();
    }

    // --------------------
    // MENUS
    // --------------------

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        if(responseFragment.isVisible())
            showRequestFragment();
        if(requestFragment.isVisible())
            finish();
    }
}
