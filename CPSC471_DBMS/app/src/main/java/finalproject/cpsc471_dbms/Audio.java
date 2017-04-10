package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by evech on 2017-03-24.
 */

public class Audio {
    protected SQLiteDatabase database;
    private DatabaseHelper dataHelper;
    private Context aContext;

    public Audio(Context context) {
        this.aContext = context;
        dataHelper = DatabaseHelper.getHelper(aContext);
        open();

    }

    public void open() throws SQLException {
        if(dataHelper == null)
            dataHelper = DatabaseHelper.getHelper(aContext);
        database = dataHelper.getWritableDatabase();
    }

}