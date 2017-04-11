package finalproject.cpsc471_dbms.UI.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 09/04/17.
 */

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton accImage;
    private Button submitButton;
    private Uri selectedImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        //Sets the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Create Account");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        accImage = (ImageButton) findViewById(R.id.accImage);
        submitButton = (Button) findViewById(R.id.submitButton);

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

    //Submits the new user to the database
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
