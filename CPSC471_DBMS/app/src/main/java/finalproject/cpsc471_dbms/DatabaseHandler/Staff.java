package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.StaffTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.StaffDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Staff {
    private static final String WHERE_KEY_EQUALS = StaffTable._ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Staff(Context context) {
        this.context = context;
    }

    public void add(StaffDef x) {
        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, x.getWorkId());
        values.put(StaffTable.SSN, x.getSsn());
        values.put(StaffTable.USER_ID, x.getUId());
        values.put(StaffTable.SALARY, x.getSalary());

        db = new _DatabaseHelper(context).getWritableDatabase();
        db.insert(StaffTable.TABLE_NAME, null, values);
        db.close();
    }

    public StaffDef get(int wid) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(StaffTable.TABLE_NAME,
                new String[] {
                        StaffTable._ID,
                        StaffTable.SSN,
                        StaffTable.USER_ID,
                        StaffTable.SALARY},
                WHERE_KEY_EQUALS,
                new String[] {wid+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        StaffDef x = new StaffDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)),
                Integer.parseInt(cur.getString(2)),
                Integer.parseInt(cur.getString(3)));
        return x;
    }

    public int update(StaffDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, x.getWorkId());
        values.put(StaffTable.SSN, x.getSsn());
        values.put(StaffTable.USER_ID, x.getUId());
        values.put(StaffTable.SALARY, x.getSalary());
        // update row
        return db.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getWorkId()+"" });
    }

    public void delete(StaffDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        db.delete(StaffTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getWorkId()+""});
        db.close();
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<StaffDef> list = genEntities();

        for (StaffDef x : list) {
            add(x);
        }
    }

    private List<StaffDef> genEntities() {
        List<StaffDef> list = new ArrayList<>();

        list.add(new StaffDef(7005, 79678, 9006, 100));
        list.add(new StaffDef(7006, 21324, 9007, 900));

        return list;
    }

}