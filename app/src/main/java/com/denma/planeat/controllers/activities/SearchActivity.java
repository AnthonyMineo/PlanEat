package com.denma.planeat.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.controllers.fragments.SearchRequestFragment;
import com.denma.planeat.controllers.fragments.SearchResponseFragment;

import butterknife.BindView;

public class SearchActivity extends BaseActivity implements SearchRequestFragment.OnSearchClickListener {

    // FOR DESIGN
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_search_fragment_layout)
    FrameLayout fragmentLayout;

    // FOR DATA
    SearchRequestFragment requestFragment;
    SearchResponseFragment responseFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.configureToolBar();
        this.configureFragment();

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

    // - Configure Toolbar
    private void configureToolBar() {
        toolbar.setTitle(getResources().getString(R.string.toolbar_search_title));
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
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof SearchRequestFragment) {
            SearchRequestFragment searchRequestFragment = (SearchRequestFragment) fragment;
            searchRequestFragment.setOnSearchClickListener(this);
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

        }
    }

    private void showResponseFragment(){
        if(fragmentLayout != null){
            // replace the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.activity_search_fragment_layout, responseFragment)
                    .addToBackStack(null)
                    .commit();

        }
    }

    @Override
    public void onSearchClick() {
        showResponseFragment();
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
}
