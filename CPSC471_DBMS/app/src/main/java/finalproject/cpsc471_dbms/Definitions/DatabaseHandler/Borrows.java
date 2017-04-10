package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.AudioTable;
import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.Definitions.AudioDef;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Borrows {
    private static final String WHERE_KEY_EQUALS = BorrowingTable.ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Borrows(Context context) {
        this.context = context;
    }

    public long add(BorrowingDef x) {
        ContentValues values = new ContentValues();
        values.put(BorrowingTable.BORROW_DATE, x.getBorrowDate());
        values.put(BorrowingTable.RETURN_DATE, x.getReturnDate());
        values.put(BorrowingTable.OVERDUE_DAY, x.getOverdueDay());
        values.put(BorrowingTable.STATUS, x.getStatus());
        values.put(BorrowingTable.ID, x.getId());
        values.put(BorrowingTable.ISBN, x.getIsbn());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(BorrowingTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public BorrowingDef get(int id) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(BorrowingTable.TABLE_NAME,
                new String[] {
                        BorrowingTable.BORROW_DATE,
                        BorrowingTable.RETURN_DATE,
                        BorrowingTable.OVERDUE_DAY,
                        BorrowingTable.STATUS,
                        BorrowingTable.ID,
                        BorrowingTable.ISBN},
                WHERE_KEY_EQUALS,
                new String[] {id+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        BorrowingDef x = new BorrowingDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)),
                Integer.parseInt(cur.getString(2)),
                cur.getString(3),
                Integer.parseInt(cur.getString(4)),
                Integer.parseInt(cur.getString(5)));
        return x;
    }

    public long update(BorrowingDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(BorrowingTable.BORROW_DATE, x.getBorrowDate());
        values.put(BorrowingTable.RETURN_DATE, x.getReturnDate());
        values.put(BorrowingTable.OVERDUE_DAY, x.getOverdueDay());
        values.put(BorrowingTable.STATUS, x.getStatus());
        values.put(BorrowingTable.ID, x.getId());
        values.put(BorrowingTable.ISBN, x.getIsbn());
        // update row
        return db.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getId()+"" });
    }

    public long delete(BorrowingDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        long result = db.delete(BorrowingTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getId()+""});
        db.close();
        return result;
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<BorrowingDef> list = genEntities();

        for (BorrowingDef x : list) {
            add(x);
        }
    }

    private List<BorrowingDef> genEntities() {
        List<BorrowingDef> list = new ArrayList<>();

        list.add(new BorrowingDef(420, 421, 4, "overdue", 2001, 1121));
        list.add(new BorrowingDef(421, 422, 1, "overdue", 2002, 1122));
        list.add(new BorrowingDef(421, 425, 0, "borrowed", 2003, 1123));
        list.add(new BorrowingDef(419, 425, 0, "borrowed", 2004, 1124));
        list.add(new BorrowingDef(419, 425, 0, "borrowed", 2005, 1125));
        list.add(new BorrowingDef(410, 420, 1, "overdue", 2006, 1126));

        return list;
    }
}
