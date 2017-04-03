package finalproject.cpsc471_dbms.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 02/03/17.
 */

/* This class represents the login screen. Functionality will be implemented later.
 * Currently a part of the tabs but will be changed.
 * Will also be changed from fragment to AppCompatActivity.
 * This class will be the launcher activity. After login the app will be redirected to the MainActivity. */
public class LoginActivity extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_screen, container, false);

        final Button loginButton = (Button) view.findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkValidInput()) {
                    //if the username and password match, login.
                }
            }
        });

        return view;
    }

    private boolean checkValidInput() {
        return true;
    }
}
