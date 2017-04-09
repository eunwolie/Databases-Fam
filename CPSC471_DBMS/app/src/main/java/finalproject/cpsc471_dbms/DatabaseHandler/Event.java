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

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
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

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateEvent(EventDef x) {
        ContentValues values = new ContentValues();
        values.put(EventTable.DESCRIPTION, x.getDescription());
        values.put(EventTable.START_TIME, x.getStartTime());
        values.put(EventTable.END_TIME, x.getEndTime());
        values.put(EventTable.DATE, x.getDate());
        values.put(EventTable.TITLE, x.getTitle());
        values.put(EventTable.SID, x.getSponsorID());
        values.put(EventTable.HOST, x.getWorkID());
        // update row
        int result = db.update(EventTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] {x.getStartTime()+"", x.getDate()+"", x.getWorkID()+"" });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteEvent(int start, int date, int wid) {
        db.delete(EventTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{start+"",date+"",wid+""});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteEvent(EventDef x) {
        return db.delete(EventTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getId() + "" });
    }



}
