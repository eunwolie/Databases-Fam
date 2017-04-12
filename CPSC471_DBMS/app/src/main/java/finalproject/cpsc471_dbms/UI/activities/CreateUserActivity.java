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
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import finalproject.cpsc471_dbms.Definitions.UserDef;
import finalproject.cpsc471_dbms.Facades.LoginFacade;
import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 09/04/17.
 */

public class CreateUserActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginFacade loginFacade;

    private ImageButton accImage;
    private TextView usernameET, fnameET, lnameET, passwordET, emailET, addressET, phoneNumberET;

    private Button submitButton, makeSponsor;
    private Uri selectedImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        loginFacade = new LoginFacade(this);

        //Sets the toolbar -----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Create Account");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views -----------------
        usernameET = (TextView) findViewById(R.id.usernameET);
        fnameET = (TextView) findViewById(R.id.fnameET);
        lnameET = (TextView) findViewById(R.id.lnameET);
        passwordET = (TextView) findViewById(R.id.passwordET);
        emailET = (TextView) findViewById(R.id.emailET);
        addressET = (TextView) findViewById(R.id.addressET);
        phoneNumberET = (TextView) findViewById(R.id.phoneNumberET);

        accImage = (ImageButton) findViewById(R.id.accImage);
        submitButton = (Button) findViewById(R.id.submitButton);
        makeSponsor = (Button) findViewById(R.id.makeSponsor);

        //Sets the listeners -----------------
        accImage.setOnClickListener(this);
        submitButton.setOnClickListener(this);
        makeSponsor.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginFacade.close();
    }

    //---------------------------------- LISTENERS ----------------------------------
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.accImage) {
            selectImage();
        } else if (id == R.id.submitButton) {
            submit();
        } else if (id == R.id.makeSponsor) {
            startActivity(new Intent(CreateUserActivity.this, CreateSponsorActivity.class));
            finish();
        } else {
            onBackPressed();
        }
    }

    //-----------------------------------------------------------------------------

    //---------------------------------- METHODS ----------------------------------

    //Submits the new user to the database
    private void submit() {
        if (selectedImage == null) {
            selectedImage = Uri.parse("android.resource://finalproject.cpsc471_dbms/" + R.drawable.def_profile); //not sure
        } else {
            try {
                byte[] inputData = getBytes(getContentResolver().openInputStream(selectedImage));

                UserDef newUser = new UserDef(0, fnameET.getText().toString(), lnameET.getText().toString(), usernameET.getText().toString(),
                                                addressET.getText().toString(), passwordET.getText().toString(), Integer.parseInt(phoneNumberET.getText().toString()),
                                                inputData, emailET.getText().toString());

                loginFacade.createAccount(newUser);

            } catch (Exception e) {
                //ignore
            }
        }

        Toast.makeText(this, "Account Created.", Toast.LENGTH_SHORT).show();
        finish();
    }

    //-----------------------------------------------------------------------------

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
                accImage.setImageURI(selectedImage);
            }
        }
    }

    //-----------------------------------------------------------------------------
}
