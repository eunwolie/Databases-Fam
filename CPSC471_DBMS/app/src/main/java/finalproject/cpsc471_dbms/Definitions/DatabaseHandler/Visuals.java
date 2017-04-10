package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Constants.VisualTable;
import finalproject.cpsc471_dbms.Definitions.VisualsDef;

/**
 * Created by evech on 2017-03-24.
 */

public class Visuals {
    private static final String WHERE_KEY_EQUALS = VisualTable.ISBN + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Visuals(Context context) {
        this.context = context;
    }

    public long add(VisualsDef x) {
        ContentValues values = new ContentValues();
        values.put(VisualTable.ISBN, x.getIsbn());
        values.put(VisualTable.PAGE_LENGTH, x.getPages());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result= db.insert(VisualTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public VisualsDef get(int isbn) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(VisualTable.TABLE_NAME,
                new String[] {
                        VisualTable.ISBN,
                        VisualTable.PAGE_LENGTH},
                WHERE_KEY_EQUALS,
                new String[] {isbn+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        VisualsDef x = new VisualsDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public long update(VisualsDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(VisualTable.ISBN, x.getIsbn());
        values.put(VisualTable.PAGE_LENGTH, x.getPages());
        // update row
        return db.update(VisualTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getIsbn()+"" });
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<VisualsDef> list = genEntities();

        for (VisualsDef x : list) {
            add(x);
        }
    }

    private List<VisualsDef> genEntities() {
        List<VisualsDef> list = new ArrayList<>();

        list.add(new VisualsDef(1121, 5));
        list.add(new VisualsDef(1122, 2));
        list.add(new VisualsDef(1123, 100));
        list.add(new VisualsDef(1124, 2));
        list.add(new VisualsDef(1125, 6));
        list.add(new VisualsDef(1126, 123));

        return list;
    }
}
