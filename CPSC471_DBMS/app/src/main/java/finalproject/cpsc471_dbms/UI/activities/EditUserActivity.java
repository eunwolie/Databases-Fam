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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 09/04/17.
 */

public class EditUserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton accImage;
    private TextView accName, accID, accEID, email, address;
    private EditText fnameET, lnameET, passwordET, emailET, addressET, phoneNumberET;

    private Button submitButton;
    private Uri selectedImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        //Sets the toolbar -----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Edit Account");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views -----------------
        fnameET = (EditText) findViewById(R.id.fnameET);
        lnameET = (EditText) findViewById(R.id.lnameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        emailET = (EditText) findViewById(R.id.emailET);
        addressET = (EditText) findViewById(R.id.addressET);
        phoneNumberET = (EditText) findViewById(R.id.phoneNumberET);

        accName = (TextView) findViewById(R.id.accName);
        accID = (TextView) findViewById(R.id.accID);
        accEID = (TextView) findViewById(R.id.accEID);
        email = (TextView) findViewById(R.id.email);
        address = (TextView) findViewById(R.id.address);

        accImage = (ImageButton) findViewById(R.id.accImage);
        submitButton = (Button) findViewById(R.id.submitButton);

        //Sets the listeners -----------------
        accImage.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.accImage) {
            selectImage();
        } else if (id == R.id.submitButton) {
            submit();
        } else {
            onBackPressed();
        }
    }

    //Submits the edited details to the database
    private void submit() {
        //adds the new event to the database
        //remember to ignore fields that are empty
        if (selectedImage == null) {
            //set default image
        } else {
            try {
                byte[] inputData = getBytes(getContentResolver().openInputStream(selectedImage));
                //add inputData to database

                Toast.makeText(this, "Saved.", Toast.LENGTH_SHORT).show();
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
                accImage.setImageURI(selectedImage);
            }
        }
    }
}
