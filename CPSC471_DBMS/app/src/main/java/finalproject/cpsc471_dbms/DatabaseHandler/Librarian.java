package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.LibrarianTable;
import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Definitions.LibrarianDef;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Librarian {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = LibrarianTable.WORK_ID + "=?";

    public Librarian(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addLib(LibrarianDef x)
    {
        ContentValues values = new ContentValues();
        values.put(LibrarianTable._ID, x.getWorkId());
        values.put(LibrarianTable.DESKNO, x.getdeskNo());
        db.insert(LibrarianTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateLib(LibrarianDef x) {
        ContentValues values = new ContentValues();
        values.put(LibrarianTable._ID, x.getWorkId());
        values.put(LibrarianTable.DESKNO, x.getdeskNo());
        // update row
        int result = db.update(LibrarianTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(x.getWorkId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteLib(int wid) {
        db.delete(LibrarianTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{wid+""});
    }

    public int deleteLib(LibrarianDef x) {
        return db.delete(LibrarianTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getWorkId() + "" });
    }

}
