package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.FloorTable;
import finalproject.cpsc471_dbms.Constants.LibHelpTable;
import finalproject.cpsc471_dbms.Constants.LibrarianTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.FloorDef;
import finalproject.cpsc471_dbms.Definitions.LibHelpDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Helps extends IHandler<LibHelpDef, LibHelpTable>{
    private static final String WHERE_KEY_EQUALS =
            LibHelpTable.WORK_ID + "=? AND " +
            LibHelpTable.USER_ID + "=?";

    public Helps(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(LibHelpDef l)
    {
        ContentValues values = new ContentValues();
        values.put(LibHelpTable.WORK_ID, l.getWorkId());
        values.put(LibHelpTable.USER_ID, l.getUserId());
        return values;
    }

    public long add(LibHelpDef def)
    { return writeDB.insert(LibHelpTable.TABLE_NAME, null, innerAdd(def)); }

    public int delete(int wid, int uid)
    { return delete(new String[]{Integer.toString(wid), Integer.toString(uid)}); }

    protected List<LibHelpDef> genEntities() {
        List<LibHelpDef> list = new ArrayList<>();

        list.add(new LibHelpDef(5,4));
        list.add(new LibHelpDef(6,4));
        list.add(new LibHelpDef(5,2));
        list.add(new LibHelpDef(5,3));
        list.add(new LibHelpDef(6,2));
        list.add(new LibHelpDef(6,1));

        return list;
    }

    public String toString()
    { return "Helps"; }

}
