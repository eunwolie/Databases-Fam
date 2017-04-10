package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by evech on 2017-03-25.
 */

public class Language {
    private static final String WHERE_KEY_EQUALS = LanguageTable.ISBN + "=?";
    private SQLiteDatabase db;
    private Context context;

    public Language(Context context) {
        this.context = context;
    }

    public long add(LanguageDef x) {
        ContentValues values = new ContentValues();
        values.put(LanguageTable.ISBN, x.getIsbn());
        values.put(LanguageTable.LANGUAGE, x.getLanguage());

        db = new _DatabaseHelper(context).getWritableDatabase();
        long result = db.insert(LanguageTable.TABLE_NAME, null, values);
        db.close();
        return result;
    }

    public LanguageDef get(int id) {
        db = new _DatabaseHelper(context).getReadableDatabase();

        Cursor cur = db.query(LanguageTable.TABLE_NAME,
                new String[] {
                        LanguageTable.ISBN,
                        LanguageTable.LANGUAGE,},
                WHERE_KEY_EQUALS,
                new String[] {id+""},
                null,null,null,null);

        LanguageDef x = new LanguageDef();

        if(cur!=null) {
            cur.moveToFirst();
            x.setLanguage(cur.getString(0));
            x.setIsbn(cur.getInt(1));
            cur.close();
        }
        return x;
    }
}
