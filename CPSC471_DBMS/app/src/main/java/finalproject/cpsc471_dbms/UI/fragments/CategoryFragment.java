package finalproject.cpsc471_dbms.UI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
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

/**
 * Created by wj-hong on 26/02/17.
 */

public class CategoryFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private FloatingActionButton addItemButton;
    private ImageButton settingsButton;
    private LinearLayout catSettings;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_tab, container, false);

        //code for this fragment goes here
        addItemButton = (FloatingActionButton) view.findViewById(R.id.addItemButton);
        settingsButton = (ImageButton) view.findViewById(R.id.settingsButton);
        catSettings = (LinearLayout) view.findViewById(R.id.catSettings);

        addItemButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);

        final EditText searchEditText = (EditText) view.findViewById(R.id.searchEditText);

        //------------- The following code is for the spinners in the cat settings. -------------
        final Spinner typeSpinner = (Spinner) view.findViewById(R.id.typeSpinner);
        final Spinner languageSpinner = (Spinner) view.findViewById(R.id.languageSpinner);

        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(getContext(), R.array.typeArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(getContext(), R.array.languageArray, android.R.layout.simple_spinner_item);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        languageSpinner.setAdapter(languageAdapter);

        typeSpinner.setOnItemSelectedListener(this);
        languageSpinner.setOnItemSelectedListener(this);

        //------------- The following code is for the actual items in the category listing. -------------
        String[] itemList = {"Title", "Author", "Genre"};
        ListAdapter categoryAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, itemList);
        ListView categoryList = (ListView) view.findViewById(R.id.categoryList);
        categoryList.setAdapter(categoryAdapter);

        categoryList.setOnItemClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.addItemButton) {
            startActivity(new Intent(getContext(), AddItemActivity.class));
        } else if (v.getId() == R.id.settingsButton) {
            if (catSettings.isShown()) {
                catSettings.setVisibility(View.GONE);
            } else {
                catSettings.setVisibility(View.VISIBLE);
            }
        }
    }

    //Spinner items
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
        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
