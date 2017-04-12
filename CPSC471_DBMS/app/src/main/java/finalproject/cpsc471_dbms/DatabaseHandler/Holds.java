package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.OnHoldTable;
import finalproject.cpsc471_dbms.Definitions.OnHoldDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Holds extends IHandler<OnHoldDef, OnHoldTable>{
    private static final String WHERE_KEY_EQUALS =
            OnHoldTable.ISBN + "=? AND " + OnHoldTable.ID + "=?";
    private SQLiteDatabase writeDB;

    public Holds(Context context)
    {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase(); }

    protected ContentValues innerAdd(OnHoldDef b)
    {
        ContentValues values = new ContentValues();
        values.put(OnHoldTable.HOLD_DATE, b.getHoldDate());
        values.put(OnHoldTable.END_DATE, b.getEndDate());
        values.put(OnHoldTable.STATUS, b.getStatus());
        values.put(OnHoldTable.ID, b.getId());
        values.put(OnHoldTable.ISBN, b.getIsbn());
        return values;
    }

    public long add(OnHoldDef def)
    { return writeDB.insert(OnHoldTable.TABLE_NAME, null, innerAdd(def)); }

    public long update(OnHoldDef x) {
        ContentValues values = new ContentValues();
        if (x.getStatus() != null)
            values.put(OnHoldTable.STATUS, x.getStatus());
        // update row
        return writeDB.update(OnHoldTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getId()+"" });
    }

    public int delete(int bookID, int uID)
    { return delete(new String[]{Integer.toString(bookID), Integer.toString(uID)});}

    // TODO : ADJUST THE DEFINITION
    protected List<OnHoldDef> genEntities() {
        List<OnHoldDef> list = new ArrayList<>();

        list.add(new OnHoldDef(420, 421, OnHoldDef.ARRIVED, 2001, 1121));
        list.add(new OnHoldDef(421, 422, OnHoldDef.WAITING, 2002, 1122));
        list.add(new OnHoldDef(421, 425, OnHoldDef.ARRIVED, 2003, 1123));
        list.add(new OnHoldDef(419, 425, OnHoldDef.ARRIVED, 2004, 1124));
        list.add(new OnHoldDef(419, 425, OnHoldDef.WAITING, 2005, 1125));
        list.add(new OnHoldDef(410, 420, OnHoldDef.WAITING, 2006, 1126));

        return list;
    }
}
