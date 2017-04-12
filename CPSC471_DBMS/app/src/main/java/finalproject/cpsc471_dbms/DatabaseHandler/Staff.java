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

public class Staff extends IHandler<StaffDef, StaffTable>{
    private static final String WHERE_KEY_EQUALS = StaffTable._ID + "=?";
    private static int workIDs = 0;
    public Staff(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(StaffDef x)
    {
        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, x.getWorkId());
        values.put(StaffTable.SSN, x.getSsn());
        values.put(StaffTable.USER_ID, x.getUId());
        values.put(StaffTable.SALARY, x.getSalary());
        workIDs++;
        return values;
    }

    public long add(StaffDef def)
    { return writeDB.insert(StaffTable.TABLE_NAME, null, innerAdd(def)); }

    public int delete(int workID)
    { return delete(new String[]{Integer.toString(workID)}); }

    private StaffDef get(String where, String[] whereArgs)
    {
        Cursor cursor = writeDB.query(StaffTable.TABLE_NAME,
                null, where,
                whereArgs,
                null, null, null, null);

        StaffDef staff = new StaffDef();

        if (cursor.moveToNext()) {
            staff.setWorkId(cursor.getInt(cursor.getColumnIndex(StaffTable._ID)));
            staff.setSalary(cursor.getInt(cursor.getColumnIndex(StaffTable.SALARY)));
            staff.setSsn(cursor.getInt(cursor.getColumnIndex(StaffTable.SSN)));
            staff.setUId(cursor.getInt(cursor.getColumnIndex(StaffTable.USER_ID)));

            cursor.close();
        }
        return staff;
    }

    public StaffDef getByUID(int uid)
    {
        return get(StaffTable.USER_ID + "=?", new String[]{Integer.toString(uid)});
    }

    public StaffDef getByWID(int wid)
    {
        return get(WHERE_KEY_EQUALS, new String[]{Integer.toString(wid)});
    }

    public int update(StaffDef x) {
        ContentValues values = new ContentValues();
        values.put(StaffTable.SALARY, x.getSalary());
        // update row
        return writeDB.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getWorkId()+"" });
    }


    protected List<StaffDef> genEntities() {
        List<StaffDef> list = new ArrayList<>();

        //create 4 staff out of 6 total users for demo
        list.add(new StaffDef(1, 79678, 4, 4));
        list.add(new StaffDef(2, 21324, 8, 1));
        list.add(new StaffDef(3, 21324, 2, 2));
        list.add(new StaffDef(4, 21324, 3, 3));

        return list;
    }

    public String toString()
    { return "Staff"; }
}
