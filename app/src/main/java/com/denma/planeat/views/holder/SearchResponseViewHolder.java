package com.denma.planeat.views.holder;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.denma.planeat.R;
import com.denma.planeat.models.remote.Recipe;

import butterknife.BindView;

public class SearchResponseViewHolder extends GenericViewHolder<Recipe> {

    @BindView(R.id.search_response_image)
    ImageView recipeImage;
    @BindView(R.id.search_response_recipe_name)
    TextView recipeName;

    public SearchResponseViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithItem(Recipe item) {
        try{
            Glide.with(getContext()).load(item.getImage()).into(recipeImage);
        } catch (Exception e){
            e.printStackTrace();
        }
        recipeName.setText(item.getLabel());
    }

}
