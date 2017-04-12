package finalproject.cpsc471_dbms.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import finalproject.cpsc471_dbms.Definitions.EventAttendanceDef;
import finalproject.cpsc471_dbms.Facades.EventFacade;
import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 27/03/17.
 */

public class EventViewActivity extends AppCompatActivity implements View.OnClickListener {

    private EventFacade eventFacade;

    private int startTime, dateof, workid;

    private ImageView eventPic;
    private TextView eventTitle, eventTime, eventDate, eventSponsor, eventHost, eventDescription;
    private Button joinEventButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_view);

        eventFacade = new EventFacade(this);

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

        //Sets TextView text
        Intent intent = getIntent();
        eventTitle.setText(intent.getStringExtra("EVENT_TITLE"));
        eventTime.setText(intent.getStringExtra("EVENT_TIME"));
        eventDate.setText(intent.getStringExtra("EVENT_DATE"));
        eventSponsor.setText(intent.getStringExtra("EVENT_SPONSOR"));
        eventHost.setText(intent.getStringExtra("EVENT_HOST"));
        eventDescription.setText(intent.getStringExtra("EVENT_DESC"));

        startTime = intent.getIntExtra("EVENT_START", 0);
        workid = intent.getIntExtra("EVENT_HOST_ID", 0);
        dateof = intent.getIntExtra("EVENT_DATE_INT", 0);

        //Sets the visibility.
        if (MainActivity.user != MainActivity.NORMAL) {
            joinEventButton.setVisibility(View.GONE);
        } else {
            joinEventButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        eventFacade.close();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.toolbar) {
            onBackPressed();
        } else if (v.getId() == R.id.joinEventButton) {
            eventFacade.attendEvent(new EventAttendanceDef(MainActivity.userId, startTime, dateof, workid));
            finish();
        } else {
            onBackPressed();
        }
    }
}
