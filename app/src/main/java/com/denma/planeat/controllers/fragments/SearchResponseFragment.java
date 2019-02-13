package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.views.adapter.SearchResponseAdapter;

import butterknife.BindView;


public class SearchResponseFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_search_response_recycler_view)
    RecyclerView recyclerView;

    // FOR DATA
    private SearchResponseAdapter searchResponseAdapter;

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
        this.configureRecyclerView();

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

    // - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // - Create adapter
        this.searchResponseAdapter = new SearchResponseAdapter();
        // - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.searchResponseAdapter);
        // - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemClickSupport.addTo(recyclerView, R.layout.search_recycle_item)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                    }
                });
    }

    // --------------------
    // ACTIONS
    // --------------------

}
