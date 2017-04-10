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
 * Created by wj-hong on 20/03/17.
 */

/* This class represents an item when browsing, or the result of a search. */
public class ItemFragment extends Fragment  {

    private ImageView itemImage;
    private TextView itemName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_layout, container, false);

        //code for this fragment goes here.
        itemImage = (ImageView) view.findViewById(R.id.itemImage);
        itemName = (TextView) view.findViewById(R.id.itemName);

        return view;
    }
}
