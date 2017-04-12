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

public class Language extends IBasicHandler<LanguageDef, LanguageTable>{
    private static final String WHERE_KEY_EQUALS = LanguageTable.ISBN + "=?";

    public Language(Context context) {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
    }

    public ContentValues innerAdd(LanguageDef l)
    {
        ContentValues values = new ContentValues();
        values.put(LanguageTable.ISBN, l.getIsbn());
        values.put(LanguageTable.LANGUAGE, l.getLanguage());
        return values;
    }

    public long add(LanguageDef def)
    { return writeDB.insert(LanguageTable.TABLE_NAME, null, innerAdd(def)); }

    protected List<LanguageDef> genEntities()
    {
        return new ArrayList<LanguageDef>();
    }
}
