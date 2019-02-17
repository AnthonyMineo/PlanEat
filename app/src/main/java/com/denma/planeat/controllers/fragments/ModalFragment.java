package com.denma.planeat.controllers.fragments;

import android.app.AlertDialog;
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
import android.widget.Toast;

import com.denma.planeat.R;
import com.denma.planeat.arch.viewmodels.MenuViewModel;
import com.denma.planeat.arch.viewmodels.ResponseViewModel;
import com.denma.planeat.models.local.Meal;
import com.denma.planeat.models.local.Menu;
import com.denma.planeat.models.remote.Recipe;
import com.denma.planeat.utils.TimeAndDateUtils;

import java.util.Calendar;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private ResponseViewModel responseViewModel;
    private int day;
    private int month;
    private int year;
    private Calendar calendar;
    private boolean updated = false;

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
        responseViewModel = ViewModelProviders.of(this, viewModelFactory).get(ResponseViewModel.class);
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

    private void updateUI(Menu currentMenu){
        String dateToDisplay = TimeAndDateUtils.formatIntDateToStringToShow(currentMenu.getEatingDate());
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

            String text = sDay + "/" + sMonth + "/" + sYear;
            eatingDate.setText(text);
        };
        DatePickerDialog dpDialog = new DatePickerDialog(getActivity(), R.style.DatePickerTheme, listener, year, month, day);
        // Adjust date range allow to pick for the user
        DatePicker datePicker = dpDialog.getDatePicker();
        datePicker.setMinDate(TimeAndDateUtils.getDateWithGapFromToday(0).getTime());
        datePicker.setMaxDate(TimeAndDateUtils.getDateWithGapFromToday(14).getTime());

        dpDialog.show();
    }

    @OnClick(R.id.save_meal_button)
    public void saveMeal(){
        int dayTimingInt = dayTimingAttribution();
        if(dayTimingInt == 0){
            Toast.makeText(this.getActivity(), getResources().getText(R.string.error_day_timing_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if(eatingDate.getText().toString().isEmpty()){
            Toast.makeText(this.getActivity(), getResources().getText(R.string.error_eating_date_empty), Toast.LENGTH_SHORT).show();
            return;
        }

        // Create the meal from user choices
        Recipe recipe = responseViewModel.getCurrentRecipe().getValue();
        Meal mealToSave = new Meal();
        mealToSave.setDayTiming(dayTimingInt);
        mealToSave.setRecipe(recipe);

        // Add it to the menu choosed by user
        int eatingDateInt = TimeAndDateUtils.formatStringDateToShowToIntToSave(eatingDate.getText().toString());
        menuViewModel.getMenuByDate(eatingDateInt).observe(this, menuToUpdate -> updateMenu(menuToUpdate, mealToSave, eatingDateInt));
    }

    private void updateMenu(Menu menuToUpdate, Meal mealToSave, int eatingDateInt){
        // Necessary to avoid the update to trigger the observer
        if(!this.updated){
            int existed = 0;
            int indexOfExisted = -1;

            // Check if a meal with same day timing already existed in the menu
            for(Meal meal : menuToUpdate.getMealList()){
                if(meal.getDayTiming() == mealToSave.getDayTiming()){
                    existed++;
                    indexOfExisted = menuToUpdate.getMealList().indexOf(meal);
                }
            }
            if(existed > 0){
                // ask if user want to overwrite it
                showDialog(menuToUpdate, indexOfExisted, mealToSave);
            } else {
                updateAndClose(menuToUpdate, mealToSave);
            }
        }
    }

    private int dayTimingAttribution(){
        int radioButtonId = dayTimingGroup.getCheckedRadioButtonId();
        int dayIndice;
        switch(radioButtonId){
            case R.id.radio_breakfast:
                dayIndice = 1;
                break;
            case R.id.radio_after_morning:
                dayIndice = 2;
                break;
            case R.id.radio_lunch:
                dayIndice = 3;
                break;
            case R.id.radio_afternoon:
                dayIndice = 4;
                break;
            case R.id.radio_dinner:
                dayIndice = 5;
                break;
            default:
                // no selection has be done
                dayIndice = 0;
                break;
        }
        return dayIndice;
    }

    private void showDialog(Menu menuToUpdate, int indexOfExisted, Meal mealToSave){
        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
        builder.setTitle(getResources().getString(R.string.overwrite_dialog_title))
                .setMessage(getResources().getString(R.string.overwrite_dialog_text))
                .setCancelable(false)
                .setPositiveButton("Yes", (dialog, id) -> {
                    menuToUpdate.getMealList().remove(indexOfExisted);
                    updateAndClose(menuToUpdate, mealToSave);
                })
                .setNegativeButton("No", (dialog, id) -> {
                    dialog.cancel();
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private void updateAndClose(Menu menuToUpdate, Meal mealToSave){
        menuToUpdate.getMealList().add(mealToSave);
        menuViewModel.updateMenu(menuToUpdate);
        this.updated = true;
        getActivity().finish();
    }

}
