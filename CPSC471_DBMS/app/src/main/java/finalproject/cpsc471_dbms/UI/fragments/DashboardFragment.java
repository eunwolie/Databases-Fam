package finalproject.cpsc471_dbms.UI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import finalproject.cpsc471_dbms.Definitions.EventDef;
import finalproject.cpsc471_dbms.Facades.EventFacade;
import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.activities.CreateEventActivity;
import finalproject.cpsc471_dbms.UI.activities.EventViewActivity;
import finalproject.cpsc471_dbms.UI.activities.MainActivity;
import finalproject.cpsc471_dbms.UI.adapters.DashAdapter;
import finalproject.cpsc471_dbms.UI.custom.Item;

import static android.view.View.GONE;

/**
 * Created by wj-hong on 24/03/17.
 */

public class DashboardFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EventFacade eventFacade;

    private FloatingActionButton fab;
    private ListView dashboardList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_tab, container, false);

        eventFacade = new EventFacade(getContext());

        //----------------- Sets the views -----------------
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        dashboardList = (ListView) view.findViewById(R.id.dashboardList);

        //------------- The following code is for the actual items in the category listing. -------------
        EventDef[] itemList = eventFacade.getAllEvents().toArray(new EventDef[eventFacade.getAllEvents().size()]);

        ListAdapter categoryAdapter = new DashAdapter(getContext(),  itemList);
        dashboardList.setAdapter(categoryAdapter);

        //----------------- Sets the listeners -----------------
        fab.setOnClickListener(this);
        dashboardList.setOnItemClickListener(this);

        // Setting user views.
        if (MainActivity.user == MainActivity.NORMAL) {
            fab.setVisibility(GONE);
        } return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        eventFacade.close();
    }

    //-------------------------- LISTENERS --------------------------
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(getContext(), CreateEventActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getContext(), EventViewActivity.class);
        EventDef item = (EventDef) parent.getItemAtPosition(position);
        intent.putExtra("EVENT_TITLE", item.getTitle());
        intent.putExtra("EVENT_START", item.getStartTime());
        intent.putExtra("EVENT_TIME", item.getStartTime() + " - " + item.getEndTime());
        intent.putExtra("EVENT_DATE", item.getDate() + "");
        intent.putExtra("EVENT_SPONSOR", eventFacade.getEventSponsor(item));
        intent.putExtra("EVENT_HOST_ID", item.getWorkID());
        //get host name
        intent.putExtra("EVENT_DATE_INT", item.getDate());
        intent.putExtra("EVENT_DESC", item.getDescription());

        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        startActivity(intent);
    }
}
