package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class SectionTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Section";
    public static final String NAME = "Genre";
    public static final String _ID = "sectID";
    public static final String FLOOR_NUMBER = "FloorNumber";

    public static String getTable()
    { return TABLE_NAME; }

}
