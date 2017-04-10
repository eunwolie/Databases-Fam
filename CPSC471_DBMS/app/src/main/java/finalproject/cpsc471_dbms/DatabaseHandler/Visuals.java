package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.VisualTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.VisualsDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Visuals {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = VisualTable.ISBN + "=?";

    public Visuals(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addVisual(VisualsDef vis) {
        ContentValues values = new ContentValues();
        values.put(VisualTable.ISBN, vis.getIsbn());
        values.put(VisualTable.PAGE_LENGTH, vis.getPages());
        db.insert(VisualTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateVisual(VisualsDef vis) {
        ContentValues values = new ContentValues();
        values.put(VisualTable.ISBN, vis.getIsbn());
        values.put(VisualTable.PAGE_LENGTH, vis.getPages());
        // update row
        int result = db.update(VisualTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(vis.getIsbn()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteVisual(int isbn) {
        db.delete(VisualTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(isbn)});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteVisual(VisualsDef vis) {
        return db.delete(VisualTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {vis.getIsbn() + "" });
    }

}
