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

public class EventViewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("EVENT");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //code for event viewing info stuff here
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
