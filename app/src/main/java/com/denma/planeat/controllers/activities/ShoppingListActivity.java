package com.denma.planeat.controllers.activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import com.denma.planeat.R;
import com.denma.planeat.controllers.BaseActivity;
import com.denma.planeat.utils.StorageHelper;

import java.util.List;

import butterknife.BindView;

public class ShoppingListActivity extends BaseActivity {

    // FOR DESIGN
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.activity_shopping_text_view)
    TextView shoppingText;

    // FOR DATA
    private String fileName;

    // --------------------
    // ON CREATE
    // --------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.configureToolBar();
        this.showShoppingList();

    }

    // --------------------
    // GETTERS
    // --------------------

    @Override
    protected int getActivityLayout() {
        return R.layout.activity_shopping_list;
    }

    // --------------------
    // CONFIGURATIONS
    // --------------------

    // - Configure Toolbar
    private void configureToolBar() {
        fileName = getIntent().getStringExtra("fileName");
        this.toolbar.setTitle(fileName);
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    // --------------------
    // ACTIONS
    // --------------------

    private void showShoppingList(){
        List<String> shoppingList = StorageHelper.getShoppingListFromStorage(getFilesDir(), this, fileName);
        String listToDiplay = null;
        for (String lign : shoppingList){
            if(listToDiplay == null){
                listToDiplay = "- " + lign + "\n";
            }else{
                listToDiplay += "- " + lign + "\n";
            }
        }
        this.shoppingText.setMovementMethod(new ScrollingMovementMethod());
        this.shoppingText.setText(listToDiplay);
    }

}
