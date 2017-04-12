package finalproject.cpsc471_dbms.UI.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 27/03/17.
 */

public class AddItemActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private ImageButton itemImage;
    private EditText titleET, isbnET, authorET, genreET, publisherET, yrPubET;
    private Spinner typeSpinner, languageSpinner;
    private Button addItemButton;
    private Uri selectedImage;
    private String type, language;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        //Sets the toolbar -----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Add Item");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views -----------------
        titleET = (EditText) findViewById(R.id.titleET);
        isbnET = (EditText) findViewById(R.id.isbnET);
        authorET = (EditText) findViewById(R.id.authorET);
        genreET = (EditText) findViewById(R.id.genreET);
        publisherET = (EditText) findViewById(R.id.publisherET);
        yrPubET = (EditText) findViewById(R.id.yrPubET);

        addItemButton = (Button) findViewById(R.id.addItemButton);
        itemImage = (ImageButton) findViewById(R.id.itemImage);

        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);
        languageSpinner = (Spinner) findViewById(R.id.languageSpinner);

        //Sets adapters -----------------
        ArrayAdapter<CharSequence> typeAdapter = ArrayAdapter.createFromResource(this, R.array.typeArray, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this, R.array.languageArray, android.R.layout.simple_spinner_item);

        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        typeSpinner.setAdapter(typeAdapter);
        languageSpinner.setAdapter(languageAdapter);

        //Sets the listeners -----------------
        addItemButton.setOnClickListener(this);
        itemImage.setOnClickListener(this);
        typeSpinner.setOnItemSelectedListener(this);
        languageSpinner.setOnItemSelectedListener(this);
    }

    //---------------------------------- LISTENERS ----------------------------------

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addItemButton) {
            submit();
        } else if (id == R.id.itemImage) {
            selectImage();
        } else {
            onBackPressed();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (view.getId() == R.id.typeSpinner) {
            Toast.makeText(parent.getContext(), parent.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //-----------------------------------------------------------------------------

    //---------------------------------- METHODS ----------------------------------

    //Submits the new event to the database
    private void submit() {
        //adds the new event to the database
        //remember to ignore fields that are empty
        if (selectedImage == null) {
            //set default image
        } else {
            try {
                byte[] inputData = getBytes(getContentResolver().openInputStream(selectedImage));
                //add inputData to database
            } catch (Exception e) {
                //ignore
            }
        }
    }

    //--------------------------------------------------------------------------------

    //---------------------------------- IMAGE CODE ----------------------------------
    private byte[] getBytes(InputStream streamIn) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int byt = 0;
        while ((byt = streamIn.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, byt);
        } return byteBuffer.toByteArray();
    }

    private void selectImage() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, 1); // starts the gallery activity.
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {

            if (resultCode == RESULT_OK) {
                selectedImage= data.getData();
                itemImage.setImageURI(selectedImage);
            }
        }
    }

    //--------------------------------------------------------------------------------
}
