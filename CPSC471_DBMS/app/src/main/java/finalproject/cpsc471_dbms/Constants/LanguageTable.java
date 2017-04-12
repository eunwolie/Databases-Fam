package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class LanguageTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Languages";
    public static final String ISBN = "LangISBN";
    public static final String LANGUAGE = "Language";

    public static String getTable()
    { return TABLE_NAME; }

}
