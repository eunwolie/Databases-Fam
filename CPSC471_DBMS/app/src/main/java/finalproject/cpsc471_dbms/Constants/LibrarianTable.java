package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

import finalproject.cpsc471_dbms.DatabaseHandler.Librarian;

/**
 * Created by farra on 2017-04-02.
 */

public class LibrarianTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Librarian";
    public static final String WORK_ID = "WorkID";
    public static final String DESKNO = "DeskNo";

    public static String getTable()
    { return TABLE_NAME; }
}
