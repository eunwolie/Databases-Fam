package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import finalproject.cpsc471_dbms.Constants.ITable;

/**
 * Created by farra on 2017-04-05.
 */

public abstract class IBasicHandler<A, T extends ITable> {
    private static final String WHERE_KEY_EQUALS = "";
    private static final String[] keys = null;

    protected SQLiteDatabase writeDB;

    protected abstract void innerAdd(A def, ContentValues values);

    public long add(A def)
    {
        ContentValues values = new ContentValues();

        innerAdd(def, values);

        return writeDB.insert(T.TABLE_NAME, null, values);
    }

    public void generate()
    {
        List<A> items = genEntities();

        for (A item : items) {
            add(item);
        }
    }

    protected abstract List<A> genEntities();

    public void close()
    { if (writeDB != null) writeDB.close(); }
}
