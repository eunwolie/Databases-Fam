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

public class EventQueries extends IQueries{

    public EventQueries(Context context)
    {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public String getHost(int wid)
    {
        String eventHost = null;

        String table = EventTable.TABLE_NAME + ", " + StaffTable.TABLE_NAME +
                ", " + UserTable.TABLE_NAME;
        String where = EventTable.HOST + "=? AND "
                + EventTable.HOST + "=" + StaffTable._ID
                + StaffTable._ID + "=" + UserTable._ID;
        String[] whereArgs = new String[]{ Integer.toString(wid) };
        Cursor cursor = writeDB.query(true, table, new String[]{UserTable.FIRST_NAME,
                UserTable.LAST_NAME},
                where, whereArgs, null, null, null, null, null);

        if (cursor.moveToNext())
        {
            eventHost = cursor.getString(cursor.getColumnIndex(UserTable.FIRST_NAME));
            eventHost += " " + cursor.getString(cursor.getColumnIndex(UserTable.LAST_NAME));
        }

        cursor.close();

        return eventHost;
    }

    public String getSponsor(int wid, int date, int startTime)
    {
        String eventSponsor = null;

        String table = EventTable.TABLE_NAME + " , " + SponsorTable.TABLE_NAME;
        String where = EventTable.SID + "=" + SponsorTable._ID
                + " AND " + EventTable.HOST + "=? AND "
                + EventTable.DATE + "=? AND " + EventTable.START_TIME + "=?";
        String[] whereArgs = new String[]{ Integer.toString(wid),
            Integer.toString(date), Integer.toString(startTime)};
        Cursor cursor = writeDB.query(true, table, new String[]{SponsorTable.NAME},
                where, whereArgs, null, null, null, null, null);

        if (cursor.moveToNext())
            eventSponsor = cursor.getString(cursor.getColumnIndex(SponsorTable.NAME));

        cursor.close();

        return eventSponsor;
    }

    public List<EventDef> getAllEventInfo()
    {
        List<EventDef> events = new ArrayList<EventDef>();

        Cursor cursor = writeDB.query(true, EventTable.TABLE_NAME, null,
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
            event.setImage(cursor.getBlob(cursor.getColumnIndex(EventTable.IMAGE)));
            events.add(event);
        }

        cursor.close();

        return events;
    }

    private int deleteBy(String attribute, String item)
    {
        return writeDB.delete(EventTable.TABLE_NAME,
                attribute + "=?",
                new String[]{item});
    }

    public int deleteByStartTime(int startTime)
    { return deleteBy(EventTable.START_TIME, Integer.toString(startTime)); }

    public int deleteByEndTime(int endTime)
    { return deleteBy(EventTable.END_TIME, Integer.toString(endTime)); }

    public int deleteByTitle(String title)
    { return deleteBy(EventTable.TITLE, title); }

    public int deleteBySponsor(int sID)
    {
        return deleteBy(EventTable.SID, Integer.toString(sID));
    }

    public int deleteSpecific(EventDef event)
    {
        String where = EventTable.HOST + "=? AND "
                + EventTable.DATE + "=? AND "
                + EventTable.START_TIME + "=?";
        String[] whereargs = new String[] {Integer.toString(event.getWorkID()),
                Integer.toString(event.getDate()), Integer.toString(event.getStartTime())};
        return writeDB.delete(EventTable.TABLE_NAME, where, whereargs);
    }

    public long addUserToEvent(int date, int startTime, int userId, int hostId)
    {
        String table = EventAttendanceTable.TABLE_NAME;

        ContentValues values = new ContentValues();

        values.put(EventAttendanceTable.DATE, date);
        values.put(EventAttendanceTable.START_TIME, startTime);
        values.put(EventAttendanceTable.UID, userId);
        values.put(EventAttendanceTable.HOST_ID, hostId);   // instead make it title?

        return writeDB.insert(table, null, values);
    }

    public int removeUserFromEvent(int uID)
    {
        return writeDB.delete(EventAttendanceTable.TABLE_NAME,
                EventAttendanceTable.UID + "=?",
                new String[]{Integer.toString(uID)});
    }

    public boolean isInEvent(int userId, EventDef e)
    {
        List<EventDef> events = new ArrayList<EventDef>();

        String where = EventAttendanceTable.UID + "=? AND "
                + EventAttendanceTable.DATE + "=? AND "
                + EventAttendanceTable.START_TIME + "=?";
        String[] want = new String[]{Integer.toString(userId),
                Integer.toString(e.getDate()),
                Integer.toString(e.getStartTime())};

        Cursor cursor = writeDB.query(true, EventAttendanceTable.TABLE_NAME,
                new String[]{"COUNT(*)"},
                where, want, null, null, null, null);

        // should be 1 or 0
        int repeatsInEvent = cursor.getInt(0);

        cursor.close();

        return (repeatsInEvent == 1);
    }

    public int eventAttendeeAmount(EventDef e)
    {
        String where =  EventAttendanceTable.DATE + "=? AND "
                + EventAttendanceTable.START_TIME + "=? AND "
                + EventAttendanceTable.HOST_ID + "=?";

        String[] want = new String[]{Integer.toString(e.getDate()),
            Integer.toString(e.getStartTime()),
            Integer.toString(e.getWorkID())};

        Cursor cursor = writeDB.query(EventAttendanceTable.TABLE_NAME,
                new String[]{"COUNT(*)"}, where, want,
                null, null, null);

        int attendeeAmt = 0;

        if (cursor.moveToNext())
        {
            attendeeAmt = cursor.getInt(0);
            cursor.close();
        }

        return attendeeAmt;
    }

    public long setImage(String title, byte[] image)
    {
        ContentValues values = new ContentValues();
        values.put(EventTable.IMAGE, image);

        return writeDB.update(EventTable.TABLE_NAME, values,
                EventTable.TITLE + "=?", new String[]{title});
    }

    public byte[] getImage(String title)
    {
        Cursor cursor = writeDB.query(true, EventTable.TABLE_NAME,
                new String[]{EventTable.IMAGE},
                EventTable.TITLE + "=?", new String[]{title},
                null, null, null, null);

        byte[] image = null;

        if (cursor.moveToNext()) {
            image = cursor.getBlob(0);
            cursor.close();
        }

        return image;
    }


}
