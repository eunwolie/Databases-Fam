package finalproject.cpsc471_dbms.UI.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import finalproject.cpsc471_dbms.Definitions.SponsorDef;
import finalproject.cpsc471_dbms.Facades.LoginFacade;
import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 11/04/17.
 */

public class CreateSponsorActivity extends AppCompatActivity implements View.OnClickListener {

    private LoginFacade loginFacade;

    private EditText spnName, spnReason;
    private Button makeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_sponsor);

        loginFacade = new LoginFacade(this);

        //Sets the toolbar -----------------
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Sponsor Sign Up");

        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);

        //Sets the views -----------------
        spnName = (EditText) findViewById(R.id.spnName);
        spnReason = (EditText) findViewById(R.id.spnReason);

        makeButton = (Button) findViewById(R.id.makeButton);

        makeButton.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginFacade.close();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.makeButton) {
            submit();
        } else {
            onBackPressed();
        }
    }

    private void submit() {
        SponsorDef newSponsor = new SponsorDef(spnName.getText().toString(), spnReason.getText().toString());

        int idPass = loginFacade.createSponsor(newSponsor);

        Toast.makeText(this, "Log in with password: " + idPass, Toast.LENGTH_SHORT).show();
        finish();
    }
}
