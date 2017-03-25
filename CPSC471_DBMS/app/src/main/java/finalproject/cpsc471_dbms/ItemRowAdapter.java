package finalproject.cpsc471_dbms;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wj-hong on 24/03/17.
 */

public class ItemRowAdapter extends ArrayAdapter {


    public ItemRowAdapter(Context context, Item[] items) {
        super(context, R.layout.item_layout, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext());
        View itemView = itemInflater.inflate(R.layout.item_layout, parent, false);

        Item singleItem = (Item) getItem(position);
        ImageView itemImage = (ImageView) itemView.findViewById(R.id.itemImage);
        TextView itemName = (TextView) itemView.findViewById(R.id.itemName);

        itemName.setText(singleItem.getName());
        itemImage.setImageResource(R.drawable.cpl_logo);

        return itemView;
    }
}
