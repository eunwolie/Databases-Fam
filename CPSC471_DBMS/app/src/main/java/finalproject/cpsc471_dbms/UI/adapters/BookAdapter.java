package finalproject.cpsc471_dbms.UI.adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import finalproject.cpsc471_dbms.Definitions.AuthorDef;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;
import finalproject.cpsc471_dbms.Definitions._BorrowedDef;
import finalproject.cpsc471_dbms.Facades.MaterialFacade;
import finalproject.cpsc471_dbms.UI.custom.Item;
import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 24/03/17.
 */

public class BookAdapter extends ArrayAdapter {

    private MaterialFacade materialFacade;

    private ImageView bookImage;
    private TextView bookName;
    private TextView bookAuthor;
    private TextView bookGenre;

    public BookAdapter(Context context, _BorrowedDef[] items) {
        super(context, R.layout.book_layout, items);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater itemInflater = LayoutInflater.from(getContext());
        View view = itemInflater.inflate(R.layout.book_layout, parent, false);

        materialFacade = new MaterialFacade(getContext());

        _BorrowedDef borrowedItem = (_BorrowedDef) getItem(position);

        MaterialsDef singleItem = materialFacade.getSpecificISBN(borrowedItem.getIsbn()).get(0);

        bookImage = (ImageView) view.findViewById(R.id.bookImage);
        bookName = (TextView) view.findViewById(R.id.bookName);
        bookAuthor = (TextView) view.findViewById(R.id.bookAuthor);
        bookGenre = (TextView) view.findViewById(R.id.bookGenre);

        bookImage.setImageResource(R.drawable.def_book);
        bookName.setText("The Thief Lord");
        bookAuthor.setText("Cornelia Funke");
        bookGenre.setText("Fantasy");

        bookGenre.setText(singleItem.getGenre());

        String authors=null;
        for(AuthorDef author : singleItem.getAuthor()) {
            authors += author.getfName() + " " + author.getlName() + ", ";
        } bookAuthor.setText(authors.substring(0, authors.length() -2));

        bookName.setText(singleItem.getTitle());
        bookImage.setImageBitmap(BitmapFactory.decodeByteArray(singleItem.getImage(), 0, singleItem.getImage().length));

        return view;
    }
}
