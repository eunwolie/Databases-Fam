package finalproject.cpsc471_dbms.DatabaseHandler;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.SponsorTable;
import finalproject.cpsc471_dbms.Definitions.SponsorDef;

/**
 * Created by evech on 2017-03-27.
 */

public class Sponsor {

    // TODO : Figure out how to do getContext()
    private _DatabaseHelper sponsordbHelper;
    private SQLiteDatabase writeDB;

    private static final String WHERE_ID_EQUALS = SponsorTable.SPONSOR_ID
            + " =?";

    public Sponsor(Context context) {
        sponsordbHelper = new _DatabaseHelper(context);
        writeDB = sponsordbHelper.getWritableDatabase();
    }

    public long save(SponsorDef sponsor) {
        ContentValues values = new ContentValues();
        values.put(SponsorTable.NAME, sponsor.getName());
        return writeDB.insert(_DatabaseHelper.CREATE_SPONSOR_TABLE, null, values);
    }

    public long update(SponsorDef sponsor) {
        ContentValues values = new ContentValues();
        values.put(SponsorTable.NAME, sponsor.getName());

        long result = writeDB.update(_DatabaseHelper.CREATE_SPONSOR_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(sponsor.getSponsorId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteSponsor(SponsorDef sponsor) {
        return writeDB.delete(_DatabaseHelper.CREATE_SPONSOR_TABLE,
                WHERE_ID_EQUALS, new String[] {sponsor.getSponsorId() + "" });
    }

    public List<SponsorDef> getSponsors() {
        List<SponsorDef> users = new ArrayList<SponsorDef>();
        Cursor cursor = writeDB.query(_DatabaseHelper.CREATE_SPONSOR_TABLE,
                new String[] { SponsorTable.SPONSOR_ID,
                        SponsorTable.NAME}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            SponsorDef user = new SponsorDef();
            user.setSponsorId(cursor.getInt(0));
            user.setName(cursor.getString(1));
            users.add(user);
        }

        cursor.close();

        return users;
    }

    public void loadSponsors() {

        // This populates the list momentarily
        List<SponsorDef> sponsors = getTempSponsor();

        for (SponsorDef use : sponsors) {
            ContentValues values = new ContentValues();
            values.put(SponsorTable.NAME, use.getName());

            writeDB.insert(_DatabaseHelper.CREATE_SPONSOR_TABLE, null, values);
        }
    }

    private List<SponsorDef> getTempSponsor()
    {
        List<SponsorDef> sponsors = new ArrayList<SponsorDef>();

        String r1 = "Gotta get rid of some books";
        String r2 = "Boss' mom told us to";
        String r3 = "They threatened to throw us in jail for fraud without this event";
        String r4 = "We love children";
        String r5 = "We're here to scope for milfs";
        String r6 = "We want to further the education of the general public";

        sponsors.add(new SponsorDef("Fook these Books", r1));
        sponsors.add(new SponsorDef("Heavenly Mattresses", r2));
        sponsors.add(new SponsorDef("Books and Crooks", r3));
        sponsors.add(new SponsorDef("Books got us Shook", r4));
        sponsors.add(new SponsorDef("Looking for Love", r5));
        sponsors.add(new SponsorDef("Hooks on Books", r6));

        return sponsors;
    }


}
