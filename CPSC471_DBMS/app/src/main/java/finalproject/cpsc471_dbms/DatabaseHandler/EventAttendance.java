package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.EventAttendanceTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.EventAttendanceDef;

/**
 * Created by evech on 2017-03-25.
 */

public class EventAttendance {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = EventAttendanceTable.UID + "=?";
    // isnt there another key we need?

    public EventAttendance(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addEAttendance(EventAttendanceDef x) {
        ContentValues values = new ContentValues();
        values.put(EventAttendanceTable.UID, x.getId());
        values.put(EventAttendanceTable.START_TIME, x.getStartTime());
        values.put(EventAttendanceTable.DATE, x.getDate());
        db.insert(EventAttendanceTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateEAttendance(EventAttendanceDef x) {
        ContentValues values = new ContentValues();
        values.put(EventAttendanceTable.UID, x.getId());
        values.put(EventAttendanceTable.START_TIME, x.getStartTime());
        values.put(EventAttendanceTable.DATE, x.getDate());
        // update row
        int result = db.update(EventAttendanceTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(x.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteEAttendance(int id) {
        db.delete(EventAttendanceTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(id)});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteEAttendance(EventAttendanceDef x) {
        return db.delete(EventAttendanceTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getId() + "" });
    }
}
