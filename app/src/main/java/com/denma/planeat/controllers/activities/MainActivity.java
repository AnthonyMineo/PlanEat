package com.denma.planeat.controllers.activities;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.denma.planeat.R;

import com.denma.planeat.controllers.BaseActivity;

import com.denma.planeat.views.adapter.PageAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.AndroidInjection;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, HasSupportFragmentInjector {

    // FOR DESIGN
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_main_drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.activity_main_nav_view)
    NavigationView navigationView;
    @BindView(R.id.activity_main_view_pager)
    ViewPager viewPager;
    @BindView(R.id.activity_main_bottom_navigation)
    BottomNavigationView bottomNavigationView;

    // FOR DATA
    private PageAdapter pagerAdapter;

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
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        // Configuration
        this.configureDagger();
        this.configureToolBar();
        this.configureDrawerLayout();
        this.configureNavigationView();
        this.configureViewPager();
        this.configureBottomView();

        // Action
        this.showFirstFragment();
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
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
    }

    // - Configure Drawer Layout
    private void configureDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    // - Configure NavigationView
    private void configureNavigationView() {
        navigationView.setNavigationItemSelectedListener(this);
    }

    // - Configure ViewPager
    private void configureViewPager() {
        pagerAdapter = new PageAdapter(getSupportFragmentManager());
        // - Set Adapter PageAdapter and glue it together
        this.viewPager.setAdapter(pagerAdapter);
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(bottomNavigationView != null){
                    bottomNavigationView.getMenu().getItem(position).setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    // - Configure BottomNavigationView
    private void configureBottomView(){
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return updateViewPagerCurrentItem(item.getItemId());
            }
        });
    }

    // --------------------
    // ACTIONS
    // --------------------

    // - Update viewPager current item
    private Boolean updateViewPagerCurrentItem(Integer integer){
        switch (integer) {
            case R.id.action_shopping_list:
                viewPager.setCurrentItem(0);
                break;
            case R.id.action_planning:
                viewPager.setCurrentItem(1);
                break;
            case R.id.action_meal_of_the_day:
                viewPager.setCurrentItem(2);
                break;
        }
        return true;
    }

    // - Show first fragment
    private void showFirstFragment(){
        viewPager.setCurrentItem(1);
        bottomNavigationView.setSelectedItemId(R.id.action_planning);
    }


    // --------------------
    // MENUS
    // --------------------

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    // - Handle back click to close menu
    @Override
    public void onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
