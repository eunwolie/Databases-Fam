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

public class EventAttendance {
    private static final String WHERE_KEY_EQUALS =
            EventAttendanceTable.UID + "=? AND" +
            EventAttendanceTable.DATE + "=? AND " +
            EventAttendanceTable.START_TIME + "=?";
    private SQLiteDatabase db;
    private Context context;

    public EventAttendance(Context context) {
        this.context = context;
    }

    public long add(EventAttendanceDef x) {
        ContentValues values = new ContentValues();
        values.put(EventAttendanceTable.UID, x.getId());
        values.put(EventAttendanceTable.DATE, x.getDate());
        values.put(EventAttendanceTable.HOST_ID, x.getWorkID());
        values.put(EventAttendanceTable.START_TIME, x.getStartTime());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(EventAttendanceTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public EventAttendanceDef get(int uid, int date, int startTime) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(BorrowingTable.TABLE_NAME,
                new String[] {
                        EventAttendanceTable.UID,
                        EventAttendanceTable.DATE,
                        EventAttendanceTable.HOST_ID,
                        EventAttendanceTable.START_TIME},
                WHERE_KEY_EQUALS,
                new String[] {uid+"", date+"", startTime+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        EventAttendanceDef x = new EventAttendanceDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)),
                Integer.parseInt(cur.getString(2)),
                Integer.parseInt(cur.getString(3)));
        return x;
    }

    public int update(EventAttendanceDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventAttendanceTable.UID, x.getId());
        values.put(EventAttendanceTable.DATE, x.getDate());
        values.put(EventAttendanceTable.HOST_ID, x.getWorkID());
        values.put(EventAttendanceTable.START_TIME, x.getStartTime());
        // update row
        return db.update(EventAttendanceTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getId()+"" });
    }

    public void delete(EventAttendanceDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        db.delete(EventAttendanceTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getId()+"", x.getDate()+"", x.getStartTime()+""});
        db.close();
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<EventAttendanceDef> list = genEntities();

        for (EventAttendanceDef x : list) {
            add(x);
        }
    }

    private List<EventAttendanceDef> genEntities() {
        List<EventAttendanceDef> list = new ArrayList<>();

        list.add(new EventAttendanceDef(9001,900, 421, 7001));
        list.add(new EventAttendanceDef(9002,800, 422, 7001));
        list.add(new EventAttendanceDef(9003,1100, 425, 7001));
        list.add(new EventAttendanceDef(9004,1200, 425, 7002));
        list.add(new EventAttendanceDef(9005,1200, 425, 7003));
        list.add(new EventAttendanceDef(9006,1200, 425, 7004));

        return list;
    }

}
