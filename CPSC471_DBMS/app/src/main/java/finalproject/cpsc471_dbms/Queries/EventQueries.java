package finalproject.cpsc471_dbms.Queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.*;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.DatabaseHandler.*;
import finalproject.cpsc471_dbms.Definitions.*;
/**
 * Created by farra on 2017-04-05.
 */

/**
 *
 * Queries about events
 *
 * (partial key for events: Hostid, start time, date)
 */


/*****
 *
 * IN THE SYSTEM OF EVENTS:
 *  In order to update an event, you have to delete it first,
 *  then re-add it, simply because we don't have any event ids to accommodate
 *  for the weak entity
 *  Thus, there is no actual updating system for events
 *
 */

// TODO : See if there's any elaborate queries we need for events

public class EventQueries {
    private SQLiteDatabase readDB;
    private SQLiteDatabase writeDB;

    public EventQueries(Context context)
    {
        readDB = new _DatabaseHelper(context).getReadableDatabase();
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public List<EventDef> getAllEventInfo()
    {
        List<EventDef> events = new ArrayList<EventDef>();

        Cursor cursor = readDB.query(true, EventTable.TABLE_NAME, null,
                null, null, null, null, null, null);

        while (cursor.moveToNext())
        {
            EventDef event = new EventDef();
            event.setDate(cursor.getInt(cursor.getColumnIndex(EventTable.DATE)));
            event.setSponsorID(cursor.getInt(cursor.getColumnIndex(EventTable.SID)));
            event.setEndTime(cursor.getInt(cursor.getColumnIndex(EventTable.END_TIME)));
            event.setStartTime(cursor.getInt(cursor.getColumnIndex(EventTable.START_TIME)));
            event.setTitle(cursor.getString(cursor.getColumnIndex(EventTable.TITLE)));
            event.setWorkID(cursor.getInt(cursor.getColumnIndex(EventTable.HOST)));
            event.setDescription(cursor.getString(cursor.getColumnIndex(EventTable.DESCRIPTION)));
            events.add(event);
        }

        cursor.close();

        return events;
    }

    // Maybe remove sponsor as a specific thing. However, the event NEEDS a host
    /*
    public EventDef getEventInfo(int workID, int date, int startTime)
    {
        EventDef event = new EventDef();

    }
    */

    private void deleteBy(String attribute, String item)
    {
        writeDB.delete(EventTable.TABLE_NAME,
                attribute + "=?",
                new String[]{item});
    }

    public void deleteByStartTime(int startTime)
    {
        deleteBy(EventTable.START_TIME, Integer.toString(startTime));
    }

    public void deleteByEndTime(int endTime)
    {
        deleteBy(EventTable.END_TIME, Integer.toString(endTime));
    }

    public void deleteByTitle(String title)
    {
        deleteBy(EventTable.TITLE, title);
    }

    // TODO : Make other ways to delete by sponsor? Like, by title?
    public void deleteBySponsor(int sID)
    {
        deleteBy(EventTable.SID, Integer.toString(sID));
    }

    public void deleteSpecific(EventDef event)
    {
        String where = EventTable.HOST + "=? AND "
                + EventTable.DATE + "=? AND "
                + EventTable.START_TIME + "=?";
        String[] whereargs = new String[] {Integer.toString(event.getWorkID()),
                Integer.toString(event.getDate()), Integer.toString(event.getStartTime())};
        writeDB.delete(EventTable.TABLE_NAME, where, whereargs);
    }

    public void addUserToEvent(EventDef event, UserDef user)
    {
        String table = EventAttendanceTable.TABLE_NAME + " , " + EventTable.TABLE_NAME;

        ContentValues values = new ContentValues();

        values.put(EventAttendanceTable.DATE, event.getDate());
        values.put(EventAttendanceTable.START_TIME, event.getStartTime());
        values.put(EventAttendanceTable.UID, user.getId());

        writeDB.insert(table, null, values);
    }
}
