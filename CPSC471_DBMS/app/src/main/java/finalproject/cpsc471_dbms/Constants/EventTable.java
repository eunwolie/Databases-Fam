package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

// Each event of different times will have own ID


public class EventTable implements BaseColumns {
    public static final String TABLE_NAME = "Event";
    public static final String DESCRIPTION = "Description";
    public static final String START_TIME = "Start Time";
    public static final String END_TIME = "End Time";
    public static final String DATE = "Date";
    public static final String TITLE = "Event Name";
    public static final String SID = "Sponsor ID";
    public static final String HOST = "Work ID";
}
