package finalproject.cpsc471_dbms.DatabaseHandler;

import finalproject.cpsc471_dbms.Constants.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by evech on 2017-03-22.
 */

public class _DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LibraryDatabase";

    public static final String CREATE_ADDRESS_TABLE = "CREATE TABLE"
            + AddressTable.TABLE_NAME + "("
            + AddressTable.NAME + " Text, "
            + "FOREIGN KEY(" + UserTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(id) " + ")";

    public static final String CREATE_ATTENDS_TABLE = "CREATE TABLE"
            + EventAttendanceTable.TABLE_NAME + "("
            + "FOREIGN KEY(" + EventAttendanceTable.SID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(SPONSOR ID) " + ", "
            + "FOREIGN KEY(" + EventAttendanceTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(User ID) " + ")";

    public static final String CREATE_AUDIO_TABLE = "CREATE TABLE"
            + AudioTable.TABLE_NAME + "("
            + AudioTable.LENGTH + " Int, "
            + "FOREIGN KEY(" + AudioTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(ISBN) " + ")";

    public static final String CREATE_AUTHORS_TABLE = "CREATE TABLE"
            + AuthorTable.TABLE_NAME + "("
            + AuthorTable.FIRST_NAME + " Text, "
            + AuthorTable.LAST_NAME + " Text, "
            + AuthorTable.MINIT_NAME + " Text, "
            + "FOREIGN KEY(" + AuthorTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(ISBN) " + ")";

    public static final String CREATE_BORROWS_TABLE = "CREATE TABLE"
            + BorrowingTable.TABLE_NAME + "("
            + BorrowingTable.BORROW_DATE + " Date, "
            + BorrowingTable.RETURN_DATE + " Int, "
            + BorrowingTable.OVERDUE_FEE + " Int, "
            + BorrowingTable.STATUS + " String, "
            + "FOREIGN KEY(" + BorrowingTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(USER ID) " + ", "
            + "FOREIGN KEY(" + BorrowingTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(ISBN) " + ")";

    public static final String CREATE_DONATE_TABLE = "CREATE TABLE"
            + DonationTable.TABLE_NAME+ "("
            + "FOREIGN KEY(" + DonationTable.SID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(SPONSOR ID) " + ", "
            + "FOREIGN KEY(" + DonationTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(ISBN) " + ")";

    public static final String CREATE_EVENT_TABLE = "CREATE TABLE"
            + EventTable.TABLE_NAME + "("
            + EventTable.DESCRIPTION + " String, "
            + EventTable.TIME  + " Int, "
            + EventTable.DATE + " Date, "
            + EventTable.TITLE + " String,"
            + EventTable.SPONSOR_ID + " INTEGER PRIMARY KEY, "
            + "FOREIGN KEY(" + EventTable.HOST + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(ID) " + ")";


    public static final String CREATE_FLOOR_TABLE = "CREATE TABLE"
            + FloorTable.TABLE_NAME + "("
            + FloorTable.NUMBER + " INTEGER PRIMARY KEY, "
            + FloorTable.COMPUTERS + " Int, "
            + "FOREIGN KEY(" + FloorTable.WORK_ID + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(Work ID) " + ")";

    public static final String CREATE_HELPS_TABLE = "CREATE TABLE"
            + LibHelpTable.TABLE_NAME + "("
            + "FOREIGN KEY(" + LibHelpTable.WORK_ID + ") REFERENCES "
            +  LibrarianTable.TABLE_NAME + "(WORK ID) " + ", "
            + "FOREIGN KEY(" + LibHelpTable.USER_ID + ") REFERENCES "
            +  UserTable.TABLE_NAME + "(USER ID) " + ")";

    public static final String CREATE_LIBRARIAN_TABLE = "CREATE TABLE"
            + LibrarianTable.TABLE_NAME + "("
            + LibrarianTable.DESKNO + " Int, "
            + "FOREIGN KEY(" + LibrarianTable.WORK_ID  + ") REFERENCES "
            + UserTable.TABLE_NAME + "(id) " + ")";

    public static final String CREATE_MATERIALS_TABLE = "CREATE TABLE"
            + MaterialTable.DESCRIPTION + "("
            + MaterialTable.AUTHOR + " String, "
            + MaterialTable.TITLE + " String, "
            + MaterialTable.TYPE + " String, "
            + MaterialTable.ISBN + " INTEGER PRIMARY KEY, "
            + MaterialTable.GENRE + " String,"
            + MaterialTable.YEAR_CREATED + " Int, "
            + MaterialTable.LANGUAGE + " String, "
            + MaterialTable.COMPANY + " String, "
            + " ) ";

    public static final String CREATE_MIDDLE_NAME_TABLE = "CREATE TABLE"
            + MiddleNameTable.TABLE_NAME + "("
            + MiddleNameTable.NAME + " Text, "
            + "FOREIGN KEY(" + MiddleNameTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(id) " + ")";

    public static final String CREATE_NAME_TABLE = "CREATE TABLE"
            + NameTable.TABLE_NAME + "("
            + NameTable.FIRST_NAME + " Text, "
            + NameTable.MIDDLE_NAME + " Text, "
            + NameTable.LAST_NAME + " Text, "
            + "FOREIGN KEY(" + NameTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(id) " + ")";

    public static final String CREATE_SECTION_TABLE = "CREATE TABLE"
            + SectionTable.TABLE_NAME + "("
            + SectionTable.GENRE + " STRING PRIMARY KEY, "
            + SectionTable.FLOOR_NUMBER + " Int, "
            + SectionTable.SHELF_NUMBER + " Int, "
            + "FOREIGN KEY(" + SectionTable.FLOOR_NUMBER + ") REFERENCES "
            + FloorTable.TABLE_NAME + "(Floor Number) " + ")";


    public static final String CREATE_SHELF_TABLE = "CREATE TABLE"
            + ShelfTable.TABLE_NAME + "("
            + ShelfTable.NUMBER + " Int, "
            + ShelfTable.ISBN + " INTEGER PRIMARY KEY, "
            + "FOREIGN KEY(" + ShelfTable.GENRE + ") REFERENCES "
            + SectionTable.TABLE_NAME + "(Genre) " + ")";

    public static final String CREATE_SPONSOR_TABLE = "CREATE TABLE"
            + SponsorTable.TABLE_NAME + "("
            + SponsorTable.NAME + " Name, "
            + SponsorTable.REASON + " String, "
            + SponsorTable.SPONSOR_ID + " INTEGER PRIMARY KEY, ) ";

    public static final String CREATE_STAFF_TABLE = "CREATE TABLE"
            + StaffTable.TABLE_NAME + "("
            + StaffTable.WORK_ID + " INTEGER PRIMARY KEY, "
            + StaffTable.SALARY + " Int, "
            + StaffTable.SSN + " Int, "
            + "FOREIGN KEY(" + StaffTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(id) " + ")";

    public static final String CREATE_TIME_TABLE = "CREATE TABLE"
            + EventTimeTable.TABLE_NAME + "("
            + EventTimeTable.START + " Int, "
            + EventTimeTable.END + " Int, "
            + EventTimeTable.TITLE + " Text, "
            + "FOREIGN KEY(" + EventTimeTable.SPONSOR_ID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + " (ID) " + ")";

    public static final String CREATE_TYPES_TABLE = "CREATE TABLE"
            + TypesTable.TABLE_NAME + "("
            + TypesTable.NAME + " Text, "
            + "FOREIGN KEY(" + TypesTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(ISBN) " + ")";

    public static final String CREATE_USER_TABLE = "CREATE TABLE"
            + UserTable.TABLE_NAME + "("
            + UserTable.ID + " INTEGER PRIMARY KEY, "
            + UserTable.NAME + " Text, "
            + UserTable.ADDRESS + " String, "
            + UserTable.PASSWORD + " String, "
            + UserTable.USERNAME + " String, "
            + UserTable.PHONE + " Int, "
            + ")";

    public static final String CREATE_VISUAL_TABLE = "CREATE TABLE"
            + VisualTable.TABLE_NAME + "("
            + VisualTable.PAGE_LENGTH + " Int, "
            + "FOREIGN KEY(" + VisualTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(ISBN) " + ")";

    private static _DatabaseHelper state;

    public static synchronized _DatabaseHelper getHelper(Context context) {
        if (state == null)
            state = new _DatabaseHelper(context);
        return state;
    }

    public _DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onOpen(SQLiteDatabase database) {
        super.onOpen(database);
        if (!database.isReadOnly()){
            database.execSQL("PRAGMA foreign key=ON;");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_ATTENDS_TABLE);
        database.execSQL(CREATE_ADDRESS_TABLE);
        database.execSQL(CREATE_AUDIO_TABLE);
        database.execSQL(CREATE_AUTHORS_TABLE);
        database.execSQL(CREATE_BORROWS_TABLE);
        database.execSQL(CREATE_DONATE_TABLE);
        database.execSQL(CREATE_EVENT_TABLE);
        database.execSQL(CREATE_FLOOR_TABLE);
        database.execSQL(CREATE_HELPS_TABLE);
        database.execSQL(CREATE_LIBRARIAN_TABLE);
        database.execSQL(CREATE_MATERIALS_TABLE);
        database.execSQL(CREATE_MIDDLE_NAME_TABLE);
        database.execSQL(CREATE_SECTION_TABLE);
        database.execSQL(CREATE_SHELF_TABLE);
        database.execSQL(CREATE_SPONSOR_TABLE);
        database.execSQL(CREATE_STAFF_TABLE);
        database.execSQL(CREATE_TIME_TABLE);
        database.execSQL(CREATE_TYPES_TABLE);
        database.execSQL(CREATE_USER_TABLE);
        database.execSQL(CREATE_VISUAL_TABLE);
        database.execSQL(CREATE_NAME_TABLE);
    }

    public void onUpgrade(SQLiteDatabase database, int version1, int version2) {}
}
