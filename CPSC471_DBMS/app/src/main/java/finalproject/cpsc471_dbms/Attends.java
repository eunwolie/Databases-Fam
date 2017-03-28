package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by evech on 2017-03-25.
 */

public class Attends {
    protected SQLiteDatabase database;
    private DatabaseHelper dataHelper;
    private Context aContext;

    public Attends(Context context) {
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
