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

// TODO : Handle multivalued attribute (language and author)

public class MaterialQueries {
    SQLiteDatabase writeDB;
    SQLiteDatabase readDB;

    public MaterialQueries(Context context, int isbn) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
        readDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void update(MaterialsDef material) {
        ContentValues values = new ContentValues();

        if (material.getIsbn() != -1)
            values.put(MaterialTable._ID, material.getIsbn());
        if (material.getTitle() != null)
            values.put(MaterialTable.TITLE, material.getTitle());
        if (material.getDescription() != null)
            values.put(MaterialTable.DESCRIPTION, material.getDescription());
        if (material.getAuthor() != null)
            values.put(MaterialTable.AUTHOR, material.getAuthor());
        if (material.getType() != null)
            values.put(MaterialTable.TYPE, material.getType());
        if (material.getGenre() != null)
            values.put(MaterialTable.GENRE, material.getGenre());
        if (material.getYearOfCreation() != -1)
            values.put(MaterialTable.YEAR_CREATED, material.getYearOfCreation());
        if (material.getLanguage() != null)
            values.put(MaterialTable.LANGUAGE, material.getLanguage());
        if (material.getCompany() != null)
            values.put(MaterialTable.COMPANY, material.getCompany());
        if (material.getShelf() != -1)
            values.put(MaterialTable.SHELF_NO, material.getShelf());

        writeDB.update(MaterialTable.TABLE_NAME, values,
                MaterialTable._ID + "=?",
                new String[]{Integer.toString(material.getIsbn())});
    }

    public void insert(MaterialsDef material) {
        ContentValues values = new ContentValues();

        values.put(MaterialTable._ID, material.getIsbn());
        values.put(MaterialTable.TITLE, material.getTitle());
        values.put(MaterialTable.DESCRIPTION, material.getDescription());
        values.put(MaterialTable.AUTHOR, material.getAuthor());
        values.put(MaterialTable.TYPE, material.getType());
        values.put(MaterialTable.GENRE, material.getGenre());
        values.put(MaterialTable.YEAR_CREATED, material.getYearOfCreation());
        values.put(MaterialTable.LANGUAGE, material.getLanguage());
        values.put(MaterialTable.COMPANY, material.getCompany());
        values.put(MaterialTable.SHELF_NO, material.getShelf());

        writeDB.insert(MaterialTable.TABLE_NAME, null, values);
    }

    public void delete(int ISBN) {
        writeDB.delete(MaterialTable.TABLE_NAME, MaterialTable._ID + "=?",
                new String[]{Integer.toString(ISBN)});
    }

    // TODO : Delete ISBN from _LocationDef
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

        // TODO : NEED TO FIX THIS ACCORDING TO MULTIPLE BOOKS IN SHELVES
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

    // TODO : Fix the result
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

        int num = cursor.getInt(0);

        cursor.close();

        return (num == 0);
    }
}
