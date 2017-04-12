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

public class Donation extends IHandler<DonationDef, DonationTable>{
    private static final String WHERE_KEY_EQUALS = DonationTable.SID + "=?";

    public Donation(Context context) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    protected void innerAdd(DonationDef d, ContentValues values)
    {
        values.put(DonationTable.SID, d.getsId());
        values.put(DonationTable.ISBN, d.getIsbn());
    }

    public DonationDef get(int SID) {

        Cursor cur = writeDB.query(DonationTable.TABLE_NAME,
                new String[] {DonationTable.AMOUNT_DONATED},
                WHERE_KEY_EQUALS,
                new String[] {Integer.toString(SID)},
                null,null,null,null);

        DonationDef d = new DonationDef();

        if(cur.moveToNext()) {
            d.setBookAmount(cur.getInt(cur.getColumnIndex(DonationTable.AMOUNT_DONATED)));
            d.setId(SID);
            cur.close();
        }
        return d;
    }

    public long update(DonationDef x) {
        // retrieve the original book record
        DonationDef d = get(x.getsId());

        // update by incrementing the amount of books accordingly
        ContentValues values = new ContentValues();
        values.put(DonationTable.AMOUNT_DONATED, x.getBookAmount() + d.getBookAmount());
        return writeDB.update(DonationTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getIsbn()+"" });
    }

    public int delete(int SID) {
        return delete(new String[]{Integer.toString(SID)});
    }

    protected List<DonationDef> genEntities() {
        List<DonationDef> list = new ArrayList<>();

        int[] bookAmounts = new int[]{2,1,3,200,14,9};

        for (int i = 0; i < bookAmounts.length; i++)
            list.add(new DonationDef(5000 + i, 1110 + i, bookAmounts[i]));

        return list;
    }
}
