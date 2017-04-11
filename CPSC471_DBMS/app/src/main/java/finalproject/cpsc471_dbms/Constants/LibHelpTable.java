package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class LibHelpTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Helps";
    public static final String USER_ID = "HelpedUserID";
    public static final String WORK_ID = "HelpWorkID";
}
