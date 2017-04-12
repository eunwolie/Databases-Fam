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

public class EventViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView eventPic;
    private TextView eventTitle, eventTime, eventDate, eventSponsor, eventHost, eventDescription;
    private Button joinEventButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Event");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views.
        eventTitle = (TextView) findViewById(R.id.eventTitle);
        eventTime = (TextView) findViewById(R.id.eventTime);
        eventDate = (TextView) findViewById(R.id.eventDate);
        eventSponsor = (TextView) findViewById(R.id.eventSponsor);
        eventHost = (TextView) findViewById(R.id.eventHost);
        eventDescription = (TextView) findViewById(R.id.eventDescription);

        eventPic = (ImageView) findViewById(R.id.eventPic);

        joinEventButton = (Button) findViewById(R.id.joinEventButton);

        //Sets the listeners
        joinEventButton.setOnClickListener(this);

        //code for event viewing info stuff here
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar) {
            onBackPressed();
        } else if (v.getId() == R.id.joinEventButton) {
            //add user to event
            finish();
        } else {
            onBackPressed();
        }
    }
}
