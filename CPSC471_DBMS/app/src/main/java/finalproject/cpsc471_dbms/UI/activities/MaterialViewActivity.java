package finalproject.cpsc471_dbms.UI.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 27/03/17.
 */

/* This class represents the activity that starts when an item is selected from the materials available in the library. */
public class MaterialViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView matImage;
    private TextView matTitle, matGenre, matLang, matType, matFloor, matSec, matShelf, matAvail, matElec;
    private Button borButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_view);

        //Sets the toolbar -----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Item");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views -----------------
        matTitle = (TextView) findViewById(R.id.matTitle);
        matGenre = (TextView) findViewById(R.id.matGenre);
        matLang = (TextView) findViewById(R.id.matLang);
        matType = (TextView) findViewById(R.id.matType);
        matFloor = (TextView) findViewById(R.id.matFloor);
        matSec = (TextView) findViewById(R.id.matSec);
        matShelf = (TextView) findViewById(R.id.matShelf);
        matAvail = (TextView) findViewById(R.id.matAvail);
        matElec = (TextView) findViewById(R.id.matElec);

        matImage = (ImageView) findViewById(R.id.matImage);
        borButton = (Button) findViewById(R.id.borButton);

        //Sets the listener -----------------
        borButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.borButton) {
            //borrow the item for the current user
            finish();
        } else {
            onBackPressed();
        }
    }
}
