package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class OnHoldTable implements BaseColumns {
    public static final String TABLE_NAME = "BooksOnHold";
    public static final String HOLD_DATE = "OnHoldDate";
    public static final String END_DATE = "EndDate";
    public static final String ID = "ID";
    public static final String ISBN = "ISBN";
}
