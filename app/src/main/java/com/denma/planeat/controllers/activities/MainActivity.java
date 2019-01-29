package com.denma.planeat.controllers.activities;


import android.os.Bundle;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_main;
    }
}
