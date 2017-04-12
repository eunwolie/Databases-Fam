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

public class Shelf extends IHandler<ShelfDef, ShelfTable>{
    private static final String WHERE_KEY_EQUALS = ShelfTable._ID + "=?";

    public Shelf(Context context) {
        writeDB = (new _DatabaseHelper(context)).getWritableDatabase();
    }

    protected void innerAdd(ShelfDef s, ContentValues values)
    {
        values.put(ShelfTable._ID, s.getShelfNumber());
        values.put(ShelfTable.GENRE, s.getGenre());
    }

    public long update(ShelfDef x) {
        ContentValues values = new ContentValues();
        values.put(ShelfTable.GENRE, x.getGenre());
        return writeDB.update(ShelfTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getShelfNumber()+"" });
    }

    public int delete(int id)
    { return delete(new String[]{Integer.toString(id)}); }

    protected List<ShelfDef> genEntities() {
        List<ShelfDef> list = new ArrayList<>();

     //   String[] genres = new String[]{"fantasy", "horror", "humour",
      //          "mystery", "non-fiction", "erotica"};

     //   int sectionNums = 1; // increase
     //   int[] floorNums = new int[]{ 1, 2, 2, 3, 3, 4 };

        /*for (int i = 0; i < floorNums.length; i++)
            list.add(new SectionDef(genres[i], sectionNums + i, floorNums[i]));
*/
        list.add(new ShelfDef("fantasy", 1));
        list.add(new ShelfDef("horror", 2));
        list.add(new ShelfDef("humour", 3));
        list.add(new ShelfDef("humour", 4));
        list.add(new ShelfDef("biography", 5));
        list.add(new ShelfDef("biography", 6));


        return list;
    }
}