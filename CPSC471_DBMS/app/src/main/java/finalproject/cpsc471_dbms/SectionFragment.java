package finalproject.cpsc471_dbms;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by wj-hong on 20/03/17.
 */

public class SectionFragment extends Fragment {

    private TextView titleText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.section_fragment, container, false);

        titleText = (TextView) view.findViewById(R.id.titleText);

        return view;
    }

    public void setTitle(String title) {
        titleText.setText(title);
    }
}
