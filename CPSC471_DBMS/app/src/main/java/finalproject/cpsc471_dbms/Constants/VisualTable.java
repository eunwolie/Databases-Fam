package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class VisualTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Visual";
    public static final String ISBN = "visualISBN";
    public static final String PAGE_LENGTH = "PageNum";
    public static final String HAS_EBOOK = "HasEBook";
}
