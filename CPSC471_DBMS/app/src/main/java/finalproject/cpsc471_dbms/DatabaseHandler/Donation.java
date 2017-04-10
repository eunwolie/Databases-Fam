package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.DonationTable;
import finalproject.cpsc471_dbms.Definitions.DonationDef;


/**
 * Created by evech on 2017-03-25.
 */

public class Donation {
    private static final String WHERE_KEY_EQUALS = DonationTable.ISBN + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Donation(Context context) {
        this.context = context;
    }

    public long add(DonationDef x) {
        ContentValues values = new ContentValues();
        values.put(DonationTable.SID, x.getsId());
        values.put(DonationTable.ISBN, x.getIsbn());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(DonationTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public DonationDef get(int isbn) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(DonationTable.TABLE_NAME,
                new String[] {
                        DonationTable.SID,
                        DonationTable.ISBN},
                WHERE_KEY_EQUALS,
                new String[] {isbn+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        DonationDef x = new DonationDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public long update(DonationDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DonationTable.SID, x.getsId());
        values.put(DonationTable.ISBN, x.getIsbn());
        // update row
        return db.update(DonationTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getIsbn()+"" });
    }

    public long delete(DonationDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        long result = db.delete(DonationTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getIsbn()+""});
        db.close();
        return result;
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<DonationDef> list = genEntities();

        for (DonationDef x : list) {
            add(x);
        }
    }

    private List<DonationDef> genEntities() {
        List<DonationDef> list = new ArrayList<>();

        list.add(new DonationDef(5001,1111));
        list.add(new DonationDef(5002,1112));
        list.add(new DonationDef(5003,1113));
        list.add(new DonationDef(5003, 1114));
        list.add(new DonationDef(5004,1115));
        list.add(new DonationDef(5006,1116));

        return list;
    }
}
