package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.EventTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.EventDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Event {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = EventTable.START_TIME + "=? AND " + EventTable.DATE + "=? AND " + EventTable.HOST + "=?" ;

    public Event(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void addEvent(EventDef x) {
        ContentValues values = new ContentValues();
        values.put(EventTable.DESCRIPTION, x.getDescription());
        values.put(EventTable.START_TIME, x.getStartTime());
        values.put(EventTable.END_TIME, x.getEndTime());
        values.put(EventTable.DATE, x.getDate());
        values.put(EventTable.TITLE, x.getTitle());
        values.put(EventTable.SID, x.getSponsorID());
        values.put(EventTable.HOST, x.getWorkID());
        db.insert(EventTable.TABLE_NAME, null, values);
    }

    public int updateEvent(EventDef x) {
        ContentValues values = new ContentValues();
        if (x.getDescription() != null)
            values.put(EventTable.DESCRIPTION, x.getDescription());
        if (x.getStartTime() != -1)
            values.put(EventTable.START_TIME, x.getStartTime());
        if (x.getEndTime() != -1)
            values.put(EventTable.END_TIME, x.getEndTime());
        if (x.getDate() != -1)
            values.put(EventTable.DATE, x.getDate());
        if (x.getTitle() != null)
            values.put(EventTable.TITLE, x.getTitle());
        if (x.getSponsorID() != -1)
            values.put(EventTable.SID, x.getSponsorID());
        if (x.getWorkID() != -1)
            values.put(EventTable.HOST, x.getWorkID());
        // update row
        int result = db.update(EventTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] {x.getStartTime()+"", x.getDate()+"", x.getWorkID()+"" });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int deleteEvent(int start, int date, int wid) {
        return db.delete(EventTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{start+"",date+"",wid+""});
    }

    public int deleteEvent(EventDef x) {
        return db.delete(EventTable.TABLE_NAME,
                WHERE_KEY_EQUALS,
                new String[] {x.getStartTime()+"", x.getDate()+"", x.getWorkID()+"" });
    }



}
