package finalproject.cpsc471_dbms.Queries;

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
    private SQLiteDatabase readDB;

    public MaterialQueries(Context context) {
        readDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public _LocationDef getLocation(int ISBN) {
        _LocationDef location = new _LocationDef();

        String tables = FloorTable.TABLE_NAME + " , "
                + SectionTable.TABLE_NAME + " , "
                + ShelfTable.TABLE_NAME + " , "
                + MaterialTable.TABLE_NAME;
        String[] want = new String[]{FloorTable._ID, SectionTable.NAME, ShelfTable._ID};
        String where =  MaterialTable._ID + "=? AND "
                + ShelfTable._ID + "=" + MaterialTable.SHELF_NO + " AND "
                + SectionTable._ID + "=" + ShelfTable.SECT_ID + " AND "
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

    public boolean isElectronic(int isbn)
    {
        Cursor cursor = readDB.query(VisualTable.TABLE_NAME,
                null, VisualTable.ISBN + "=? AND " + VisualTable.HAS_EBOOK + "=?",
                new String[]{Integer.toString(isbn), Integer.toString(1)},
                null, null, null);

        boolean electronic = false;

        if (cursor.moveToNext())  electronic = true;

        return electronic;
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
