package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.SectionTable;
import finalproject.cpsc471_dbms.Constants.StaffTable;
import finalproject.cpsc471_dbms.Definitions.SectionDef;
import finalproject.cpsc471_dbms.Definitions.StaffDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Sections {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = SectionTable._ID + "=?";

    public Sections(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addSection(SectionDef sec)
    {
        ContentValues values = new ContentValues();
        values.put(SectionTable._ID, sec.getGenre());
        values.put(SectionTable.SHELF_NUMBER, sec.getsNo());
        values.put(SectionTable.FLOOR_NUMBER, sec.getfNo());
        db.insert(SectionTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateSection(SectionDef sec) {
        ContentValues values = new ContentValues();
        values.put(SectionTable._ID, sec.getGenre());
        values.put(SectionTable.SHELF_NUMBER, sec.getsNo());
        values.put(SectionTable.FLOOR_NUMBER, sec.getfNo());
        // update row
        int result = db.update(SectionTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(sec.getGenre()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteSection(String genre) {
        db.delete(SectionTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{genre});
    }

    public int deleteSection(SectionDef sec) {
        return db.delete(SectionTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {sec.getGenre() + "" });
    }

}
