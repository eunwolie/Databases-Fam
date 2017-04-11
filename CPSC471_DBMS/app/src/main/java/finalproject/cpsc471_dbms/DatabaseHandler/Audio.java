package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by evech on 2017-03-24.
 */

public class Audio extends IHandler<AudioDef, AudioTable>{
    private static final String WHERE_KEY_EQUALS = AudioTable.ISBN + "=?";

    public Audio(Context context) { writeDB = new _DatabaseHelper(context).getWritableDatabase(); }

    public Audio(SQLiteDatabase writeDB) { this.writeDB = writeDB; }

    public void innerAdd(AudioDef a, ContentValues values)
    {
        values.put(AudioTable.ISBN, a.getIsbn());
        values.put(AudioTable.LENGTH, a.getLength());
    }

    public int delete(int ISBN)
    { return delete(new String[]{Integer.toString(ISBN)}); }

    // TODO : ADJUST TO WORK WITH MATERIAL
    protected List<AudioDef> genEntities() {
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