package finalproject.cpsc471_dbms;

import android.os.Bundle;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wj-hong on 26/02/17.
 */

public class CategoryFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_tab, container, false);

        //code for this fragment goes here
        final EditText searchEditText = (EditText) view.findViewById(R.id.searchEditText);
        final ImageButton settingsButton = (ImageButton) view.findViewById(R.id.settingsButton);
        final LinearLayout catSettings = (LinearLayout) view.findViewById(R.id.catSettings);
        /* final Spinner languageSpinner = (Spinner) view.findViewById(R.id.languageSpinner);

        List<String> languages = new ArrayList<>();
        languages.add("English");
        languages.add("French");
        languages.add("Spanish");

        ArrayAdapter<String> languageAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, languages);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(languageAdapter);

        languageSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lang = parent.getItemAtPosition(position).toString();
                Toast.makeText(getContext(), "Language: " + lang, Toast.LENGTH_SHORT).show();
            }
        }); */

        //do stuff with the searchedittext, settingsButton and catsettings

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (catSettings.isShown()) {
                    catSettings.setVisibility(View.GONE);
                } else {
                    catSettings.setVisibility(View.VISIBLE);
                }
            }
        });

        Item[] itemList = {new Item("cpl_logo.png", "Genre"), new Item("cpl_logo.png", "Author"), new Item("cpl_logo.png", "Availability")};
        ListAdapter categoryAdapter = new ItemRowAdapter(this.getContext(), itemList);
        ListView categoryList = (ListView) view.findViewById(R.id.categoryList);
        categoryList.setAdapter(categoryAdapter);

        categoryList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String itemDes = parent.getItemAtPosition(position).toString();
                        Toast.makeText(parent.getContext(), itemDes, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        return view;
    }
}
