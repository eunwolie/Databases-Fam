package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.List;

import finalproject.cpsc471_dbms.Constants.ITable;

/**
 * Created by farra on 2017-04-05.
 */

public abstract class IHandler<A, T extends ITable> extends IBasicHandler<A, T>{
    private static String WHERE_KEY_EQUALS = "";

    public IHandler(String key){ WHERE_KEY_EQUALS = key; }

    protected int delete(String[] keys)
    { return writeDB.delete(T.TABLE_NAME, WHERE_KEY_EQUALS, keys); }
}
