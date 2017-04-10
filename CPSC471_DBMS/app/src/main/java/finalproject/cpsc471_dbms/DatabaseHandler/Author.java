package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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

    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS =
            AuthorTable.FIRST_NAME + "=? AND " +
            AuthorTable.MINIT_NAME + "=? AND " +
            AuthorTable.LAST_NAME + "=?";

    public Author(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void addAuthor(AuthorDef author) {
        ContentValues values = new ContentValues();
        values.put(AuthorTable.FIRST_NAME, author.getfName());
        values.put(AuthorTable.MINIT_NAME, author.getMinit());
        values.put(AuthorTable.LAST_NAME, author.getlName());
        values.put(AuthorTable.ISBN, author.getIsbn());
        db.insert(AuthorTable.TABLE_NAME, null, values);
    }

    // idk about this one
    public int updateAuthor (AuthorDef x) {
        ContentValues values = new ContentValues();
        values.put(AuthorTable.FIRST_NAME, x.getfName());
        values.put(AuthorTable.MINIT_NAME, x.getMinit());
        values.put(AuthorTable.LAST_NAME, x.getlName());
        values.put(AuthorTable.ISBN, x.getIsbn());
        // update row
        int result = db.update(AuthorTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] {x.getfName(),x.getMinit(),x.getlName() });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int deleteAuthor(String fname, String minit, String lname) {
        return db.delete(AuthorTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{fname, minit, lname});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteAuthor(AuthorDef x) {
        return db.delete(AuthorTable.TABLE_NAME,
                WHERE_KEY_EQUALS,
                new String[]{x.getfName(), x.getMinit(), x.getlName()});
    }

}
