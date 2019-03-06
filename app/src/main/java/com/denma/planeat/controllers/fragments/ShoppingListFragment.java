package com.denma.planeat.controllers.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;
import com.denma.planeat.controllers.activities.ShoppingListActivity;
import com.denma.planeat.utils.ItemClickSupport;
import com.denma.planeat.views.adapter.ShoppingListAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class ShoppingListFragment extends BaseFragment {

    // FOR DESIGN
    @BindView(R.id.fragment_shopping_list_recycler_view)
    RecyclerView recyclerView;

    // FOR DATA
    private ShoppingListAdapter shoppingListAdapter;

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public static ShoppingListFragment newInstance() {
        Bundle args = new Bundle();
        ShoppingListFragment fragment = new ShoppingListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    // --------------------
    // LIFE CYCLE
    // --------------------

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Configuration
        this.configureRecyclerView();
        this.getFileNameList();

        return view;
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_shopping_list;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure RecyclerView, Adapter, LayoutManager & glue it together
    private void configureRecyclerView() {
        // - Create adapter
        this.shoppingListAdapter = new ShoppingListAdapter(this.getContext());
        // - Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.shoppingListAdapter);
        // - Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ItemClickSupport.addTo(recyclerView, R.layout.shopping_recycle_item)
                .setOnItemClickListener((recyclerView, position, v) -> {
                    // launch an activity to show shopping list detail
                    startShoppingListActivity(position);
                });
    }

    private void getFileNameList(){
        List<String> fileNameList = new ArrayList<>();
        File folder = new File(getActivity().getFilesDir(), "ShoppingList");
        File[] listFile = folder.listFiles();
        if(listFile != null){
            for(File file : listFile){
                if(file.isFile()){
                    fileNameList.add(file.getName());
                }
            }
            this.shoppingListAdapter.updateData(fileNameList);
        }
    }

    @Override
    public void configureViewModel() { }

    // --------------------
    // NAVIGATION
    // --------------------

    private void startShoppingListActivity(int position){
        Intent intent = new Intent(getActivity(), ShoppingListActivity.class);
        intent.putExtra("fileName", shoppingListAdapter.getItem(position));
        startActivity(intent);
    }

}
