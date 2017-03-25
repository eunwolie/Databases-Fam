package finalproject.cpsc471_dbms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

        final ImageView accImage = (ImageView) view.findViewById(R.id.accImage);
        final TextView accName = (TextView) view.findViewById(R.id.accName);
        final TextView accID = (TextView) view.findViewById(R.id.accID);
        final TextView accEID = (TextView) view.findViewById(R.id.accEID);

        //do stuff

        return view;
    }
}
