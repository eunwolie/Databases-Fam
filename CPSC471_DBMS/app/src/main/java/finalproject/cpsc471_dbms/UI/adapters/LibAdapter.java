package finalproject.cpsc471_dbms.UI.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.custom.Item;

/**
 * Created by wj-hong on 11/04/17.
 */

public class LibAdapter extends ArrayAdapter {

    public LibAdapter(Context context, String[] libs) {
        super(context, R.layout.rating_layout, libs);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext());
        View view = itemInflater.inflate(R.layout.rating_layout, parent, false);

        TextView libName = (TextView) view.findViewById(R.id.libName);
        TextView libID = (TextView) view.findViewById(R.id.libID);
        RadioButton likeButton = (RadioButton) view.findViewById(R.id.likeButton);

        libName.setText("Tim Cook");
        libID.setText("15626");

        return view;
    }
}
