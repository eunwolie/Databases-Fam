package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class AuthorTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Author";
    public static final String FIRST_NAME = "aFirstName";
    public static final String MINIT_NAME = "aMiddleName";
    public static final String LAST_NAME = "aLastName";
    public static final String ISBN = "bookISBN";
}
