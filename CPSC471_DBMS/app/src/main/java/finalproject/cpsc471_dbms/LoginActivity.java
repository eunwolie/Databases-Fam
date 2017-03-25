package finalproject.cpsc471_dbms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by wj-hong on 02/03/17.
 */

/* This class represents the login screen. Functionality will be implemented later.
 * Currently a part of the tabs but will be changed.
 * Will also be changed from fragment to AppCompatActivity.
 * This class will be the launcher activity. After login the app will be redirected to the MainActivity. */
public class LoginActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        final EditText usernameText = (EditText) findViewById(R.id.usernameEdit);
        final EditText passwordText = (EditText) findViewById(R.id.passwordEdit);

        final Button loginButton = (Button) findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidInput(usernameText.getText().toString(), passwordText.getText().toString())) {
                    //if the username and password match, login.
                    Intent launch = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(launch);
                }
            }
        });
    }

    private boolean checkValidInput(String username, String password) {

        //check if username exists
        //check if username matches password
        Toast.makeText(this, "Credentials: " + username + " :: " + password, Toast.LENGTH_SHORT).show();

        return true;
    }
}
