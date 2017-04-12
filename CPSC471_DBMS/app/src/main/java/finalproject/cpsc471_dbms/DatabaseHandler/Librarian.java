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
import finalproject.cpsc471_dbms.Constants.LibrarianTable;
import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.LibHelpDef;
import finalproject.cpsc471_dbms.Definitions.LibrarianDef;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Librarian extends IHandler<LibrarianDef, LibrarianTable>{
    private static final String WHERE_KEY_EQUALS = LibrarianTable.WORK_ID + "=?";

    public Librarian(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(LibrarianDef l)
    {
        ContentValues values = new ContentValues();
        values.put(LibrarianTable.WORK_ID, l.getWorkId());
        values.put(LibrarianTable.DESKNO, l.getdeskNo());
        return values;
    }

    public long add(LibrarianDef def)
    { return writeDB.insert(LibrarianTable.TABLE_NAME, null, innerAdd(def)); }

    // TODO : FIX LIBRARIAN GET
    public LibrarianDef get(int wid) {
        Cursor cur = writeDB.query(BorrowingTable.TABLE_NAME,
                new String[] { LibrarianTable.WORK_ID, LibrarianTable.DESKNO },
                WHERE_KEY_EQUALS,
                new String[] {wid+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        LibrarianDef x = new LibrarianDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public int update(LibrarianDef x) {

        ContentValues values = new ContentValues();
        values.put(LibrarianTable.DESKNO, x.getdeskNo());
        // update row
        return writeDB.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getWorkId()+"" });
    }

    public int delete(int id)
    { return delete(new String[]{Integer.toString(id)}); }

    protected List<LibrarianDef> genEntities() {
        List<LibrarianDef> list = new ArrayList<>();

        list.add(new LibrarianDef(7006,104));

        return list;
    }
}