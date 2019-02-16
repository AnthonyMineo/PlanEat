package com.denma.planeat.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.controllers.fragments.ModalFragment;
import com.denma.planeat.controllers.fragments.RecipeFragment;
import com.denma.planeat.controllers.fragments.SearchRequestFragment;
import com.denma.planeat.controllers.fragments.SearchResponseFragment;
import com.denma.planeat.models.remote.Recipe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class SearchActivity extends BaseActivity implements SearchRequestFragment.OnSearchClickListener, SearchResponseFragment.OnRecipeClickListener, HasSupportFragmentInjector {

    // FOR DESIGN
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
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuration
        this.configureDagger();
        this.configureToolBar();
        this.configureFragment();

        // Actions
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
                this.chooseMenu.setVisible(false);
                onBackPressed();
                if(responseFragment.isVisible())
                    this.toolbar.setTitle(getResources().getString(R.string.toolbar_response_title));
                if(requestFragment.isVisible())
                    toolbar.setTitle(getResources().getString(R.string.toolbar_search_title));
                break;
            case R.id.toolbar_menu_choose:
                ModalFragment.newInstance().show(this.getSupportFragmentManager(), "MODAL");
        }
        return true;
    }


}
