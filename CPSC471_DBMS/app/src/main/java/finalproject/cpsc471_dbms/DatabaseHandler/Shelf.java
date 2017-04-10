package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by evech on 2017-03-24.
 */

public class Shelf{
    protected SQLiteDatabase database;
    private _DatabaseHelper dataHelper;
    private Context aContext;

    public Shelf(Context context) {
        this.aContext = context;
        dataHelper = _DatabaseHelper.getHelper(aContext);
        open();

    }

    public void open() throws SQLException {
        if(dataHelper == null)
            dataHelper = _DatabaseHelper.getHelper(aContext);
        database = dataHelper.getWritableDatabase();
    }

}