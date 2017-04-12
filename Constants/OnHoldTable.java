package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class OnHoldTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "BooksOnHold";
    public static final String HOLD_DATE = "OnHoldDate";
    public static final String END_DATE = "EndDate";
    public static final String STATUS = "OnHoldStatus";
    public static final String ID = "HoldingID";
    public static final String ISBN = "HeldISBN";
}
