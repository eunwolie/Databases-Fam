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

import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions._BorrowedDef;
import finalproject.cpsc471_dbms.Facades.AccountFacade;
import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.activities.EditUserActivity;
import finalproject.cpsc471_dbms.UI.activities.MainActivity;
import finalproject.cpsc471_dbms.UI.adapters.BookAdapter;
import finalproject.cpsc471_dbms.UI.custom.Item;

/**
 * Created by wj-hong on 26/02/17.
 */

public class AccountFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private AccountFacade accountFacade;

    private FloatingActionButton fab;
    private ImageView accImage;
    private TextView accName, accID, accEID, email, address;
    private Spinner sectionSpinner;
    private ListView sectionList;
    private BookAdapter bookAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_tab, container, false);

        accountFacade = new AccountFacade(getContext(), MainActivity.userId);

        //----------------- Sets the views -----------------
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        accImage = (ImageView) view.findViewById(R.id.accImage);

        accName = (TextView) view.findViewById(R.id.accName);
        accID = (TextView) view.findViewById(R.id.accID);
        accEID = (TextView) view.findViewById(R.id.accEID);
        email = (TextView) view.findViewById(R.id.email);
        address = (TextView) view.findViewById(R.id.address);

        sectionSpinner = (Spinner) view.findViewById(R.id.sectionSpinner);
        sectionList = (ListView) view.findViewById(R.id.sectionList);

        //----------------- Sets the spinners -----------------
        ArrayAdapter<CharSequence> sectionAdapter = ArrayAdapter.createFromResource(getContext(), R.array.sectionArray, android.R.layout.simple_spinner_item);
        sectionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sectionSpinner.setAdapter(sectionAdapter);

        //------------- The following code is for the actual items in the category listing. -------------
        _BorrowedDef[] itemList = accountFacade.getBorrowedMaterial().toArray(new _BorrowedDef[accountFacade.getBorrowedMaterial().size()]);

        bookAdapter = new BookAdapter(getContext(), itemList);
        sectionList.setAdapter(bookAdapter);

        //----------------- Sets the listeners -----------------
        fab.setOnClickListener(this);
        sectionSpinner.setOnItemSelectedListener(this);
        sectionList.setOnItemClickListener(this);

        return view;
    }

    //---------------------------------- LISTENERS ----------------------------------
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getContext(), EditUserActivity.class));
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            //checked out stuff from database
        } else if (position == 1) {
            //on hold stuff from database
        } else if (position == 2) {
            //over due stuff from database
        } bookAdapter.notifyDataSetChanged();
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
