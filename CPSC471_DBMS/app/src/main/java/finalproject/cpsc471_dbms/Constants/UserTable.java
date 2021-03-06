package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class UserTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "User";
    public static final String _ID = "ID";
    public static final String EMAIL = "Email";
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String USERNAME = "Username";
    public static final String ADDRESS = "Address";
    public static final String PASSWORD = "Password";
    public static final String PHONE = "PhoneNumber";
    public static final String IMAGE = "Image";

    public static String getTable()
    { return TABLE_NAME; }
}
