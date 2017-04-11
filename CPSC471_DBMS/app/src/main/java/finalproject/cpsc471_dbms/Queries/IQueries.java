package finalproject.cpsc471_dbms.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import finalproject.cpsc471_dbms.Constants.StaffTable;
import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.DatabaseHandler._DatabaseHelper;
/**
 * Created by farra on 2017-04-05.
 */

public abstract class IQueries {
    protected SQLiteDatabase readDB;
    protected SQLiteDatabase writeDB;

    public void close()
    {
        if (readDB != null) readDB.close();
        if (writeDB != null) writeDB.close();
    }
}
