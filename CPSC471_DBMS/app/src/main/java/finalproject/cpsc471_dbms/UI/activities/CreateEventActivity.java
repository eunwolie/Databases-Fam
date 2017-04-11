package finalproject.cpsc471_dbms.UI.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 10/04/17.
 */

public class CreateEventActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText eventNameET;
    private EditText sponsorET;
    private EditText employeeET;
    private EditText dateET;
    private EditText timeET;

    private Button addEventButton;
    private ImageButton eventImage;
    private Uri selectedImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Create Event");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        addEventButton = (Button) findViewById(R.id.addEventButton);
        eventImage = (ImageButton) findViewById(R.id.eventImage);

        addEventButton.setOnClickListener(this);
        eventImage.setOnClickListener(this);

        eventNameET = (EditText) findViewById(R.id.eventNameET);
        sponsorET = (EditText) findViewById(R.id.sponsorET);
        employeeET = (EditText) findViewById(R.id.employeeET);
        dateET = (EditText) findViewById(R.id.dateET);
        timeET = (EditText) findViewById(R.id.timeET);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.addEventButton) {
            submit();
        } else if (id == R.id.eventImage) {
            selectImage();
        } else {
            onBackPressed();
        }
    }

    /* Submits the new event to the database
     * Check key attributes not empty.
     * If they aren't empty, update the database. */
    private void submit() {
        if (eventNameET.getText().toString().equals("")) {
            Toast.makeText(this, "Event name cannot be empty!", Toast.LENGTH_SHORT).show();
        } else if (employeeET.getText().toString().equals("")) {
            Toast.makeText(this, "Employee ID cannot be empty!", Toast.LENGTH_SHORT).show();
        } else{
            try {
                if (selectedImage != null) {
                    byte[] inputData = getBytes(getContentResolver().openInputStream(selectedImage));
                    //add inputData to database
                }

                //set event name and employee id
                //set sponsor id to given
                //set date and time

                //end activity
                finish();
            } catch (Exception e) {
                //ignore
            }
        }
    }

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
                eventImage.setImageURI(selectedImage);
            }
        }
    }
}
