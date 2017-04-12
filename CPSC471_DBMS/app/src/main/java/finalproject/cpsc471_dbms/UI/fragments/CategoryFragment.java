package finalproject.cpsc471_dbms.UI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.activities.AddItemActivity;
import finalproject.cpsc471_dbms.UI.activities.DonateBooksActivity;
import finalproject.cpsc471_dbms.UI.activities.MainActivity;
import finalproject.cpsc471_dbms.UI.activities.MaterialViewActivity;
import finalproject.cpsc471_dbms.UI.activities.RateActivity;
import finalproject.cpsc471_dbms.UI.adapters.BookAdapter;
import finalproject.cpsc471_dbms.UI.custom.Item;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by wj-hong on 26/02/17.
 */

public class CategoryFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private EditText searchEditText;
    private FloatingActionButton addItemButton, rateButton, charityButton;
    private ImageButton settingsButton;
    private Spinner typeSpinner, languageSpinner;
    private LinearLayout catSettings;
    private ListView categoryList;
    private ArrayAdapter categoryAdapter;
    private BookAdapter bookAdapter;

    private boolean newSearch = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_tab, container, false);

        //----------------- Sets the views -----------------
        searchEditText = (EditText) view.findViewById(R.id.searchEditText);

        addItemButton = (FloatingActionButton) view.findViewById(R.id.addItemButton);
        rateButton = (FloatingActionButton) view.findViewById(R.id.rateButton);
        charityButton = (FloatingActionButton) view.findViewById(R.id.charityButton);

        settingsButton = (ImageButton) view.findViewById(R.id.settingsButton);
        catSettings = (LinearLayout) view.findViewById(R.id.catSettings);

        typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        languageSpinner = (Spinner) view.findViewById(R.id.languageSpinner);

        categoryList = (ListView) view.findViewById(R.id.categoryList);

        //------------- The following code is for the spinners in the cat settings. -------------
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.typeArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(getContext(), R.array.languageArray, android.R.layout.simple_spinner_item);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        languageSpinner.setAdapter(languageAdapter);

        //------------- The following code is for the actual items in the category listing. -------------
        String[] itemList = {"Title", "Author", "Genre"};   //test---------------------------------------------

        ListAdapter categoryAdapter = new ArrayAdapter<>(getContext(), R.layout.better_generic_layout, itemList);
        categoryList.setAdapter(categoryAdapter);

        //----------------- Sets the listeners -----------------
        addItemButton.setOnClickListener(this);
        rateButton.setOnClickListener(this);
        charityButton.setOnClickListener(this);

        settingsButton.setOnClickListener(this);

        typeSpinner.setOnItemSelectedListener(this);
        languageSpinner.setOnItemSelectedListener(this);

        categoryList.setOnItemClickListener(this);

        //Sets the user views.
        switch (MainActivity.user) {

            case MainActivity.NORMAL:       addItemButton.setVisibility(GONE);
                                            rateButton.setVisibility(VISIBLE);
                                            charityButton.setVisibility(GONE);
                                            break;
            case MainActivity.LIBRARIAN:    addItemButton.setVisibility(VISIBLE);
                                            rateButton.setVisibility(GONE);
                                            charityButton.setVisibility(GONE);
                                            break;
            case MainActivity.SPONSOR:      addItemButton.setVisibility(GONE);
                                            rateButton.setVisibility(GONE);
                                            charityButton.setVisibility(VISIBLE);
                                            break;

            default:    addItemButton.setVisibility(VISIBLE);
                        rateButton.setVisibility(GONE);
                        charityButton.setVisibility(GONE);
                        break;
        } return view;
    }

    //---------------------------------- METHODS ----------------------------------




    //---------------------------------- LISTENERS ----------------------------------

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addItemButton) {
            startActivity(new Intent(getContext(), AddItemActivity.class));
        } else if (id == R.id.settingsButton) {
            if (catSettings.isShown()) {
                catSettings.setVisibility(GONE);
            } else {
                catSettings.setVisibility(VISIBLE);
            }
        } else if (id == R.id.rateButton) {
            startActivity(new Intent(getContext(), RateActivity.class));
        } else if (id == R.id.charityButton) {
            startActivity(new Intent(getContext(), DonateBooksActivity.class));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.typeSpinner) {
            //filter by type
            Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.languageSpinner) {
            //filter by language
            Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //list view thing
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        String[] itemList = {"Title", "Author", "Genre"};   //test---------------------------------------------
        String chosen = parent.getItemAtPosition(position).toString();

        //first checks if it's author and genre so it can create the right adapter or whatever
        //second makes it a book adapter, so it's books
        //third is a book press so it makes it like start a book view
        //you can't go backwards in search feelsbad
        if ((chosen.equals("Author")) || (chosen.equals("Genre"))) {
            //get refreshed data in string and set to itemlist or whatever it is
            categoryAdapter.notifyDataSetChanged();
            categoryList.setAdapter(categoryAdapter);
        } else if (!newSearch) {
            //get new data from db and put into the new adapter
            Item[] ye = {new Item("cpl_logo.png", "ye fam")};  //test---------------
            bookAdapter = new BookAdapter(getContext(), ye);
            categoryList.setAdapter(bookAdapter);
            newSearch = true;
        } else {
            //first identify the book with primary keys or whatever
            startActivity(new Intent(getContext(), MaterialViewActivity.class));
            //bundle and pass
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }
}
