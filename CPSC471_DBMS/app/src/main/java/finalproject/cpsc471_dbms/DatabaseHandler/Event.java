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

public class Event {
    private static final String WHERE_KEY_EQUALS =
            EventTable.SID + "=? AND" +
            EventTable.TITLE + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Event(Context context) {
        this.context = context;
    }

    public long add(EventDef x) {
        ContentValues values = new ContentValues();
        values.put(EventTable.HOST, x.getWorkID());
        values.put(EventTable.SID, x.getSponsorID());
        values.put(EventTable.TITLE, x.getTitle());
        values.put(EventTable.DATE, x.getDate());
        values.put(EventTable.START_TIME, x.getStartTime());
        values.put(EventTable.END_TIME, x.getEndTime());
        values.put(EventTable.DESCRIPTION, x.getDescription());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(EventTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public EventDef get(int sid, String title) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(BorrowingTable.TABLE_NAME,
                new String[] {
                        EventTable.HOST,
                        EventTable.SID,
                        EventTable.TITLE,
                        EventTable.DATE,
                        EventTable.START_TIME,
                        EventTable.END_TIME,
                        EventTable.DESCRIPTION},
                WHERE_KEY_EQUALS,
                new String[] {sid+"",title},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        EventDef x = new EventDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)),
                cur.getString(2),
                Integer.parseInt(cur.getString(3)),
                Integer.parseInt(cur.getString(4)),
                Integer.parseInt(cur.getString(5)),
                cur.getString(6));
        return x;
    }

    public long update(EventDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EventTable.HOST, x.getWorkID());
        values.put(EventTable.SID, x.getSponsorID());
        values.put(EventTable.TITLE, x.getTitle());
        values.put(EventTable.DATE, x.getDate());
        values.put(EventTable.START_TIME, x.getStartTime());
        values.put(EventTable.END_TIME, x.getEndTime());
        values.put(EventTable.DESCRIPTION, x.getDescription());
        // update row
        return db.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getSponsorID()+"", x.getTitle() });
    }

    public long delete(EventDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

       long result = db.delete(EventTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getSponsorID()+"", x.getTitle()});
        db.close();
        return result;
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<EventDef> list = genEntities();

        for (EventDef x : list) {
            add(x);
        }
    }

    private List<EventDef> genEntities() {
        List<EventDef> list = new ArrayList<>();

        list.add(new EventDef(5001, 430, "event1" ));
        list.add(new EventDef(5002, 410, "event2"));
        list.add(new EventDef(5003, 410, "event3"));
        list.add(new EventDef(5004, 410, "event4"));
        list.add(new EventDef(5005, 410, "event5"));
        list.add(new EventDef(5006, 410, "event6"));

        return list;
    }
}
