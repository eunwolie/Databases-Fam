package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.AudioTable;
import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.Definitions.AudioDef;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Borrows {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = BorrowingTable.ID + "=?";

    public Borrows(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void addBorrows(BorrowingDef bor) {
        ContentValues values = new ContentValues();
        values.put(BorrowingTable.BORROW_DATE, bor.getBorrowDate());
        values.put(BorrowingTable.RETURN_DATE, bor.getReturnDate());
        values.put(BorrowingTable.OVERDUE_DAY, bor.getOverdueDay());
        values.put(BorrowingTable.ID, bor.getId());
        values.put(BorrowingTable.ISBN, bor.getIsbn());
        db.insert(BorrowingTable.TABLE_NAME, null, values);
    }

    public int updateBorrows(BorrowingDef bor) {
        ContentValues values = new ContentValues();
        values.put(BorrowingTable.BORROW_DATE, bor.getBorrowDate());
        values.put(BorrowingTable.RETURN_DATE, bor.getReturnDate());
        values.put(BorrowingTable.OVERDUE_DAY, bor.getOverdueDay());
        values.put(BorrowingTable.ID, bor.getId());
        values.put(BorrowingTable.ISBN, bor.getIsbn());
        // update row
        int result = db.update(BorrowingTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(bor.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int deleteBorrows(int id) {
        return db.delete(BorrowingTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(id)});
    }

    public int deleteBorrows(BorrowingDef bor) {
        return db.delete(BorrowingTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {bor.getId() + "" });
    }

}
