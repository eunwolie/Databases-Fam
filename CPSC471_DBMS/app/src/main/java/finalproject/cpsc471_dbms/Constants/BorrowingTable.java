package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class BorrowingTable implements BaseColumns {
    public static final String TABLE_NAME = "Borrows";
    public static final String BORROW_DATE = "Borrow Date";
    public static final String RETURN_DATE = "Return Date";
    public static final String OVERDUE_FEE = "Overdue Fee";
    public static final String STATUS = "Status";
    public static final String ID = "ID";
    public static final String ISBN = "ISBN";
}
