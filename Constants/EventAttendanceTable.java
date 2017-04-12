package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class EventAttendanceTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "EventAttendance";
    public static final String UID = "UserAttendID";
    public static final String START_TIME = "eventStartTime";
    public static final String DATE = "eventDate";
    public static final String HOST_ID = "HostID";
}
