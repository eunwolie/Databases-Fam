package finalproject.cpsc471_dbms.UI;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 20/03/17.
 */

/* This class represents an item when browsing, or the result of a search. */
public class ItemFragment extends Fragment  {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment, container, false);

        //code for this fragment goes here.

        return view;
    }
}
