package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.VisualTable;
import finalproject.cpsc471_dbms.Definitions.VisualsDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Visuals extends IHandler<VisualsDef, VisualTable>{
    private static final String WHERE_KEY_EQUALS = VisualTable.ISBN + "=?";

    public Visuals(Context context) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public Visuals(SQLiteDatabase writeDB) { this.writeDB = writeDB; }

    protected void innerAdd(VisualsDef v, ContentValues values)
    {
        values.put(VisualTable.ISBN, v.getIsbn());
        values.put(VisualTable.PAGE_LENGTH, v.getLength());
        values.put(VisualTable.HAS_EBOOK, v.getHasEBook());
    }

    public int delete(int isbn)
    { return delete(new String[]{Integer.toString(isbn)}); }

    // TODO : Need to account for actual materials ISBN
    protected List<VisualsDef> genEntities() {
        List<VisualsDef> list = new ArrayList<>();

        list.add(new VisualsDef(1114, 5, 0));
        list.add(new VisualsDef(1115, 2, 0));
        list.add(new VisualsDef(1115, 100, 0));
        list.add(new VisualsDef(1116, 2, 0));
        list.add(new VisualsDef(1117, 6, 0));
        list.add(new VisualsDef(1118, 123, 0));

        return list;
    }
}
