package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

// Each event of different times will have own ID


public class EventTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Event";
    public static final String DESCRIPTION = "eDescription";
    public static final String START_TIME = "StartTime";
    public static final String END_TIME = "EndTime";
    public static final String DATE = "Date";
    public static final String TITLE = "EventName";
    public static final String IMAGE = "Image";
    public static final String SID = "SponsorID";
    public static final String HOST = "HostWorkID";

    public static String getTable()
    { return TABLE_NAME; }

}