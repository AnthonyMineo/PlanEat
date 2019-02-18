package com.denma.planeat.controllers.fragments;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.ResponseViewModel;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.SearchActivity;
import com.denma.planeat.models.remote.Hit;
import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.models.remote.Response;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.views.adapter.SearchResponseAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;


public class SearchResponseFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.error_text_view)
    TextView errorText;
    @BindView(R.id.fragment_search_response_recycler_view)
    RecyclerView recyclerView;

    // FOR DATA
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private ResponseViewModel responseViewModel;
    private SearchResponseAdapter searchResponseAdapter;

    public OnRecipeClickListener callback;
    public void setOnRecipeClickListener(SearchActivity activity) {
        callback = activity;
    }
    public interface OnRecipeClickListener {
        void onRecipeClick();
    }

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public SearchResponseFragment() {
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
        this.configureRecyclerView();
        this.configureViewModel();

        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_search_response;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure Dagger2
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    // - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // - Create adapter
        this.searchResponseAdapter = new SearchResponseAdapter();
        // - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.searchResponseAdapter);
        // - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemClickSupport.addTo(recyclerView, R.layout.search_recycle_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    // update selected recipe on view model
                    responseViewModel.setCurrentRecipe(searchResponseAdapter.getRecipe(position));
                    callback.onRecipeClick();
                });
    }

    private void configureViewModel(){
        responseViewModel = ViewModelProviders.of(this, viewModelFactory).get(ResponseViewModel.class);
        responseViewModel.getResponse().observe(this, this::updateUI);
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateUI(Response response){
        List<Recipe> recipeList = new ArrayList<>();
        if(response.getHits().size() != 0){
            this.errorText.setVisibility(View.GONE);
            for(Hit hit : response.getHits()){
                recipeList.add(hit.getRecipe());
            }
            this.recyclerView.setVisibility(View.VISIBLE);
            searchResponseAdapter.updateData(recipeList);
        } else {
            this.errorText.setVisibility(View.VISIBLE);
        }
    }

}
