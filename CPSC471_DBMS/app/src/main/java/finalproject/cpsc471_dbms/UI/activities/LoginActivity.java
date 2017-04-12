package finalproject.cpsc471_dbms.UI.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import finalproject.cpsc471_dbms.Facades.LoginFacade;
import finalproject.cpsc471_dbms.Facades.MainFacade;
import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 02/03/17.
 */

/* This class represents the login screen. Functionality will be implemented later.
 * Currently a part of the tabs but will be changed.
 * Will also be changed from fragment to AppCompatActivity.
 * This class will be the launcher activity. After login the app will be redirected to the MainActivity. */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginFacade loginQueries;
    private MainFacade mainQueries;
    private EditText usernameText;
    private EditText passwordText;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        loginQueries = new LoginFacade(this);
        mainQueries = new MainFacade(this);
        mainQueries.getTestLists();
        mainQueries.populateLists();

        usernameText = (EditText) findViewById(R.id.usernameEdit);
        passwordText = (EditText) findViewById(R.id.passwordEdit);

        final Button loginButton = (Button) findViewById(R.id.loginButton);
        final Button createButton = (Button) findViewById(R.id.createButton);

        loginButton.setOnClickListener(this);
        createButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginQueries.close();
    }

    //Buttons listener
    @Override
    public void onClick(View v) {
        int flag;
        Intent intent = null;
        if (v.getId() == R.id.loginButton) {
            if ((flag = checkValidInput()) != -1) {
                Toast.makeText(this, "Credentials: " + usernameText.getText() + " :: " + passwordText.getText(), Toast.LENGTH_SHORT).show();
                MainActivity.user = flag;
                intent = new Intent(LoginActivity.this, MainActivity.class);
            } else {
                Toast.makeText(this, "You have entered an invalid username or password.", Toast.LENGTH_SHORT).show();
            }
        } else if (v.getId() == R.id.createButton) {
            intent = new Intent(LoginActivity.this, CreateUserActivity.class);
        } startActivity(intent);
    }

    //Invalid = -1; Regular User = 0; Librarian = 1; Sponsor = 2; CHECK MAINACTIVITY FOR DETAILS
    private int checkValidInput() {
        if (loginQueries.isExistingSponsor(usernameText.getText().toString(), passwordText.getText().toString()))
            return MainActivity.SPONSOR;
        else {
            int id = loginQueries.getUserID(usernameText.getText().toString(), passwordText.getText().toString());
            if (id != -1) {
                if (loginQueries.isUserRegular(id))
                    return MainActivity.NORMAL;
                else
                    return MainActivity.LIBRARIAN;
            }
            else
                return -1;
        }
    }
}
