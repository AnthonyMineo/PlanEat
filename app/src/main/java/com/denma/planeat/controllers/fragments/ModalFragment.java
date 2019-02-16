package com.denma.planeat.controllers.fragments;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MenuViewModel;
import com.denma.planeat.models.local.Menu;
import com.denma.planeat.utils.TimeAndDateUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;

public class ModalFragment extends BottomSheetDialogFragment {

    // FOR DESIGN
    @BindView(R.id.radio_group_day_timing)
    RadioGroup dayTimingGroup;
    @BindView(R.id.eating_date_text_view)
    TextView eatingDate;
    @BindView(R.id.eating_date_button)
    ImageView eatingButton;

    // FOR DATA
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private MenuViewModel menuViewModel;
    private int day;
    private int month;
    private int year;
    private Calendar calendar;

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
        this.configureDagger();
        this.configureViewModel();
        this.configureDatePicker();
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure Dagger2
    private void configureDagger() {
        AndroidSupportInjection.inject(this);
    }

    private void configureViewModel(){
        menuViewModel = ViewModelProviders.of(this, viewModelFactory).get(MenuViewModel.class);
        menuViewModel.getCurrentMenu().observe(this, this::updateUI);
    }

    private void configureDatePicker(){
        calendar = Calendar.getInstance();
        day = calendar.get(Calendar.DAY_OF_MONTH);
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);

        eatingButton.setOnClickListener(view -> DateDialog());
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void updateUI(Menu menu){
        String dateToDisplay = TimeAndDateUtils.formatIntDateToStringToShow(menu.getEatingDate());
        this.eatingDate.setText(dateToDisplay);
    }

    private void DateDialog(){
        // - Allow the user to pick the date he want from the datePicker widget
        DatePickerDialog.OnDateSetListener listener = (datePicker, year, month, day) -> {
            String sDay = String.valueOf(day);
            String sMonth = String.valueOf(month+1);
            String sYear = String.valueOf(year);

            if (day < 10)
                sDay = "0" + sDay;
            if (month < 10)
                sMonth = "0" + sMonth;

            eatingDate.setText(sDay + "/" + sMonth + "/" + sYear);
        };
        DatePickerDialog dpDialog = new DatePickerDialog(getActivity(), R.style.DatePickerTheme, listener, year, month, day);
        dpDialog.show();
    }

}
