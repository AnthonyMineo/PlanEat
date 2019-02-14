package com.denma.planeat.controllers.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseFragment;

import butterknife.BindView;

public class RecipeFragment extends BaseFragment {

    @BindView(R.id.recipe_web_view)
    WebView webView;


    public RecipeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);

        // Configuration
        this.configureWebView();

        return view;
    }

    @Override
    public int getFragmentLayout() {
        return R.layout.fragment_recipe;
    }

    private void configureWebView(){
        // - Configure WebView
       // String url = getIntent().getExtras().getString("url");
        //webView.loadUrl(url);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.setWebViewClient(new WebViewClient());
    }

}
