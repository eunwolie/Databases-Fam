package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class FloorTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Floor";
    public static final String _ID = "fNumber";
    public static final String COMPUTERS = "HasComputer";
    public static final String WORK_ID = "WorkID";
}
