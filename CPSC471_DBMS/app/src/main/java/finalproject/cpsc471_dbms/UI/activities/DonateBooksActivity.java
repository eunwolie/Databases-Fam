package finalproject.cpsc471_dbms.UI.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 11/04/17.
 */

public class DonateBooksActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText donateCount;
    private Button donateButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_books);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Donate");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views
        donateCount = (EditText) findViewById(R.id.donateCount);
        donateButton = (Button) findViewById(R.id.donateButton);

        donateButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.donateButton) {
            //submit donation
        } else {
            onBackPressed();
        }
    }
}
