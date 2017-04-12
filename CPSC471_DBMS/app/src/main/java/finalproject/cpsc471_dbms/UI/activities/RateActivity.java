package finalproject.cpsc471_dbms.UI.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.adapters.LibAdapter;

/**
 * Created by wj-hong on 11/04/17.
 */

public class RateActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_lib);

        //Sets the toolbar -----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Favourite Librarians!");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Sets the views -----------------
        ListView libList = (ListView) findViewById(R.id.librarianList);

        //Sets the adapter -----------------
        String[] itemList = {"Title", "Author", "Genre", "Title", "Author", "Genre", "Title", "Author", "Genre"}; //test
        ListAdapter libAdapter = new LibAdapter(this, itemList);
        libList.setAdapter(libAdapter);

        //Sets the listeners -----------------
        libList.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
    }
}
