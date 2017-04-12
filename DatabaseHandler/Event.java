package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.EventTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.EventDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Event extends IHandler<EventDef, EventTable>{
    private static final String WHERE_KEY_EQUALS =
            EventTable.HOST + "=? AND " + EventTable.DATE + "=? AND "
                    + EventTable.START_TIME + "=?";

    public Event(Context context)
    { writeDB = new _DatabaseHelper(context).getWritableDatabase(); }

    protected void innerAdd(EventDef e, ContentValues values)
    {
        values.put(EventTable.HOST, e.getWorkID());
        values.put(EventTable.SID, e.getSponsorID());
        values.put(EventTable.TITLE, e.getTitle());
        values.put(EventTable.DATE, e.getDate());
        values.put(EventTable.START_TIME, e.getStartTime());
        values.put(EventTable.END_TIME, e.getEndTime());
        values.put(EventTable.DESCRIPTION, e.getDescription());
        values.put(EventTable.IMAGE, e.getImage());
    }

    public EventDef get(int workID, int date, int startTime) {
        String where = EventTable.HOST + "=? AND "
                + EventTable.DATE + "=? AND "
                + EventTable.START_TIME + "=?";
        String[] whereArgs = new String[]{
                Integer.toString(workID), Integer.toString(date), Integer.toString(startTime)
        };

        Cursor cursor = writeDB.query(EventTable.TABLE_NAME, null,
                where, whereArgs, null, null, null);

        EventDef event = new EventDef();

        while (cursor.moveToNext())
        {
            event.setDate(cursor.getInt(cursor.getColumnIndex(EventTable.DATE)));
            event.setSponsorID(cursor.getInt(cursor.getColumnIndex(EventTable.SID)));
            event.setEndTime(cursor.getInt(cursor.getColumnIndex(EventTable.END_TIME)));
            event.setStartTime(cursor.getInt(cursor.getColumnIndex(EventTable.START_TIME)));
            event.setTitle(cursor.getString(cursor.getColumnIndex(EventTable.TITLE)));
            event.setWorkID(cursor.getInt(cursor.getColumnIndex(EventTable.HOST)));
            event.setDescription(cursor.getString(cursor.getColumnIndex(EventTable.DESCRIPTION)));
            event.setImage(cursor.getBlob(cursor.getColumnIndex(EventTable.IMAGE)));
        }

        cursor.close();
        return event;
    }

    public long update(EventDef x) {
        ContentValues values = new ContentValues();
        values.put(EventTable.HOST, x.getWorkID());
        values.put(EventTable.SID, x.getSponsorID());
        values.put(EventTable.TITLE, x.getTitle());
        values.put(EventTable.DATE, x.getDate());
        values.put(EventTable.START_TIME, x.getStartTime());
        values.put(EventTable.END_TIME, x.getEndTime());
        values.put(EventTable.DESCRIPTION, x.getDescription());
        // update row
        return writeDB.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getSponsorID()+"", x.getTitle() });
    }

    public int delete(EventDef e) {
        return delete(new String[]{
                Integer.toString(e.getWorkID()), Integer.toString(e.getDate()),
                Integer.toString(e.getStartTime())
        });
    }

    protected List<EventDef> genEntities() {
        List<EventDef> list = new ArrayList<>();

        // int startTime, int endTime, int date, String title,
        // int sponsorID, int workID, String description, byte[] image

        int[] startTimes = new int[]{0, 20, 50, 20, 12};
        int[] dates = new int[]{100, 100, 200, 301, 430};
        String[] descriptions = new String[]{
                "We want all of your money",
                "We only want some of your money",
                "Here we figure out how many single moms there are",
                "Anime drawing tutorial!!",
                "Baby Book Reading"
        };


        for (int i = 0; i < startTimes.length; i++)
        {
            EventDef e = new EventDef();
            e.setStartTime(startTimes[i]);
            e.setEndTime(startTimes[i] + 300);
            e.setDate(dates[i]);
            e.setTitle("Event" + i);
            e.setSponsorID(5000 + i);
            e.setWorkID(7004 + i);
            e.setDescription("Generic");
            // TODO : ADD EVENT IMAGE
            list.add(e);
        }

        return list;
    }
}
