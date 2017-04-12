package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class ShelfTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Shelf";
    public static final String SECT_ID = "SectionID";
    public static final String _ID = "ShelfNumber";

    public static String getTable()
    { return TABLE_NAME; }
}
