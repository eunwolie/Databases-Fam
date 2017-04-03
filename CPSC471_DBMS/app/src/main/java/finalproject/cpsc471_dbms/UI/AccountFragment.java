package finalproject.cpsc471_dbms.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 26/02/17.
 */

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_tab, container, false);

        //code for this fragment goes here

        return view;
    }
}
