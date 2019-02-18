package com.denma.planeat.views.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.denma.planeat.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShoppingListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.shopping_list_label)
    TextView shoppingLabel;

    public ShoppingListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithFileName(String fileName){
        fileName = "- " + fileName;
        this.shoppingLabel.setText(fileName);
    }
}
