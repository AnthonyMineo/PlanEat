package com.denma.planeat.views.holder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denma.planeat.R;
import com.denma.planeat.models.remote.Recipe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchResponseViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.search_response_image)
    ImageView recipeImage;
    @BindView(R.id.search_response_recipe_name)
    TextView recipeName;
    @BindView(R.id.search_response_cooking_time)
    TextView recipeCookingTime;

    public SearchResponseViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithRecipe(Recipe recipe, Context context){

    }
}
