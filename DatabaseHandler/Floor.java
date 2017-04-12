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

public class Floor extends IHandler<FloorDef, FloorTable>{
    private static final String WHERE_KEY_EQUALS = FloorTable._ID + "=?";

    public Floor(Context context) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected void innerAdd(FloorDef x, ContentValues values)
    {
        values.put(FloorTable._ID, x.getfNumber());
        values.put(FloorTable.WORK_ID, x.getWorkId());
        values.put(FloorTable.COMPUTERS, x.getfComputer());
    }

    // TODO : DO I NEED THIS??
    /*
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
    */

    public long update(FloorDef x) {
        ContentValues values = new ContentValues();

        if (x.getWorkId() != -1)
            values.put(FloorTable.WORK_ID, x.getWorkId());
        if (x.getfComputer() != -1)
            values.put(FloorTable.COMPUTERS, x.getfComputer());

        return writeDB.update(FloorTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getfNumber()+"" });
    }

    // TODO : FIX THESE ENTITIES LATER
    protected List<FloorDef> genEntities() {
        List<FloorDef> list = new ArrayList<>();

        list.add(new FloorDef(1, 10, 7001));
        list.add(new FloorDef(2, 3, 7002));
        list.add(new FloorDef(3, 10, 7003));
        list.add(new FloorDef(4, 0, 7004));

        //int[] workIDs = new int[]{};

        //for (int i = 0; i < workIDs.length)

        return list;
    }

}
