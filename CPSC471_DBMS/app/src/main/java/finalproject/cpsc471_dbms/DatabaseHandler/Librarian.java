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
import finalproject.cpsc471_dbms.Definitions.LibrarianDef;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Librarian {
    private static final String WHERE_KEY_EQUALS = LibrarianTable.WORK_ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Librarian(Context context) {
        this.context = context;
    }

    public long add(LibrarianDef x) {
        ContentValues values = new ContentValues();
        values.put(LibrarianTable.WORK_ID, x.getWorkId());
        values.put(LibrarianTable.DESKNO, x.getdeskNo());

        db = new _DatabaseHelper(context).getWritableDatabase();
        return db.insert(LibrarianTable.TABLE_NAME, null, values);
    }

    public LibrarianDef get(int wid) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(BorrowingTable.TABLE_NAME,
                new String[] {
                        LibrarianTable.WORK_ID,
                        LibrarianTable.DESKNO
                       },
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
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LibrarianTable.WORK_ID, x.getWorkId());
        values.put(LibrarianTable.DESKNO, x.getdeskNo());
        // update row
        return db.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getWorkId()+"" });
    }

    public void delete(LibrarianDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        db.delete(BorrowingTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getWorkId()+""});
        db.close();
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<LibrarianDef> list = genEntities();

        for (LibrarianDef x : list) {
            add(x);
        }
    }

    private List<LibrarianDef> genEntities() {
        List<LibrarianDef> list = new ArrayList<>();

        list.add(new LibrarianDef(7006,104));

        return list;
    }
}
