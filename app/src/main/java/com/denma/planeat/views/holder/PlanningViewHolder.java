package com.denma.planeat.views.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denma.planeat.R;
import com.denma.planeat.models.local.Menu;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanningViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.planning_icon_day_name)
    TextView day_name;
    @BindView(R.id.planning_icon_day_number)
    TextView day_number;
    @BindView(R.id.planning_icon_breakfast)
    ImageView breakfast;
    @BindView(R.id.planning_icon_after_morning)
    ImageView after_morning;
    @BindView(R.id.planning_icon_lunch)
    ImageView lunch;
    @BindView(R.id.planning_icon_afternoon)
    ImageView afternoon;
    @BindView(R.id.planning_icon_dinner)
    ImageView dinner;

    public PlanningViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithRecipe(Menu menu){

    }
}
