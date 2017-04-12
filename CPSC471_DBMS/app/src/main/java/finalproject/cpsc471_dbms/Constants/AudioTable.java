package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 201`7-04-02.
 */

public class AudioTable extends ITable implements BaseColumns {

    public static final String TABLE_NAME = "Audio";
    public static final String ISBN = "audioISBN";
    public static final String LENGTH = "Length";

    public static String getTable()
    { return TABLE_NAME; }

}
