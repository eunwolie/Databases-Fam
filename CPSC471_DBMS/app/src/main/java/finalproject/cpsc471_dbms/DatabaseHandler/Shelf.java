package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.ShelfTable;
import finalproject.cpsc471_dbms.Definitions.ShelfDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Shelf extends IHandler<ShelfDef, ShelfTable>{
    private static final String WHERE_KEY_EQUALS = ShelfTable._ID + "=?";

    public Shelf(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = (new _DatabaseHelper(context)).getWritableDatabase();
    }

    protected ContentValues innerAdd(ShelfDef s)
    {
        ContentValues values = new ContentValues();
        values.put(ShelfTable.SECT_ID, s.getSectId());
        return values;
    }

    public long add(ShelfDef def)
    { return writeDB.insert(ShelfTable.TABLE_NAME, null, innerAdd(def)); }

    public long update(ShelfDef x) {
        ContentValues values = new ContentValues();
        values.put(ShelfTable.SECT_ID, x.getSectId());
        return writeDB.update(ShelfTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getShelfNumber()+"" });
    }

    public int delete(int id)
    { return delete(new String[]{Integer.toString(id)}); }

    protected List<ShelfDef> genEntities() {
        List<ShelfDef> list = new ArrayList<>();

        int[] genres = new int[]{0, 1, 2, 2, 3, 4, 5, 5};

         for (int i = 0; i < genres.length; i++)
            list.add(new ShelfDef(genres[i], i + 1));

        return list;
    }
}