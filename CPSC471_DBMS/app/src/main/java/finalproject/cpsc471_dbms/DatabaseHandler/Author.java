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

public class Author extends IBasicHandler<AuthorDef, AuthorTable>{
    private static final String WHERE_KEY_EQUALS = AuthorTable.ISBN + "=?";

    public Author(Context context) {   
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected void innerAdd(AuthorDef a, ContentValues values)
    {
        values.put(AuthorTable.FIRST_NAME, a.getfName());
        values.put(AuthorTable.MINIT_NAME, a.getMinit());
        values.put(AuthorTable.LAST_NAME, a.getlName());
        values.put(AuthorTable.ISBN, a.getIsbn());
    }

    // TODO : CHECK IF BOOKS EXIST
    protected List<AuthorDef> genEntities() {
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
