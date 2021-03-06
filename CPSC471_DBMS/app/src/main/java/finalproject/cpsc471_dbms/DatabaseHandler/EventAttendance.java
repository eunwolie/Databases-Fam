package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.EventAttendanceTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.EventAttendanceDef;

/**
 * Created by evech on 2017-03-25.
 */

public class EventAttendance extends IHandler<EventAttendanceDef, EventAttendanceTable>{
    private static final String WHERE_KEY_EQUALS =
            EventAttendanceTable.UID + "=? AND" +
            EventAttendanceTable.DATE + "=? AND " +
            EventAttendanceTable.START_TIME + "=?";
    private SQLiteDatabase writeDB;

    public EventAttendance(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public ContentValues innerAdd(EventAttendanceDef e)
    {
        ContentValues values = new ContentValues();

        values.put(EventAttendanceTable.UID, e.getId());
        values.put(EventAttendanceTable.DATE, e.getDate());
        values.put(EventAttendanceTable.HOST_ID, e.getWorkID());
        values.put(EventAttendanceTable.START_TIME, e.getStartTime());

        return values;
    }

    public long add(EventAttendanceDef def)
    { return writeDB.insert(EventAttendanceTable.TABLE_NAME, null, innerAdd(def)); }

    public void delete(EventAttendanceDef e)
    { delete(new String[] {Integer.toString(e.getId()),
            Integer.toString(e.getDate()), Integer.toString(e.getStartTime())}); }


    // TODO : WHEN DELETE AND ADD NEW EVENT, COPY ALL OF THE ENTRIES INTO A NEW TABLE
    public int update(EventAttendanceDef x) {
        ContentValues values = new ContentValues();
        values.put(EventAttendanceTable.DATE, x.getDate());
        values.put(EventAttendanceTable.HOST_ID, x.getWorkID());
        values.put(EventAttendanceTable.START_TIME, x.getStartTime());
        // update row
        return writeDB.update(EventAttendanceTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getId()+"" });
    }

    protected List<EventAttendanceDef> genEntities() {
        List<EventAttendanceDef> list = new ArrayList<>();

        list.add(new EventAttendanceDef(5, 900, 50, 1));
        list.add(new EventAttendanceDef(6, 800, 50, 1));
        list.add(new EventAttendanceDef(5, 1100, 50, 2));
        list.add(new EventAttendanceDef(6, 1200, 50, 2));
        list.add(new EventAttendanceDef(5, 1200, 50, 3));
        list.add(new EventAttendanceDef(5, 1200, 50, 4));

        return list;
    }

    public String toString()
    { return "Attends"; }
}
