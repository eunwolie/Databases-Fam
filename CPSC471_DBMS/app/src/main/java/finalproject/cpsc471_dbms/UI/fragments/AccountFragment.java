package finalproject.cpsc471_dbms.UI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.activities.EditUserActivity;
import finalproject.cpsc471_dbms.UI.adapters.BookAdapter;
import finalproject.cpsc471_dbms.UI.custom.Item;

/**
 * Created by wj-hong on 26/02/17.
 */

public class AccountFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private Spinner sectionSpinner;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_tab, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        final ImageView accImage = (ImageView) view.findViewById(R.id.accImage);

        final TextView accName = (TextView) view.findViewById(R.id.accName);
        final TextView accID = (TextView) view.findViewById(R.id.accID);
        final TextView accEID = (TextView) view.findViewById(R.id.accEID);
        final TextView email = (TextView) view.findViewById(R.id.email);
        final TextView address = (TextView) view.findViewById(R.id.address);

        sectionSpinner = (Spinner) view.findViewById(R.id.sectionSpinner);
        ArrayAdapter<CharSequence> sectionAdapter = ArrayAdapter.createFromResource(getContext(), R.array.sectionArray, android.R.layout.simple_spinner_item);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sectionSpinner.setAdapter(sectionAdapter);
        sectionSpinner.setOnItemSelectedListener(this);

        Item[] itemList = {new Item("cpl_logo.png", "Genre"), new Item("cpl_logo.png", "Author"), new Item("cpl_logo.png", "Availability"),
                new Item("cpl_logo.png", "Author"), new Item("cpl_logo.png", "Availability")};
        ListAdapter bookAdapter = new BookAdapter(getContext(),  itemList);
        ListView sectionList = (ListView) view.findViewById(R.id.sectionList);
        sectionList.setAdapter(bookAdapter);

        sectionList.setOnItemClickListener(this);

        fab.setOnClickListener(this);

        return view;
    }

    //for the button
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), EditUserActivity.class));
    }

    //for the spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            //populate list view with checked out stuff
        } else if (position == 1) {
            //populate list view with on hold stuff
        } else if (position == 2) {
            //populate list view with over due stuff
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //for the list
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
