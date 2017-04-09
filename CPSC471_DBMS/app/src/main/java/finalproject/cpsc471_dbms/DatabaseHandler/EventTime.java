package finalproject.cpsc471_dbms.DatabaseHandler;


import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.EventTimeTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.EventTimeDef;

/**
 * Created by evech on 2017-03-25.
 */

public class EventTime {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = EventTimeTable.SPONSOR_ID + "=? AND" + EventTimeTable.TITLE + "=?" ;

    public EventTime(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addEventTime(EventTimeDef x) {
        ContentValues values = new ContentValues();
        values.put(EventTimeTable.START, x.getStart());
        values.put(EventTimeTable.END, x.getEnd());
        values.put(EventTimeTable.SPONSOR_ID, x.getSponsorID());
        values.put(EventTimeTable.TITLE, x.getTitle());
        db.insert(BorrowingTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateEventTime(EventTimeDef x) {
        ContentValues values = new ContentValues();
        values.put(EventTimeTable.START, x.getStart());
        values.put(EventTimeTable.END, x.getEnd());
        values.put(EventTimeTable.SPONSOR_ID, x.getSponsorID());
        values.put(EventTimeTable.TITLE, x.getTitle());
        // update row
        int result = db.update(EventTimeTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] {x.getSponsorID() + "",x.getTitle() });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteEventTIme(int sid, String title) {
        db.delete(EventTimeTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(sid), title});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteEventTIme(EventTimeDef x) {
        return db.delete(EventTimeTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getSponsorID() + "",x.getTitle() });
    }


}
