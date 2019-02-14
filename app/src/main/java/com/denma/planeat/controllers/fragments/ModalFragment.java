package com.denma.planeat.controllers.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.denma.planeat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ModalFragment extends BottomSheetDialogFragment {

    // FOR DESIGN
    @BindView(R.id.radio_group_day_timing)
    RadioGroup dayTimingGroup;
    @BindView(R.id.eating_date_text_view)
    TextView eatingDate;
    @BindView(R.id.eating_date_button)
    ImageView eatingButton;

    // FOR DATA

    // --------------------
    // CONSTRUCTORS
    // --------------------

    public static ModalFragment newInstance(){
        ModalFragment bottomSheetFragment = new ModalFragment();
        Bundle bundle = new Bundle();
        bottomSheetFragment.setArguments(bundle);
        return bottomSheetFragment ;
    }

    // --------------------
    // ON CREATE VIEW
    // --------------------

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modal_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Configuration
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

}
