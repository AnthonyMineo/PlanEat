package com.denma.planeat.controllers.fragments;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MainScreenViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.MainActivity;
import com.denma.planeat.models.local.FoodMenu;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.utils.TimeAndDateUtils;
import com.denma.planeat.views.adapter.PlanningAdapter;

import java.util.List;

import butterknife.BindView;

public class PlanningFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_planning_recycler_view)
    RecyclerView recyclerView;

    // FOR DATA
    private MainScreenViewModel mainScreenViewModel;
    private PlanningAdapter planningAdapter;

    public OnMenuClickListener callback;
    public void setOnMenuClickListener(MainActivity activity) {
        callback = activity;
    }
    public interface OnMenuClickListener {
        void onMenuClick();
    }

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public PlanningFragment() {
        // Required empty public constructor
    }

    public static PlanningFragment newInstance() {
        Bundle args = new Bundle();
        PlanningFragment fragment = new PlanningFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // --------------------
    // ON CREATE VIEW
    // --------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Configuration
        this.configureRecyclerView();

        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_planning;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // - Create adapter
        this.planningAdapter = new PlanningAdapter(this.getContext());
        // - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.planningAdapter);
        // - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemClickSupport.addTo(recyclerView, R.layout.plannig_recycle_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    mainScreenViewModel.setCurrentMenu(planningAdapter.getItem(position));
                    callback.onMenuClick();
                });
    }

    @Override
    public void configureViewModel(){
        int todayDate = TimeAndDateUtils.formatDateToInt_yyyyMMdd(TimeAndDateUtils.getDateWithGapFromToday(0));
        mainScreenViewModel = ViewModelProviders.of(getActivity()).get(MainScreenViewModel.class);
        mainScreenViewModel.getMenuFrom2WeeksRange(todayDate).observe(this, this::updateMenu);
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateMenu(List<FoodMenu> foodMenus){
        planningAdapter.updateData(foodMenus);
    }
}
