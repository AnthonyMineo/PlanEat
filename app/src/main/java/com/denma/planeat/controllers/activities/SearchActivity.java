package com.denma.planeat.controllers.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MenuViewModel;
import com.denma.planeat.arch.viewmodels.ResponseViewModel;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.controllers.fragments.ModalFragment;
import com.denma.planeat.controllers.fragments.RecipeFragment;
import com.denma.planeat.controllers.fragments.SearchRequestFragment;
import com.denma.planeat.controllers.fragments.SearchResponseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SearchActivity extends BaseActivity implements SearchRequestFragment.OnSearchClickListener, SearchResponseFragment.OnRecipeClickListener {

    // FOR DESIGN
    @BindView(R.id.activity_search_layout)
    LinearLayout mainLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_search_fragment_layout)
    FrameLayout fragmentLayout;
    private MenuItem chooseMenu;

    // FOR DATA
    SearchRequestFragment requestFragment;
    SearchResponseFragment responseFragment;
    RecipeFragment recipeFragment;

    // FOR INJECTION
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MenuViewModel menuViewModel;
    private ResponseViewModel responseViewModel;

    // --------------------
    // ON CREATE
    // --------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuration
        this.configureDagger();
        this.configureToolBar();
        this.configureFragment();
        this.configureViewModel();

        // Actions
        this.alphaViewAnimation(mainLayout, 100);
        this.showRequestFragment();
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

    private void configureFragment(){
        if(requestFragment == null){
            requestFragment = new SearchRequestFragment();
        }
        if(responseFragment == null){
            responseFragment = new SearchResponseFragment();
        }
        if(recipeFragment == null){
            recipeFragment = new RecipeFragment();
        }
    }

    private void configureViewModel(){
        menuViewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel.class);
        responseViewModel = ViewModelProviders.of(this, viewModelFactory).get(ResponseViewModel.class);
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

    private void showRequestFragment(){
        if(fragmentLayout != null){
            // Add the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_search_fragment_layout, requestFragment).commit();
            this.toolbar.setTitle(getResources().getString(R.string.toolbar_search_title));
        }
    }

    private void showResponseFragment(){
        if(fragmentLayout != null){
            // replace the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_search_fragment_layout, responseFragment)
                    .addToBackStack("Response")
                    .commit();
            this.toolbar.setTitle(getResources().getString(R.string.toolbar_response_title));
        }
    }

    private void showRecipeFragment(){
        if(fragmentLayout != null){
            // replace the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_search_fragment_layout, recipeFragment)
                    .addToBackStack("Recipe")
                    .commit();
            this.toolbar.setTitle(getResources().getString(R.string.toolbar_recipe_title));
        }
    }

    @Override
    public void onSearchClick() {
        showResponseFragment();
    }

    @Override
    public void onRecipeClick() {
        showRecipeFragment();
        this.chooseMenu.setVisible(true);
    }

    // --------------------
    // MENUS
    // --------------------

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // - Inflate the menu and add it to the Toolbar
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_tools, menu);

        this.chooseMenu = menu.findItem(R.id.toolbar_menu_choose);
        this.chooseMenu.setVisible(false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.toolbar_menu_choose:
                ModalFragment.newInstance().show(this.getSupportFragmentManager(), "MODAL");
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.chooseMenu.setVisible(false);
        if(responseFragment.isVisible())
            this.toolbar.setTitle(getResources().getString(R.string.toolbar_response_title));
        if(requestFragment.isVisible())
            toolbar.setTitle(getResources().getString(R.string.toolbar_search_title));
    }
}
