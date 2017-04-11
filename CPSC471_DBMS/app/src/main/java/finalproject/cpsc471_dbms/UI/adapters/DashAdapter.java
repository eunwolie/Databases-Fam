package finalproject.cpsc471_dbms.UI.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import finalproject.cpsc471_dbms.R;
import finalproject.cpsc471_dbms.UI.custom.Item;

/**
 * Created by wj-hong on 10/04/17.
 */

public class DashAdapter extends ArrayAdapter {

    public DashAdapter(Context context, Item[] events) {
        super(context, R.layout.dash_item_layout, events);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext());
        View itemView = itemInflater.inflate(R.layout.dash_item_layout, parent, false);

        Item singleItem = (Item) getItem(position);

        ImageView cardImage = (ImageView) itemView.findViewById(R.id.cardImage);
        TextView eventName = (TextView) itemView.findViewById(R.id.eventName);
        TextView sponsor = (TextView) itemView.findViewById(R.id.sponsor);
        TextView date = (TextView) itemView.findViewById(R.id.date);

        date.setText("TUE, JAN 3 - WED, MAR 1");
        sponsor.setText("Saruman Ltd");
        eventName.setText("Taking the Hobbits to Isengard");
        cardImage.setImageResource(R.drawable.cpl_logo);

        return itemView;
    }
}
