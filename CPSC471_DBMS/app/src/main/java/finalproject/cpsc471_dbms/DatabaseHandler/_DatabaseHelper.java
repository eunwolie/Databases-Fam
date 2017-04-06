package finalproject.cpsc471_dbms.DatabaseHandler;

import finalproject.cpsc471_dbms.Constants.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by evech on 2017-03-22.
 */

// DECIDED TO PLACE KEYS OF 1 INTO N

// TODO : How the fuck are we going to do domain if it's not supported??
// Likely going to just hardcode shit
// TODO : Figure out how often need to close database after getWritableDatabase
// TODO : Add pictures to the material database (use blob and byte[] and byte stream)
// TODO : Create an ON-HOLD relation, which would have a pick-up date, and
//          hold-date, and user, and isbn
// TODO : need to keep track of the rows - use the _COUNT?


public class _DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LibraryDatabase";

    public static final String CREATE_ADDRESS_TABLE = "CREATE TABLE "
            + AddressTable.TABLE_NAME + "("
            + AddressTable.ADDRESS + " TEXT, "
            + AddressTable.USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + AddressTable.USER_ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ")";

    public static final String CREATE_ATTENDS_TABLE = "CREATE TABLE "
            + EventAttendanceTable.TABLE_NAME + "("
            + EventAttendanceTable.UID + " INTEGER, "
            + EventAttendanceTable.START_TIME + " INTEGER, "
            + EventAttendanceTable.DATE + " INTEGER, "
            + "FOREIGN KEY(" + EventAttendanceTable.UID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ", "
            + "FOREIGN KEY(" + EventAttendanceTable.START_TIME + ") REFERENCES "
            + EventTable.TABLE_NAME + "(" + EventTable.START_TIME + ") " + ", "
            + "FOREIGN KEY(" + EventAttendanceTable.DATE + ") REFERENCES "
            + EventTable.TABLE_NAME + "(" + EventTable.DATE + ") )";

    public static final String CREATE_AUDIO_TABLE = "CREATE TABLE "
            + AudioTable.TABLE_NAME + "("
            + AudioTable.LENGTH + " INTEGER, "
            + AudioTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + AudioTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") )";

    public static final String CREATE_AUTHORS_TABLE = "CREATE TABLE "
            + AuthorTable.TABLE_NAME + "("
            + AuthorTable.FIRST_NAME + " TEXT, "
            + AuthorTable.LAST_NAME + " TEXT, "
            + AuthorTable.MINIT_NAME + " TEXT, "
            + AuthorTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + AuthorTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") )";

    public static final String CREATE_BORROWS_TABLE = "CREATE TABLE "
            + BorrowingTable.TABLE_NAME + "("
            + BorrowingTable.BORROW_DATE + " INTEGER, "
            + BorrowingTable.RETURN_DATE + " INTEGER, "
            + BorrowingTable.OVERDUE_FEE + " INTEGER, "
            + BorrowingTable.STATUS + " TEXT, "
            + BorrowingTable.ID + " INTEGER, "
            + BorrowingTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + BorrowingTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ", "
            + "FOREIGN KEY(" + BorrowingTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") )";

    public static final String CREATE_DONATE_TABLE = "CREATE TABLE "
            + DonationTable.TABLE_NAME + "("
            + DonationTable.SID + " INTEGER, "
            + DonationTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + DonationTable.SID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(" + SponsorTable._ID + ") " + ", "
            + "FOREIGN KEY(" + DonationTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") " + ")";

    public static final String CREATE_EVENT_TABLE = "CREATE TABLE "
            + EventTable.TABLE_NAME + "("
            + EventTable.DESCRIPTION + " TEXT, "
            + EventTable.START_TIME + " INTEGER, "
            + EventTable.END_TIME  + " INTEGER, "
            + EventTable.DATE + " INTEGER, "
            + EventTable.TITLE + " TEXT,"
            + EventTable.SID + " INTEGER, "
            + EventTable.HOST + " INTEGER, "
            + "FOREIGN KEY(" + EventTable.SID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(" + SponsorTable._ID + ") , "
            + "FOREIGN KEY(" + EventTable.HOST + ") REFERENCES "
            + StaffTable.TABLE_NAME + "(" + StaffTable._ID + ") )";

    public static final String CREATE_FLOOR_TABLE = "CREATE TABLE "
            + FloorTable.TABLE_NAME + "("
            + FloorTable._ID + " INTEGER PRIMARY KEY, "
            + FloorTable.COMPUTERS + " INTEGER, "
            + FloorTable.WORK_ID + " INTEGER, "
            + "FOREIGN KEY(" + FloorTable.WORK_ID + ") REFERENCES "
            + LibrarianTable.TABLE_NAME + "(" + LibrarianTable.DESKNO + ") )";

    public static final String CREATE_HELPS_TABLE = "CREATE TABLE "
            + LibHelpTable.TABLE_NAME + "("
            + LibHelpTable.WORK_ID + " INTEGER, "
            + LibHelpTable.USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + LibHelpTable.WORK_ID + ") REFERENCES "
            +  LibrarianTable.TABLE_NAME + "(" + StaffTable._ID + ") " + ", "
            + "FOREIGN KEY(" + LibHelpTable.USER_ID + ") REFERENCES "
            +  UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ")";

    public static final String CREATE_LANGUAGE_TABLE = "CREATE TABLE "
            + LanguageTable.TABLE_NAME + "("
            + LanguageTable.LANGUAGE + " TEXT, "
            + LanguageTable.ISBN + " INTEGER, "
            + "FO REIGN KEY(" + LanguageTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") " + ")";

    public static final String CREATE_LIBRARIAN_TABLE = "CREATE TABLE "
            + LibrarianTable.TABLE_NAME + "("
            + LibrarianTable.DESKNO + " INTEGER, "
            + LibrarianTable.WORK_ID + " INTEGER, "
            + "FOREIGN KEY(" + LibrarianTable.WORK_ID  + ") REFERENCES "
            + StaffTable.TABLE_NAME + "(" + StaffTable._ID + ") " + ")";

    public static final String CREATE_MATERIALS_TABLE = "CREATE TABLE "
            + MaterialTable.DESCRIPTION + "("
            + MaterialTable.AUTHOR + " TEXT, "
            + MaterialTable.TITLE + " TEXT, "
            + MaterialTable.TYPE + " TEXT, "
            + MaterialTable._ID + " INTEGER PRIMARY KEY, "
            + MaterialTable.GENRE + " TEXT,"
            + MaterialTable.YEAR_CREATED + " INTEGER, "
            + MaterialTable.LANGUAGE + " TEXT, "
            + MaterialTable.COMPANY + " TEXT, "
            + MaterialTable.SHELF_NO + " INTEGER, "
            + "FOREIGN KEY(" + MaterialTable.SHELF_NO + ") REFERENCES "
            + ShelfTable.TABLE_NAME + "(" + ShelfTable._ID + ") "
            + ")";

    public static final String CREATE_MIDDLE_NAME_TABLE = "CREATE TABLE "
            + MiddleNameTable.TABLE_NAME + "("
            + MiddleNameTable.NAME + " TEXT, "
            + MiddleNameTable.USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + MiddleNameTable.USER_ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ")";

    public static final String CREATE_NAME_TABLE = "CREATE TABLE "
            + NameTable.TABLE_NAME + "("
            + NameTable.FIRST_NAME + " TEXT, "
            + NameTable.MINIT_NAME + " TEXT, "
            + NameTable.LAST_NAME + " TEXT, "
            + NameTable._ID + " INTEGER, "
            + "FOREIGN KEY(" + NameTable._ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ")";

    public static final String CREATE_SECTION_TABLE = "CREATE TABLE "
            + SectionTable.TABLE_NAME + "("
            + SectionTable.SHELF_NUMBER + " INTEGER, "
            + SectionTable._ID + "INTEGER NOT NULL PRIMARY KEY"
            + SectionTable.SHELF_NUMBER + " INTEGER, "
            + SectionTable.FLOOR_NUMBER + " INTEGER, "
            + "FOREIGN KEY(" + SectionTable.SHELF_NUMBER + ") REFERENCES "
            + ShelfTable.TABLE_NAME + "(" + ShelfTable._ID + ") , "
            + "FOREIGN KEY(" + SectionTable.FLOOR_NUMBER + ") REFERENCES "
            + FloorTable.TABLE_NAME + "(" + FloorTable._ID + ") )";

    public static final String CREATE_SHELF_TABLE = "CREATE TABLE "
            + ShelfTable.TABLE_NAME + "("
            + ShelfTable._ID + " INTEGER NOT NULL PRIMARY KEY, "
            + ShelfTable.ISBN + " INTEGER, "
            + ShelfTable.GENRE + " TEXT, "
            + "FOREIGN KEY(" + ShelfTable.GENRE + ") REFERENCES "
            + SectionTable.TABLE_NAME + "(" + SectionTable._ID + ") )";

    public static final String CREATE_SPONSOR_TABLE = "CREATE TABLE "
            + SponsorTable.TABLE_NAME + "("
            + SponsorTable.NAME + " TEXT, "
            + SponsorTable.REASON + " TEXT, "
            + SponsorTable._ID + " INTEGER NOT NULL PRIMARY KEY )";

    public static final String CREATE_STAFF_TABLE = "CREATE TABLE "
            + StaffTable.TABLE_NAME + "("
            + StaffTable._ID + " INTEGER NOT NULL PRIMARY KEY, "
            + StaffTable.SALARY + " INTEGER, "
            + StaffTable.SSN + " INTEGER NOT NULL UNIQUE, "
            + StaffTable.USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + StaffTable.USER_ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") )";

    public static final String CREATE_TYPES_TABLE = "CREATE TABLE "
            + TypesTable.TABLE_NAME + "("
            + TypesTable.NAME + " TEXT, "
            + TypesTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + TypesTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") " + ")";

    public static final String CREATE_USER_TABLE = "CREATE TABLE "
            + UserTable.TABLE_NAME + "("
            + UserTable._ID + " INTEGER NOT NULL PRIMARY KEY, "
            + UserTable.NAME + " TEXT NOT NULL, "
            + UserTable.ADDRESS + " TEXT, "
            + UserTable.PASSWORD + " TEXT NOT NULL, "
            + UserTable.USERNAME + " TEXT NOT NULL UNIQUE, "
            + UserTable.PHONE + " INTEGER "
            + ")";

    public static final String CREATE_VISUAL_TABLE = "CREATE TABLE "
            + VisualTable.TABLE_NAME + "("
            + VisualTable.SHELF_NO + " INTEGER, "
            + VisualTable.PAGE_LENGTH + " INTEGER NOT NULL, "
            + VisualTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + VisualTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") " + ")";

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
        database.execSQL(CREATE_LANGUAGE_TABLE);
        database.execSQL(CREATE_LIBRARIAN_TABLE);
        database.execSQL(CREATE_MATERIALS_TABLE);
        database.execSQL(CREATE_MIDDLE_NAME_TABLE);
        database.execSQL(CREATE_SECTION_TABLE);
        database.execSQL(CREATE_SHELF_TABLE);
        database.execSQL(CREATE_SPONSOR_TABLE);
        database.execSQL(CREATE_STAFF_TABLE);
        database.execSQL(CREATE_TYPES_TABLE);
        database.execSQL(CREATE_USER_TABLE);
        database.execSQL(CREATE_VISUAL_TABLE);
        database.execSQL(CREATE_NAME_TABLE);
    }

    public void onUpgrade(SQLiteDatabase database, int version1, int version2) {}
}
