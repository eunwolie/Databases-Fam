package finalproject.cpsc471_dbms.UI.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import finalproject.cpsc471_dbms.UI.custom.Item;
import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 24/03/17.
 */

public class BookAdapter extends ArrayAdapter {

    private ImageView bookImage;
    private TextView bookName;
    private TextView bookAuthor;
    private TextView bookGenre;

    public BookAdapter(Context context, Item[] items) {
        super(context, R.layout.book_layout, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext());
        View view = itemInflater.inflate(R.layout.book_layout, parent, false);

        bookImage = (ImageView) view.findViewById(R.id.bookImage);
        bookName = (TextView) view.findViewById(R.id.bookName);
        bookAuthor = (TextView) view.findViewById(R.id.bookAuthor);
        bookGenre = (TextView) view.findViewById(R.id.bookGenre);

        bookImage.setImageResource(R.drawable.ic_book_1);
        bookName.setText("The Thief Lord");
        bookAuthor.setText("Cornelia Funke");
        bookGenre.setText("Fantasy");

        return view;
    }
}
