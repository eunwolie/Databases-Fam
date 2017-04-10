package finalproject.cpsc471_dbms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by wj-hong on 26/02/17.
 */

public class AccountFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_tab, container, false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);

        final ImageView accImage = (ImageView) view.findViewById(R.id.accImage);
        final TextView accName = (TextView) view.findViewById(R.id.accName);
        final TextView accID = (TextView) view.findViewById(R.id.accID);
        final TextView accEID = (TextView) view.findViewById(R.id.accEID);

        FragmentManager fragmentManager = getChildFragmentManager();

        SectionFragment onHold = (SectionFragment) fragmentManager.findFragmentById(R.id.onHoldSection);
        SectionFragment checkedOut = (SectionFragment) fragmentManager.findFragmentById(R.id.checkedOutSection);
        SectionFragment overDue = (SectionFragment) fragmentManager.findFragmentById(R.id.overdueSection);

        final String onHoldTitle = "ON HOLD";
        final String checkedOutTitle = "CHECKED OUT";
        final String overDueTitle = "OVER DUE";

        onHold.setTitle(onHoldTitle);
        checkedOut.setTitle(checkedOutTitle);
        overDue.setTitle(overDueTitle);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MaterialViewActivity.class));
            }
        });

        //do stuff

        return view;
    }

}
