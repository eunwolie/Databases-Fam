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

public class Borrows extends IHandler<BorrowingDef, BorrowingTable>{
    private static final String WHERE_KEY_EQUALS =
            BorrowingTable.ISBN + "=? AND " + BorrowingTable.ID + "=?";
    private SQLiteDatabase writeDB;

    public Borrows(Context context)
    {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(BorrowingDef b)
    {
        ContentValues values = new ContentValues();
        values.put(BorrowingTable.BORROW_DATE, b.getBorrowDate());
        values.put(BorrowingTable.RETURN_DATE, b.getReturnDate());
        values.put(BorrowingTable.OVERDUE_DAY, b.getOverdueDay());
        values.put(BorrowingTable.STATUS, b.getStatus());
        values.put(BorrowingTable.ID, b.getId());
        values.put(BorrowingTable.ISBN, b.getIsbn());
        return values;
    }

    public long add(BorrowingDef def)
    { return writeDB.insert(BorrowingTable.TABLE_NAME, null, innerAdd(def)); }

    public long update(BorrowingDef x) {
        ContentValues values = new ContentValues();
        if (x.getOverdueDay() != -1)
           values.put(BorrowingTable.OVERDUE_DAY, x.getOverdueDay());
        if (x.getStatus() != null)
            values.put(BorrowingTable.STATUS, x.getStatus());
        // update row
        return writeDB.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getId()+"" });
    }

    public int delete(int bookID, int uID)
    { return delete(new String[]{Integer.toString(bookID), Integer.toString(uID)});}

    // TODO : CHECK IF THE BOOK IDS EXIST
    protected List<BorrowingDef> genEntities() {
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
