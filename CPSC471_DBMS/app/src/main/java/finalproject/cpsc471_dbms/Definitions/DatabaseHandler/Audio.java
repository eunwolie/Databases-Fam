package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by evech on 2017-03-24.
 */

public class Audio {
    private static final String WHERE_KEY_EQUALS = AudioTable.ISBN + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Audio(Context context) {
        this.context = context;
    }

    public long add(AudioDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(AudioTable.ISBN, x.getIsbn());
        values.put(AudioTable.LENGTH, x.getLength());

        return db.insert(AudioTable.TABLE_NAME, null, values);
    }

    public AudioDef get(int isbn) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(AudioTable.TABLE_NAME,
                new String[] {
                        AudioTable.ISBN,
                        AudioTable.LENGTH},
                WHERE_KEY_EQUALS,
                new String[] {isbn+""},
                null,null,null,null);
        if(cur!=null)
            cur.moveToFirst();
        AudioDef x = new AudioDef(
                Integer.parseInt(cur.getString(0)),
                Integer.parseInt(cur.getString(1)));
        return x;
    }

    public long update(AudioDef x) {
        db = new _DatabaseHelper(context).getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(AudioTable.ISBN, x.getIsbn());
        values.put(AudioTable.LENGTH, x.getLength());
        // update row
        return db.update(AudioTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getIsbn()+"" });
    }

    public void loadEntities() {
        // This populates the list momentarily
        List<AudioDef> list = genEntities();

        for (AudioDef x : list) {
            add(x);
        }
    }

    private List<AudioDef> genEntities() {
        List<AudioDef> list = new ArrayList<>();

        list.add(new AudioDef(1111, 5));
        list.add(new AudioDef(1112, 2));
        list.add(new AudioDef(1113, 100));
        list.add(new AudioDef(1114, 2));
        list.add(new AudioDef(1115, 6));
        list.add(new AudioDef(1116, 123));

        return list;
    }
}