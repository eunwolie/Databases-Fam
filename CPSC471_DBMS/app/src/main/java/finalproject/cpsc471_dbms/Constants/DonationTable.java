package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class DonationTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Donates";
    public static final String SID = "DonatorID";
    public static final String AMOUNT_DONATED = "AmountDonated";

    public static String getTable()
    { return TABLE_NAME; }

}
