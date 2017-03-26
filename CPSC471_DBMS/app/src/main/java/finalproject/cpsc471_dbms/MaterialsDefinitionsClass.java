package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by evech on 2017-03-24.
 */

public class MaterialsDefinitionsClass {
    protected SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    public MaterialsDefinitionsClass(Context context) {
        this.context = context;
        dbHelper = DatabaseHelper.getHelper(context);
        open();

    }

    public void open() throws SQLException {
        if(dbHelper == null)
            dbHelper = DatabaseHelper.getHelper(context);
        database = dbHelper.getWritableDatabase();
    }
}
