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
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(FloorDef x)
    {
        ContentValues values = new ContentValues();
        //values.put(FloorTable.WORK_ID, x.getWorkId());
        values.put(FloorTable.COMPUTERS, x.getfComputer());
        return values;
    }

    public long add(FloorDef def)
    { return writeDB.insert(FloorTable.TABLE_NAME, null, innerAdd(def)); }

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

    // TODO : ADD WORK IDS AFTERWARDS
    protected List<FloorDef> genEntities() {
        List<FloorDef> list = new ArrayList<>();

        // MUST ADD THE WORK IDS AFTER

        list.add(new FloorDef(1, 10, 7001));
        list.add(new FloorDef(2, 3, 7002));
        list.add(new FloorDef(3, 10, 7003));
        list.add(new FloorDef(4, 0, 7004));

        //int[] workIDs = new int[]{};

        //for (int i = 0; i < workIDs.length)

        return list;
    }

}
