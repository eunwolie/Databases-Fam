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
    private static final String WHERE_KEY_EQUALS = "";

    protected int delete(String[] keys)
    { return writeDB.delete(T.TABLE_NAME, WHERE_KEY_EQUALS, keys); }
}
