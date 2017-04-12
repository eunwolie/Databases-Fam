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

    // SHOULD NEVER BE CALLED
    protected List<VisualsDef> genEntities() {
        List<VisualsDef> list = new ArrayList<>();

        // SHOULD BE EMPTY

        return list;
    }
}
