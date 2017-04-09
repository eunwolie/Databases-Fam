package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.ShelfTable;
import finalproject.cpsc471_dbms.Constants.StaffTable;
import finalproject.cpsc471_dbms.Definitions.ShelfDef;
import finalproject.cpsc471_dbms.Definitions.StaffDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Shelf{
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = ShelfTable.SHELF_NO + "=?";

    public Shelf(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public void addShelf(ShelfDef shelf)
    {
        ContentValues values = new ContentValues();
        values.put(ShelfTable.ISBN, shelf.getIsbn());
        values.put(ShelfTable.GENRE, shelf.getGenre());
        values.put(ShelfTable.SHELF_NO, shelf.getShelfNumber());
        db.insert(ShelfTable.TABLE_NAME, null, values);
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateShelf(ShelfDef shelf) {
        ContentValues values = new ContentValues();
        values.put(ShelfTable.ISBN, shelf.getIsbn());
        values.put(ShelfTable.GENRE, shelf.getGenre());
        values.put(ShelfTable.SHELF_NO, shelf.getShelfNumber());
        // update row
        int result = db.update(ShelfTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(shelf.getShelfNumber()});
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    // idk how this works either
    public int deleteShelf(int no) {
        db.delete(ShelfTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{String.valueOf(no)});
    }

    public int deleteShelf(ShelfDef shelf) {
        return db.delete(ShelfTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {shelf.getShelfNumber() + "" });
    }

}