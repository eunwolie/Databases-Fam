package finalproject.cpsc471_dbms.Constants;

import android.provider.BaseColumns;

/**
 * Created by farra on 2017-04-02.
 */

public class MaterialTable extends ITable implements BaseColumns {
    public static final String TABLE_NAME = "Material";
    public static final String _ID = "ISBN";
    public static final String DESCRIPTION = "Description";
    public static final String TITLE = "Title";
    public static final String GENRE = "Genre";
    public static final String TYPE = "Types";
    public static final String YEAR_CREATED = "YearCreated";
    public static final String COMPANY = "Company";
    public static final String IMAGE = "Image";
    public static final String SHELF_NO = "ShelfNo";
}
