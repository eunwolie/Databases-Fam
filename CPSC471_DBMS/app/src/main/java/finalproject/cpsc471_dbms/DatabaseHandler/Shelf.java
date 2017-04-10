package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.ShelfTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.ShelfDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Shelf{
    private static final String WHERE_KEY_EQUALS = ShelfTable._ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Shelf(Context context) {
        this.context = context;
    }

    public long add(ShelfDef x) {
        ContentValues values = new ContentValues();
        values.put(ShelfTable._ID, x.getShelfNumber());
        values.put(ShelfTable.GENRE, x.getGenre());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(BorrowingTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public ShelfDef get(int shid) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(BorrowingTable.TABLE_NAME,
                new String[] {
                        ShelfTable.GENRE,
                        ShelfTable._ID},
                WHERE_KEY_EQUALS,
                new String[] {shid+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        ShelfDef x = new ShelfDef(
                cur.getString(0),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public long update(ShelfDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ShelfTable._ID, x.getShelfNumber());
        values.put(ShelfTable.GENRE, x.getGenre());
        // update row
        return db.update(ShelfTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getShelfNumber()+"" });
    }

    public void delete(ShelfDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        db.delete(BorrowingTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getShelfNumber()+""});
        db.close();
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<ShelfDef> list = genEntities();

        for (ShelfDef x : list) {
            add(x);
        }
    }

    private List<ShelfDef> genEntities() {
        List<ShelfDef> list = new ArrayList<>();

        list.add(new ShelfDef("fantasy", 1));
        list.add(new ShelfDef("horror", 2));
        list.add(new ShelfDef("humour", 3));
        list.add(new ShelfDef("humour", 4));
        list.add(new ShelfDef("biography", 5));
        list.add(new ShelfDef("biography", 6));


        return list;
    }
}