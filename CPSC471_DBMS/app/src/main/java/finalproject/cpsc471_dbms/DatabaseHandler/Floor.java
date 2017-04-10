package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.FloorTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.FloorDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Floor {
    private static final String WHERE_KEY_EQUALS = FloorTable._ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Floor(Context context) {
        this.context = context;
    }

    public long add(FloorDef x) {
        ContentValues values = new ContentValues();
        values.put(FloorTable._ID, x.getfNumber());
        values.put(FloorTable.WORK_ID, x.getWorkId());
        values.put(FloorTable.COMPUTERS, x.getfComputer());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(FloorTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public FloorDef get(int id) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(FloorTable.TABLE_NAME,
                new String[] {
                        FloorTable._ID,
                        FloorTable.COMPUTERS,
                        FloorTable.WORK_ID,},
                WHERE_KEY_EQUALS,
                new String[] {id+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        FloorDef x = new FloorDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)),
                Integer.parseInt(cur.getString(2)));
        return x;
    }

    public long update(FloorDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FloorTable._ID, x.getfNumber());
        values.put(FloorTable.WORK_ID, x.getWorkId());
        values.put(FloorTable.COMPUTERS, x.getfComputer());
        // update row
        return db.update(FloorTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getfNumber()+"" });
    }

    public long delete(BorrowingDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        long result = db.delete(FloorTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getId()+""});
        db.close();
        return result;
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<FloorDef> list = genEntities();

        for (FloorDef x : list) {
            add(x);
        }
    }

    private List<FloorDef> genEntities() {
        List<FloorDef> list = new ArrayList<>();

        list.add(new FloorDef(1, 10, 7001));
        list.add(new FloorDef(2, 3, 7001));
        list.add(new FloorDef(3, 10, 7002));
        list.add(new FloorDef(4, 0, 7002));
        list.add(new FloorDef(5, 2, 7004));
        list.add(new FloorDef(6, 10, 70003));

        return list;
    }

}
