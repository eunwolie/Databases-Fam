package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import finalproject.cpsc471_dbms.Constants.ITable;

/**
 * Created by farra on 2017-04-05.
 */

public abstract class IBasicHandler<A, T extends ITable> {
    protected SQLiteDatabase writeDB;

    protected abstract ContentValues innerAdd(A def);

    public abstract long add(A def);

    // IN ORDER TO GENERATE, FIRST CREATE THE BASE, THEN ADD THE FOREIGN KEYS
    public void generate()
    {
        List<A> items = genEntities();

        for (A item : items) {
            add(item);
        }

        //addForeignKeys();
    }

    protected abstract List<A> genEntities();

    //protected abstract List<A> addForeignKeys();

    public void close()
    { if (writeDB != null) writeDB.close(); }
}
