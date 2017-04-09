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
    private static final String WHERE_KEY_EQUALS = StaffTable.ID + "=?";

    public Staff(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addStaff(StaffDef staff)
    {
        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, staff.getColumnId());
        values.put(StaffTable.SALARY, staff.getSalary());
        values.put(StaffTable.SSN, staff.getSsn());
        values.put(StaffTable.WORK_ID, staff.getWorkId());
        db.insert(StaffTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateStaff(StaffDef staff) {
        ContentValues values = new ContentValues();
        values.put(StaffTable._ID, staff.getColumnId());
        values.put(StaffTable.SALARY, staff.getSalary());
        values.put(StaffTable.SSN, staff.getSsn());
        values.put(StaffTable.WORK_ID, staff.getWorkId());
        // update row
        int result = db.update(StaffTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(staff.getColumnId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteStaff(int id) {
        db.delete(StaffTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(id)});
    }

    public int deleteStaff(StaffDef staff) {
        return db.delete(StaffTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {staff.getColumnId() + "" });
    }


    /*  protected SQLiteDatabase database;
    private _DatabaseHelper dataHelper;
    private Context aContext;

    public Staff(Context context) {
        this.aContext = context;
        dataHelper = _DatabaseHelper.getHelper(aContext);
        open();

    }

    public void open() throws SQLException {
        if(dataHelper == null)
            dataHelper = _DatabaseHelper.getHelper(aContext);
        database = dataHelper.getWritableDatabase();
    }*/

}