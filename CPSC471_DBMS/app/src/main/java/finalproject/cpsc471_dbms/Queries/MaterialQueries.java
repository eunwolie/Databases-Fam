package finalproject.cpsc471_dbms.Queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.DatabaseHandler.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by farra on 2017-04-05.
 */

public class MaterialQueries extends IQueries{
    private SQLiteDatabase writeDB;
    private SQLiteDatabase readDB;

    public MaterialQueries(Context context) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
        readDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void insert(MaterialsDef material) {
        ContentValues values = new ContentValues();


    }

    public void delete(int ISBN) {
        writeDB.delete(MaterialTable.TABLE_NAME, MaterialTable._ID + "=?",
                new String[]{Integer.toString(ISBN)});
    }

    public _LocationDef getLocation(int ISBN) {
        _LocationDef location = new _LocationDef();

        String tables = FloorTable.TABLE_NAME + " , "
                + SectionTable.TABLE_NAME + " , "
                + ShelfTable.TABLE_NAME + " , "
                + MaterialTable.TABLE_NAME;
        String[] want = new String[]{FloorTable._ID, SectionTable._ID, ShelfTable._ID};
        String where =  MaterialTable._ID + "=? AND "
                + ShelfTable._ID + "=" + MaterialTable.SHELF_NO + " AND "
                + SectionTable._ID + "=" + ShelfTable.GENRE + " AND "
                + FloorTable._ID + "=" + SectionTable.FLOOR_NUMBER;

        Cursor cursor = readDB.query(tables, want,
                where,
                new String[]{Integer.toString(ISBN)},
                null, null, null);

        if (cursor == null) return null;

        cursor.moveToFirst();

        location.setFloor(cursor.getInt(cursor.getColumnIndex(FloorTable._ID)));
        location.setSection(cursor.getInt(cursor.getColumnIndex(SectionTable._ID)));
        location.setShelf(cursor.getInt(cursor.getColumnIndex(ShelfTable._ID)));

        cursor.close();

        return location;
    }

    public boolean isAvailable(int isbn)
    {
        String table = BorrowingTable.TABLE_NAME + " , " + OnHoldTable.TABLE_NAME
                + " , " + MaterialTable.TABLE_NAME;
        String want =  BorrowingTable.ISBN + "=? AND "
                + OnHoldTable.ISBN + "=? AND "
                + MaterialTable._ID + "=?";

        Cursor cursor = readDB.query(table,
                new String[]{"COUNT (*)"},
                want,
                new String[]{Integer.toString(isbn), Integer.toString(isbn), Integer.toString(isbn)},
                null, null, null);

        boolean available = false;

        if (cursor.moveToNext()) available = true;

        cursor.close();

        return available;
    }

    public long addImage(int isbn, byte[] image)
    {
        ContentValues values = new ContentValues();
        values.put(MaterialTable.IMAGE, image);

        return writeDB.update(MaterialTable.TABLE_NAME, values,
                MaterialTable._ID + "=?", new String[]{Integer.toString(isbn)});
    }

    public byte[] getImage(int isbn)
    {
        Cursor cursor = readDB.query(MaterialTable.TABLE_NAME, new String[]{MaterialTable.IMAGE},
                MaterialTable._ID + "=?", new String[]{Integer.toString(isbn)},
                null, null, null);

        byte[] image = null;

        if (cursor.moveToNext())
            image = cursor.getBlob(0);

        cursor.close();

        return image;
    }
}
