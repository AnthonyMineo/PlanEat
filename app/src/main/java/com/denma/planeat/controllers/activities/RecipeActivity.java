package com.denma.planeat.controllers.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.controllers.fragments.RecipeFragment;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RecipeActivity extends BaseActivity implements HasSupportFragmentInjector {

    // FOR DESIGN
    @BindView(R.id.activity_recipe_layout)
    LinearLayout mainLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_recipe_fragment_layout)
    FrameLayout fragmentLayout;

    // FOR DATA
    RecipeFragment recipeFragment;

    // FOR INJECTION
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    @Override
    public DispatchingAndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }

    // --------------------
    // ON CREATE
    // --------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuration
        this.configureDagger();
        this.configureToolBar();

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
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
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

    private void showRecipeFragment(){
        if(fragmentLayout != null){
            if(recipeFragment == null){
                recipeFragment = new RecipeFragment();
            }
            // replace the fragment to the FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.activity_recipe_fragment_layout, recipeFragment)
                    .commit();
            this.toolbar.setTitle(getResources().getString(R.string.toolbar_recipe_title));
        }
    }

}
