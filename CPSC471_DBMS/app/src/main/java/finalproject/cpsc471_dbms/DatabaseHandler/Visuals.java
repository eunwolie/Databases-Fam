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
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public Visuals(SQLiteDatabase writeDB) {
        super(WHERE_KEY_EQUALS);
        this.writeDB = writeDB; }

    protected ContentValues innerAdd(VisualsDef v)
    {
        ContentValues values = new ContentValues();
        values.put(VisualTable.ISBN, v.getIsbn());
        values.put(VisualTable.PAGE_LENGTH, v.getLength());
        values.put(VisualTable.HAS_EBOOK, v.getHasEBook());
        return values;
    }

    public long add(VisualsDef def)
    { return writeDB.insert(VisualTable.TABLE_NAME, null, innerAdd(def)); }

    public int delete(int isbn)
    { return delete(new String[]{Integer.toString(isbn)}); }

    // SHOULD NEVER BE CALLED
    protected List<VisualsDef> genEntities() {
        List<VisualsDef> list = new ArrayList<>();

        // SHOULD BE EMPTY

        return list;
    }
}
