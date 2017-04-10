package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.AudioTable;
import finalproject.cpsc471_dbms.Constants.DonationTable;
import finalproject.cpsc471_dbms.Definitions.AudioDef;
import finalproject.cpsc471_dbms.Definitions.DonationDef;


/**
 * Created by evech on 2017-03-25.
 */

public class Donation {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = DonationTable.SID + "=? AND " + DonationTable.ISBN + "=?";

    public Donation(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void addDonation(DonationDef don) {
        ContentValues values = new ContentValues();
        values.put(DonationTable.SID, don.getsId());
        values.put(DonationTable.ISBN, don.getIsbn());
        db.insert(DonationTable.TABLE_NAME, null, values);
    }

    public int updateDonation (DonationDef don) {
        ContentValues values = new ContentValues();
        values.put(DonationTable.SID, don.getsId());
        values.put(DonationTable.ISBN, don.getIsbn());
        // update row
        int result = db.update(DonationTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(don.getsId()), String.valueOf(don.getIsbn()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    public int deleteDonation(int sid, int isbn) {
        return db.delete(DonationTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(sid), Integer.toString(isbn)});
    }

    public int deleteDonation(DonationDef don) {
        return db.delete(DonationTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {don.getsId() + "",don.getIsbn()+""});
    }
}
