package finalproject.cpsc471_dbms.UI.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 27/03/17.
 */

/* This class represents the activity that starts when an item is selected from the materials available in the library. */
public class MaterialViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_view);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("ITEM");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //code for this activity here
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar) {
            onBackPressed();
        } else {
            onBackPressed();
        }
    }
}
