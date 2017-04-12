package finalproject.cpsc471_dbms.DatabaseHandler;

import finalproject.cpsc471_dbms.Constants.*;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by evech on 2017-03-22.
 */

/*
 TODO : How the fuck are we going to do domain if it's not supported??
 TODO : HOW TO HANDLE DATES?
 TODO : POPULATE LISTS (enlist William's help later)
 TODO : APPEND DEFAULT VALUES TO CREATE TABLE
 TODO : ADD INCREMENTING IDS FOR ENTITIES

 THINK : BOOK ROOM
 THINK : ADD LIBRARY CARD TO PHONE
 THINK : NOTIFICATION?
 */


public class _DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LibraryDatabase";

    public static final String CREATE_ATTENDS_TABLE = "CREATE TABLE "
            + EventAttendanceTable.TABLE_NAME + "("
            + EventAttendanceTable.UID + " INTEGER, "
            + EventAttendanceTable.START_TIME + " INTEGER, "
            + EventAttendanceTable.DATE + " INTEGER, "
            + EventAttendanceTable.HOST_ID + " INTEGER, "
            + "FOREIGN KEY(" + EventAttendanceTable.UID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") " + ", "
            + "FOREIGN KEY(" + EventAttendanceTable.START_TIME + ") REFERENCES "
            + EventTable.TABLE_NAME + "(" + EventTable.START_TIME + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + EventAttendanceTable.HOST_ID + ") REFERENCES "
            + EventTable.TABLE_NAME + "(" + EventTable.HOST + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + EventAttendanceTable.DATE + ") REFERENCES "
            + EventTable.TABLE_NAME + "(" + EventTable.DATE + ") ON DELETE CASCADE )";

    public static final String CREATE_AUDIO_TABLE = "CREATE TABLE "
            + AudioTable.TABLE_NAME + "("
            + AudioTable.LENGTH + " INTEGER, "
            + AudioTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + AudioTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_AUTHORS_TABLE = "CREATE TABLE "
            + AuthorTable.TABLE_NAME + "("
            + AuthorTable.FIRST_NAME + " TEXT, "
            + AuthorTable.LAST_NAME + " TEXT, "
            + AuthorTable.MINIT_NAME + " TEXT, "
            + AuthorTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + AuthorTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_BORROWS_TABLE = "CREATE TABLE "
            + BorrowingTable.TABLE_NAME + "("
            + BorrowingTable.BORROW_DATE + " INTEGER, "
            + BorrowingTable.RETURN_DATE + " INTEGER, "
            + BorrowingTable.OVERDUE_DAY + " INTEGER, "
            + BorrowingTable.STATUS + " TEXT, "
            + BorrowingTable.ID + " INTEGER, "
            + BorrowingTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + BorrowingTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + BorrowingTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_DONATE_TABLE = "CREATE TABLE "
            + DonationTable.TABLE_NAME + "("
            + DonationTable.AMOUNT_DONATED + " INTEGER, "
            + DonationTable.SID + " INTEGER, "
            + DonationTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + DonationTable.SID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(" + SponsorTable._ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + DonationTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_EVENT_TABLE = "CREATE TABLE "
            + EventTable.TABLE_NAME + "("
            + EventTable.DESCRIPTION + " TEXT, "
            + EventTable.START_TIME + " INTEGER, "
            + EventTable.END_TIME  + " INTEGER, "
            + EventTable.DATE + " INTEGER, "
            + EventTable.TITLE + " TEXT, "
            + EventTable.IMAGE + " BLOB, "
            + EventTable.SID + " INTEGER, "
            + EventTable.HOST + " INTEGER, "
            + "FOREIGN KEY(" + EventTable.SID + ") REFERENCES "
            + SponsorTable.TABLE_NAME + "(" + SponsorTable._ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + EventTable.HOST + ") REFERENCES "
            + StaffTable.TABLE_NAME + "(" + StaffTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_FLOOR_TABLE = "CREATE TABLE "
            + FloorTable.TABLE_NAME + "("
            + FloorTable._ID + " INTEGER PRIMARY KEY, "
            + FloorTable.COMPUTERS + " INTEGER, "
            + FloorTable.WORK_ID + " INTEGER, "
            + "FOREIGN KEY(" + FloorTable.WORK_ID + ") REFERENCES "
            + LibrarianTable.TABLE_NAME + "(" + LibrarianTable.DESKNO + ") ON DELETE CASCADE )";

    public static final String CREATE_HELPS_TABLE = "CREATE TABLE "
            + LibHelpTable.TABLE_NAME + "("
            + LibHelpTable.WORK_ID + " INTEGER, "
            + LibHelpTable.USER_ID + " INTEGER, "
            + "FOREIGN KEY(" + LibHelpTable.WORK_ID + ") REFERENCES "
            +  LibrarianTable.TABLE_NAME + "(" + StaffTable._ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + LibHelpTable.USER_ID + ") REFERENCES "
            +  UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_LANGUAGE_TABLE = "CREATE TABLE "
            + LanguageTable.TABLE_NAME + "("
            + LanguageTable.LANGUAGE + " TEXT, "
            + LanguageTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + LanguageTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_LIBRARIAN_TABLE = "CREATE TABLE "
            + LibrarianTable.TABLE_NAME + "("
            + LibrarianTable.DESKNO + " INTEGER, "
            + LibrarianTable.WORK_ID + " INTEGER, "
            + "FOREIGN KEY(" + LibrarianTable.WORK_ID  + ") REFERENCES "
            + StaffTable.TABLE_NAME + "(" + StaffTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_MATERIALS_TABLE = "CREATE TABLE "
            + MaterialTable.TABLE_NAME + "("
            + MaterialTable.TITLE + " TEXT, "
            + MaterialTable._ID + " INTEGER PRIMARY KEY, "
            + MaterialTable.GENRE + " TEXT NOT NULL,"
            + MaterialTable.TYPE + " TEXT NOT NULL,"
            + MaterialTable.YEAR_CREATED + " INTEGER, "
            + MaterialTable.COMPANY + " TEXT, "
            + MaterialTable.IMAGE + " BLOB, "
            + MaterialTable.SHELF_NO + " INTEGER, "
            + "FOREIGN KEY(" + MaterialTable.SHELF_NO + ") REFERENCES "
            + ShelfTable.TABLE_NAME + "(" + ShelfTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_ON_HOLD_TABLE = "CREATE TABLE "
            + OnHoldTable.TABLE_NAME + "("
            + OnHoldTable.HOLD_DATE + " INTEGER, "
            + OnHoldTable.END_DATE + " INTEGER, "
            + OnHoldTable.STATUS + " TEXT, "
            + OnHoldTable.ID + " INTEGER, "
            + OnHoldTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + OnHoldTable.ID + ") REFERENCES "
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + OnHoldTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_SECTION_TABLE = "CREATE TABLE "
            + SectionTable.TABLE_NAME + "("
            + SectionTable._ID + " INTEGER NOT NULL PRIMARY KEY, "
            + SectionTable.FLOOR_NUMBER + " INTEGER, "
            + "FOREIGN KEY(" + SectionTable.FLOOR_NUMBER + ") REFERENCES "
            + FloorTable.TABLE_NAME + "(" + FloorTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_SHELF_TABLE = "CREATE TABLE "
            + ShelfTable.TABLE_NAME + "("
            + ShelfTable._ID + " INTEGER NOT NULL PRIMARY KEY, "
            + ShelfTable.GENRE + " TEXT, "
            + "FOREIGN KEY(" + ShelfTable.GENRE + ") REFERENCES "
            + SectionTable.TABLE_NAME + "(" + SectionTable._ID + ") ON DELETE CASCADE )";

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
            + UserTable.TABLE_NAME + "(" + UserTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_TYPES_TABLE = "CREATE TABLE "
            + TypesTable.TABLE_NAME + "("
            + TypesTable.NAME + " TEXT, "
            + TypesTable.ISBN + " INTEGER, "
            + "FOREIGN KEY(" + TypesTable.ISBN + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

    public static final String CREATE_USER_TABLE = "CREATE TABLE "
            + UserTable.TABLE_NAME + "("
            + UserTable._ID + " INTEGER NOT NULL PRIMARY KEY, "
            + UserTable.EMAIL + " TEXT UNIQUE, "
            + UserTable.FIRST_NAME + " TEXT NOT NULL, "
            + UserTable.LAST_NAME + " TEXT NOT NULL, "
            + UserTable.ADDRESS + " TEXT, "
            + UserTable.PASSWORD + " TEXT NOT NULL, "
            + UserTable.USERNAME + " TEXT NOT NULL UNIQUE, "
            + UserTable.PHONE + " INTEGER, "
            + UserTable.IMAGE + " BLOB "
            + ")";

    public static final String CREATE_VISUAL_TABLE = "CREATE TABLE "
            + VisualTable.TABLE_NAME + "("
            + VisualTable.PAGE_LENGTH + " INTEGER NOT NULL, "
            + VisualTable.HAS_EBOOK + " INTEGER NOT NULL, "
            + VisualTable.ISBN + " INTEGER NOT NULL, "
            + "FOREIGN KEY(" + VisualTable.ISBN  + ") REFERENCES "
            + MaterialTable.TABLE_NAME + "(" + MaterialTable._ID + ") ON DELETE CASCADE )";

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
        database.execSQL(CREATE_ON_HOLD_TABLE);
        database.execSQL(CREATE_SECTION_TABLE);
        database.execSQL(CREATE_SHELF_TABLE);
        database.execSQL(CREATE_SPONSOR_TABLE);
        database.execSQL(CREATE_STAFF_TABLE);
        database.execSQL(CREATE_TYPES_TABLE);
        database.execSQL(CREATE_USER_TABLE);
        database.execSQL(CREATE_VISUAL_TABLE);
    }

    public void onUpgrade(SQLiteDatabase database, int version1, int version2) {}
}
