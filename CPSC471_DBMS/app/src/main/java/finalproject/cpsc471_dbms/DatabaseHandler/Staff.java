package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.StaffTable;
import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.Definitions.StaffDef;
import finalproject.cpsc471_dbms.Definitions.UserDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Staff {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = StaffTable._ID + "=?";

    public Staff(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void addStaff(StaffDef staff)
    {
        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, staff.getWorkId());
        values.put(StaffTable.SALARY, staff.getSalary());
        values.put(StaffTable.SSN, staff.getSsn());
        values.put(StaffTable.USER_ID, staff.getUId());
        db.insert(StaffTable.TABLE_NAME, null, values);
    }

    public int updateStaff(StaffDef staff) {
        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, staff.getWorkId());
        values.put(StaffTable.SALARY, staff.getSalary());
        values.put(StaffTable.SSN, staff.getSsn());
        values.put(StaffTable.USER_ID, staff.getUId());
        // update row
        int result = db.update(StaffTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(staff.getWorkId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int deleteStaff(int id) {
        return db.delete(StaffTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(id)});
    }

    public int deleteStaff(StaffDef staff) {
        return db.delete(StaffTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {staff.getWorkId() + "" });
    }

}