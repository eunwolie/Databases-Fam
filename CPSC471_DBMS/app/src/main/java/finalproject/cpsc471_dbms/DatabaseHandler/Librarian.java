package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by evech on 2017-03-25.
 */

public class Librarian {
    protected SQLiteDatabase database;
    private _DatabaseHelper dataHelper;
    private Context aContext;

    public Librarian(Context context) {
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
