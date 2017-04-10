package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.AudioTable;
import finalproject.cpsc471_dbms.Constants.AuthorTable;
import finalproject.cpsc471_dbms.Constants.DonationTable;
import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.Definitions.AudioDef;
import finalproject.cpsc471_dbms.Definitions.AuthorDef;
import finalproject.cpsc471_dbms.Definitions.DonationDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Author {
    private static final String WHERE_KEY_EQUALS = AuthorTable.FIRST_NAME
            + "=? AND " + AuthorTable.MINIT_NAME +
            "=? AND " + AuthorTable.LAST_NAME + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Author(Context context) {
        this.context = context;
    }

    public long add(AuthorDef x) {
        ContentValues values = new ContentValues();
        values.put(AuthorTable.FIRST_NAME, x.getfName());
        values.put(AuthorTable.MINIT_NAME, x.getMinit());
        values.put(AuthorTable.LAST_NAME, x.getlName());
        values.put(AuthorTable.ISBN, x.getIsbn());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(AuthorTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public void loadEntities() {

        // This populates the list momentarily
        List<AuthorDef> list = genEntities();

        for (AuthorDef x : list) {
            add(x);
        }
    }

    private List<AuthorDef> genEntities() {
        List<AuthorDef> list = new ArrayList<>();

        list.add(new AuthorDef("a","b","c",1111));
        list.add(new AuthorDef("d","e","f",1112));
        list.add(new AuthorDef("aa","b","c",1113));
        list.add(new AuthorDef("j","k","rowling",1114));
        list.add(new AuthorDef("bob","","rich",1115));
        list.add(new AuthorDef("kill","me","pls",1116));

        return list;
    }
}
