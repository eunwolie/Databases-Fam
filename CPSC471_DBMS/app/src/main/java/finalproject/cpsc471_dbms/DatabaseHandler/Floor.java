package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.FloorTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.FloorDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Floor {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = FloorTable._ID + "=?";

    public Floor(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addFloor(FloorDef x) {
        ContentValues values = new ContentValues();
        values.put(FloorTable._ID, x.getfNumber());
        values.put(FloorTable.COMPUTERS, x.getfComputer());
        values.put(FloorTable.WORK_ID, x.getWorkId());
        db.insert(FloorTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateFloor(FloorDef x) {
        ContentValues values = new ContentValues();
        values.put(FloorTable._ID, x.getfNumber());
        values.put(FloorTable.COMPUTERS, x.getfComputer());
        values.put(FloorTable.WORK_ID, x.getWorkId());        // update row
        int result = db.update(FloorTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(x.getfNumber()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteFloor(int f) {
        db.delete(FloorTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(f)});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteFloor(FloorDef x) {
        return db.delete(FloorTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getfNumber() + "" });
    }

}
