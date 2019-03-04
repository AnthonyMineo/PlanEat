package com.denma.planeat.controllers.activities;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
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
import com.denma.planeat.arch.viewmodels.RecipeScreenViewModel;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.controllers.fragments.ModalFragment;
import com.denma.planeat.controllers.fragments.RecipeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;

public class RecipeActivity extends BaseActivity{

    // FOR DESIGN
    @BindView(R.id.activity_recipe_layout)
    LinearLayout mainLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_recipe_fragment_layout)
    FrameLayout fragmentLayout;
    private MenuItem chooseMenu;

    // FOR DATA
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private RecipeScreenViewModel recipeScreenViewModel;
    private RecipeFragment recipeFragment;

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

        // Actions
        this.alphaViewAnimation(mainLayout, 100);
        this.showRecipeFragment();
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_recipe;
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
        this.toolbar.setTitle(getResources().getString(R.string.toolbar_recipe_title));
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configureViewModel(){
        recipeScreenViewModel = ViewModelProviders.of(this, viewModelFactory).get(RecipeScreenViewModel.class);
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

    // --------------------
    // MENUS
    // --------------------

    private void showRecipeFragment(){
        if(fragmentLayout != null){
            if(recipeFragment == null){
                recipeFragment = new RecipeFragment();
            }
            // replace the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_recipe_fragment_layout, recipeFragment)
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // - Inflate the menu and add it to the Toolbar
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search_tools, menu);
        this.chooseMenu = menu.findItem(R.id.toolbar_menu_choose);

        // Change toolbar behavior depending on parent
        if(getIntent().getExtras().getString("parent").equals("MainActivity")){
            this.chooseMenu.setVisible(false);
        }else if (getIntent().getExtras().getString("parent").equals("SearchActivity")){
            this.chooseMenu.setVisible(true);
        }

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

}
