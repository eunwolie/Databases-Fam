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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_tab, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.setOnClickListener(this);

        Item[] itemList = {new Item("cpl_logo.png", "Genre"), new Item("cpl_logo.png", "Author"), new Item("cpl_logo.png", "Availability"),
                new Item("cpl_logo.png", "Author"), new Item("cpl_logo.png", "Availability")};
        ListAdapter categoryAdapter = new DashAdapter(getContext(),  itemList);
        ListView categoryList = (ListView) view.findViewById(R.id.dashboardList);
        categoryList.setAdapter(categoryAdapter);

        categoryList.setOnItemClickListener(this);

        // Setting user views.
        if (MainActivity.user == MainActivity.NORMAL) {
            fab.setVisibility(GONE);
        } return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(getContext(), CreateEventActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getContext(), EventViewActivity.class));
    }
}
