package finalproject.cpsc471_dbms.DatabaseHandler;

import android.database.sqlite.SQLiteDatabase;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.SectionTable;
import finalproject.cpsc471_dbms.Constants.SponsorTable;
import finalproject.cpsc471_dbms.Definitions.SponsorDef;

/**
 * Created by evech on 2017-03-27.
 */

public class Sponsor extends IHandler<SponsorDef, SponsorTable>{
    private static final String WHERE_KEY_EQUALS = SponsorTable._ID + " =?";

    public Sponsor(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public ContentValues innerAdd(SponsorDef sponsor)
    {
        ContentValues values = new ContentValues();
        values.put(SponsorTable.NAME, sponsor.getName());
        values.put(SponsorTable.REASON, sponsor.getReason());
        return values;
    }

    public long add(SponsorDef def)
    { return writeDB.insert(SponsorTable.TABLE_NAME, null, innerAdd(def)); }

    public int delete(int id)
    { return delete(new String[]{Integer.toString(id)}); }

    protected List<SponsorDef> genEntities()
    {
        List<SponsorDef> sponsors = new ArrayList<>();

        String r1 = "Gotta get rid of some books";
        String r2 = "Boss' mom told us to";
        String r3 = "They threatened to throw us in jail for fraud without this event";
        String r4 = "We love children";
        String r5 = "We're here to scope for milfs";
        String r6 = "We want to further the education of the general public";

        sponsors.add(new SponsorDef(5001, "Fook these Books", r1));
        sponsors.add(new SponsorDef(5002, "Heavenly Mattresses", r2));
        sponsors.add(new SponsorDef(5003, "Books and Crooks", r3));
        sponsors.add(new SponsorDef(5004, "Books got us Shook", r4));
        sponsors.add(new SponsorDef(5005, "Looking for Love", r5));
        sponsors.add(new SponsorDef(5006, "Hooks on Books", r6));

        return sponsors;
    }


}
