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

    public Author(Context context) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(AuthorDef a)
    {
        ContentValues values = new ContentValues();
        values.put(AuthorTable.FIRST_NAME, a.getfName());
        values.put(AuthorTable.MINIT_NAME, a.getMinit());
        values.put(AuthorTable.LAST_NAME, a.getlName());
        values.put(AuthorTable.ISBN, a.getIsbn());
        return values;
    }

    public long add(AuthorDef def)
    { return writeDB.insert(AuthorTable.TABLE_NAME, null, innerAdd(def)); }

    // SHOULD NEVER BE CALLED
    protected List<AuthorDef> genEntities() {
        List<AuthorDef> list = new ArrayList<>();

        // SHOULD BE EMPTY

        return list;
    }

    public String toString()
    { return "Author"; }
}
