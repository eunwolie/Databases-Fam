package finalproject.cpsc471_dbms.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 02/03/17.
 */

/* This class represents the login screen. Functionality will be implemented later.
 * Currently a part of the tabs but will be changed.
 * Will also be changed from fragment to AppCompatActivity.
 * This class will be the launcher activity. After login the app will be redirected to the MainActivity. */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText;
    private EditText passwordText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        usernameText = (EditText) findViewById(R.id.usernameEdit);
        passwordText = (EditText) findViewById(R.id.passwordEdit);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final Button createButton = (Button) findViewById(R.id.createButton);

        loginButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        if (v.getId() == R.id.loginButton) {
            if (checkValidInput())
                intent = new Intent(LoginActivity.this, MainActivity.class);
        } else if (v.getId() == R.id.createButton) {
            intent = new Intent(LoginActivity.this, CreateUserActivity.class);
        } startActivity(intent);
    }

    //incomplete
    private boolean checkValidInput() {
        Toast.makeText(this, "Credentials: " + usernameText.getText() + " :: " + passwordText.getText(), Toast.LENGTH_SHORT).show();
        return true;
    }
}
