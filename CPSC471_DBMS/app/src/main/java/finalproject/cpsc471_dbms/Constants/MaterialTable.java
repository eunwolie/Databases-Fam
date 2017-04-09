package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class MaterialTable implements BaseColumns {
    public static final String TABLE_NAME = "Material";
    public static final String _ID = "ISBN";
    public static final String DESCRIPTION = "Description";
    public static final String AUTHOR = "Creator";
    public static final String TITLE = "Title";
    public static final String TYPE = "Type";
    public static final String GENRE = "Genre";
    public static final String YEAR_CREATED = "YearCreated";

    // TODO : Make a table because books might have different languages (unfortunately)
    public static final String LANGUAGE = "Language";
    public static final String COMPANY = "Company";
    public static final String SHELF_NO = "ShelfNumber";
}
