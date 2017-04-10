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
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.FloorDef;
import finalproject.cpsc471_dbms.Definitions.LibHelpDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Helps {
    private static final String WHERE_KEY_EQUALS =
            LibHelpTable.WORK_ID + "=? AND " +
            LibHelpTable.USER_ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Helps(Context context) {
        this.context = context;
    }

    public long add(LibHelpDef x) {
        ContentValues values = new ContentValues();
        values.put(LibHelpTable.WORK_ID, x.getWorkId());
        values.put(LibHelpTable.USER_ID, x.getUserId());

        db = new _DatabaseHelper(context).getWritableDatabase();
        return db.insert(LibHelpTable.TABLE_NAME, null, values);
    }

    public LibHelpDef get(int wid, int uid) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(LibHelpTable.TABLE_NAME,
                new String[] {
                        LibHelpTable.WORK_ID,
                        LibHelpTable.USER_ID},
                WHERE_KEY_EQUALS,
                new String[] {wid+"",uid+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        LibHelpDef x = new LibHelpDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public int update(LibHelpDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(LibHelpTable.WORK_ID, x.getWorkId());
        values.put(LibHelpTable.USER_ID, x.getUserId());
        // update row
        return db.update(LibHelpTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getWorkId()+"", x.getUserId()+"" });
    }

    public void delete(LibHelpDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        db.delete(LibHelpTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getWorkId()+"", x.getUserId()+""});
        db.close();
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<LibHelpDef> list = genEntities();

        for (LibHelpDef x : list) {
            add(x);
        }
    }

    private List<LibHelpDef> genEntities() {
        List<LibHelpDef> list = new ArrayList<>();

        list.add(new LibHelpDef(7001,9001));
        list.add(new LibHelpDef(7002,9002));
        list.add(new LibHelpDef(7002,9003));
        list.add(new LibHelpDef(7003,9004));
        list.add(new LibHelpDef(7004,9001));
        list.add(new LibHelpDef(7002,9001));
        list.add(new LibHelpDef(7002,9001));

        return list;
    }

}
