package com.denma.planeat.views.holder;

import android.view.View;
import android.widget.TextView;

import com.denma.planeat.R;

import butterknife.BindView;

public class ShoppingListViewHolder extends GenericViewHolder<String> {

    @BindView(R.id.shopping_list_label)
    TextView shoppingLabel;

    public ShoppingListViewHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void updateWithItem(String fileName) {
        fileName = "- " + fileName;
        this.shoppingLabel.setText(fileName);
    }

}
