package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.SectionTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.SectionDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Sections {
    private static final String WHERE_KEY_EQUALS = SectionTable._ID + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Sections(Context context) {
        this.context = context;
    }

    public long add(SectionDef x) {
        ContentValues values = new ContentValues();
        values.put(SectionTable._ID, x.getGenre());
        values.put(SectionTable.FLOOR_NUMBER, x.getfNo());

        db = new _DatabaseHelper(context).getWritableDatabase();
        return db.insert(SectionTable.TABLE_NAME, null, values);
    }

    public SectionDef get(String genre) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(SectionTable.TABLE_NAME,
                new String[] {
                        SectionTable._ID,
                        SectionTable.FLOOR_NUMBER,
                },
                WHERE_KEY_EQUALS,
                new String[] {genre},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        SectionDef x = new SectionDef(
                cur.getString(0),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public int update(SectionDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SectionTable._ID, x.getGenre());
        values.put(SectionTable.FLOOR_NUMBER, x.getfNo());

        // update row
        return db.update(SectionTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getGenre()+"" });
    }

    public void delete(SectionDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        db.delete(SectionTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[] {x.getGenre()+""});
        db.close();
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<SectionDef> list = genEntities();

        for (SectionDef x : list) {
            add(x);
        }
    }

    private List<SectionDef> genEntities() {
        List<SectionDef> list = new ArrayList<>();

        list.add(new SectionDef("fantasy",1));
        list.add(new SectionDef("horror",1));
        list.add(new SectionDef("humour",2));
        list.add(new SectionDef("humour",3));
        list.add(new SectionDef("biography",4));
        list.add(new SectionDef("biography",5));

        return list;
    }
}
