package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class Staff {
    protected SQLiteDatabase database;
    private DatabaseHelper dataHelper;
    private Context aContext;

    public Staff(Context context) {
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