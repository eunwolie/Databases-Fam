package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;

/**
 * Created by evech on 2017-03-22.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "LibraryDatabase";

    public static final String USERS_TABLE_NAME = "UserDefinitionsClass";
    public static final String USERS_COLUMN_ID = "ID";
    public static final String USERS_COLUMN_NAME = "Name";

    public static final String USERS_COLUMN_USERNAME = "Username";
    public static final String USERS_COLUMN_ADDRESS = "Address";
    public static final String USERS_COLUMN_PASSWORD = "Password";
    public static final String USERS_COLUMN_PHONE = "Phone Number";

    public static final String NAME_TABLE_NAME = "Name"
    public static final String NAME_COLUMN_FIRST_NAME = "First Name";
    public static final String NAME_COLUMN_MIDDLE_NAME = "Middle Name";
    public static final String NAME_COLUMN_LAST_NAME = "Last Name";
    public static final String NAME_COLUMN_WORK_ID = "Work ID";

    public static final String STAFF_TABLE_NAME = "Staff";
    public static final String STAFF_COLUMN_ID = "Work ID";
    public static final String STAFF_COLUMN_SALARY = "Salary";
    public static final String STAFF_COLUMN_SSN = "SSN";
    public static final String STAFF_COLUMN_WORK_ID = "Work ID";

    public static final String LIBRARIAN_TABLE_NAME = "Librarian";
    public static final String LIBRARIAN_COLUMN_DESKNO = "Desk No";
    public static final String LIBRARIAN_COLUMN_WORK_ID = "Work ID";


    public static final String MIDDLE_NAME_TABLE = "Middle Name";
    public static final String MIDDLE_COLUMN_NAME = "Name";
    public static final String MIDDLE_COLUMN_ID = "Work ID";

    public static final String ADDRESS_NAME_TABLE = "Address";
    public static final String ADDRESS_COLUMN_NAMES = "Name";
    public static final String ADDRESS_USER_ID = "UserDefinitionsClass ID";

    public static final String CREATE_ADDRESS_TABLE = "CREATE TABLE"
            + ADDRESS_NAME_TABLE + "("
            + ADDRESS_COLUMN_NAMES + " Text, "
            + "FOREIGN KEY(" + ADDRESS_USER_ID + ") REFERENCES "
            + USERS_TABLE_NAME + "(id) " + ")";

    public static final String CREATE_USER_TABLE = "CREATE TABLE"
            + USERS_TABLE_NAME + "("
            + USERS_COLUMN_ID + " INTEGER PRIMARY KEY, "
            + USERS_COLUMN_NAME + " Text, "
            + USERS_COLUMN_ADDRESS + " String, "
            + USERS_COLUMN_PASSWORD + " String, "
            + USERS_COLUMN_USERNAME + " String, "
            + USERS_COLUMN_PHONE + " Int, "
            + ")";

    public static final String CREATE_NAME_TABLE = "CREATE TABLE"
            + NAME_TABLE_NAME + "("
            + NAME_COLUMN_FIRST_NAME + " Text, "
            + NAME_COLUMN_MIDDLE_NAME + " Text, "
            + NAME_COLUMN_LAST_NAME + " Text, "
            + "FOREIGN KEY(" + NAME_COLUMN_WORK_ID + ") REFERENCES "
            + USERS_TABLE_NAME + "(id) " + ")";

    public static final String CREATE_STAFF_TABLE = "CREATE TABLE"
            + STAFF_TABLE_NAME + "("
            + STAFF_COLUMN_ID + " INTEGER PRIMARY KEY, "
            + STAFF_COLUMN_SALARY + " Int, "
            + STAFF_COLUMN_SSN + " Int, "
            + "FOREIGN KEY(" + STAFF_COLUMN_WORK_ID + ") REFERENCES "
            + USERS_TABLE_NAME + "(id) " + ")";

    public static final String CREATE_LIBRARIAN_TABLE = "CREATE TABLE"
            + LIBRARIAN_TABLE_NAME + "("
            + LIBRARIAN_COLUMN_DESKNO + " Int, "
            + "FOREIGN KEY(" + LIBRARIAN_COLUMN_WORK_ID  + ") REFERENCES "
            + USERS_TABLE_NAME + "(id) " + ")";

    public static final String CREATE_MIDDLE_NAME_TABLE = "CREATE TABLE"
            + MIDDLE_NAME_TABLE + "("
            + MIDDLE_COLUMN_NAME + " Text, "
            + "FOREIGN KEY(" + MIDDLE_COLUMN_ID + ") REFERENCES "
            + USERS_TABLE_NAME + "(id) " + ")";


    public static final String MATERIALS_TABLE_NAME = "Material";
    public static final String MATERIALS_COLUMN_DESCRIPTION = "Description";
    public static final String MATERIALS_COLUMN_AUTHOR = "Author/Creator";
    public static final String MATERIALS_COLUMN_TITLE = "Title";
    public static final String MATERIALS_COLUMN_TYPE = "Type";
    public static final String MATERIALS_COLUMN_IBSN = "IBSN";
    public static final String MATERIALS_COLUMN_GENRE = "Genre";
    public static final String MATERIALS_COLUMN_YEAR_CREATED = "Year of Creation";
    public static final String MATERIALS_COLUMN_LANGUAGE = "Language";
    public static final String MATERIALS_COLUMN_COMPANY = "Company";

    public static final String AUDIO_TABLE_NAME = "Audio";
    public static final String AUDIO_COLUMN_LENGTH = "Length";
    public static final String AUDIO_COLUMN_ISBN = "ISBN";


    public static final String VISUAL_TABLE_NAME = "Visual";
    public static final String VISUAL_COLUMN_PAGES = "Number of Page";
    public static final String VISUAL_COLUMN_ISBN = "ISBN";


    public static final String AUTHORS_TABLE_NAME = "Author";
    public static final String AUTHORS_COLUMN_FIRST_NAME = "First Name";
    public static final String AUTHORS_COLUMN_MINIT_NAME = "Middle Name";
    public static final String AUTHORS_COLUMN_LAST_NAME = "Last Name";
    public static final String AUTHORS_COLUMN_ISBN = "ISBN"

    public static final String TYPES = "Type";
    public static final String TYPES_COLUMN_NAME = "Type Name";
    public static final String TYPES_COLUMN_ISBN = "ISBN";

    public static final String CREATE_TYPES_TABLE = "CREATE TABLE"
            + TYPES + "("
            + TYPES_COLUMN_NAME + " Text, "
            + "FOREIGN KEY(" + TYPES_COLUMN_ISBN + ") REFERENCES "
            + MATERIALS_TABLE_NAME + "(ISBN) " + ")";


    public static final String CREATE_MATERIALS_TABLE = "CREATE TABLE"
            + MATERIALS_COLUMN_DESCRIPTION + "("
            + MATERIALS_COLUMN_AUTHOR + " String, "
            + MATERIALS_COLUMN_TITLE + " String, "
            + MATERIALS_COLUMN_TYPE + " String, "
            + MATERIALS_COLUMN_IBSN + " INTEGER PRIMARY KEY, "
            + MATERIALS_COLUMN_GENRE + " String,"
            + MATERIALS_COLUMN_YEAR_CREATED + " Int, "
            + MATERIALS_COLUMN_LANGUAGE + " String, "
            + MATERIALS_COLUMN_COMPANY + " String, "
            + " ) ";

    public static final String CREATE_AUDIO_TABLE = "CREATE TABLE"
            + AUDIO_TABLE_NAME + "("
            + AUDIO_COLUMN_LENGTH + " Int, "
            + "FOREIGN KEY(" + AUDIO_COLUMN_ISBN  + ") REFERENCES "
            + MATERIALS_TABLE_NAME + "(ISBN) " + ")";


    public static final String CREATE_VISUAL_TABLE = "CREATE TABLE"
            + VISUAL_TABLE_NAME + "("
            + VISUAL_COLUMN_PAGES + " Int, "
            + "FOREIGN KEY(" + VISUAL_COLUMN_ISBN  + ") REFERENCES "
            + MATERIALS_TABLE_NAME + "(ISBN) " + ")";


    public static final String CREATE_AUTHORS_TABLE = "CREATE TABLE"
            + AUTHORS_TABLE_NAME + "("
            + AUTHORS_COLUMN_FIRST_NAME + " Text, "
            + AUTHORS_COLUMN_LAST_NAME + " Text, "
            + AUTHORS_COLUMN_MINIT_NAME + " Text, "
            + "FOREIGN KEY(" + AUTHORS_COLUMN_ISBN  + ") REFERENCES "
            + MATERIALS_TABLE_NAME + "(ISBN) " + ")";

    public static final String EVENT_TABLE_NAME = "Event";
    public static final String SPONSOR_TABLE_NAME = "SponsorDefinitonsClass";

    public static final String FLOOR_TABLE_NAME = "Floor";
    public static final String FLOOR_COLUMN_NUMBER = "Number";
    public static final String FLOOR_COLUMN_COMPUTERS = "Computers";
    public static final String FLOOR_COLUMN_WORK_ID = "Work ID";

    public static final String SECTION_TABLE_NAME = "Section";
    public static final String SECTION_COLUMN_GENRE = "Genre";
    public static final String SECTION_COLUMN_SHELF_NUMBER = "Shelf Number";
    public static final String SECTION_COLUMN_FLOOR_NUMBER = "Floor Number";

    public static final String SHELF_TABLE_NAME = "Shelf";
    public static final String SHELF_COLUMN_NUMBER = "Shelf Number";
    public static final String SHELF_COLUMN_ISBN = "ISBN";
    public static final String SHELF_COLUMN_GENRE = "Genre";


    public static final String CREATE_FLOOR_TABLE = "CREATE TABLE"
            + FLOOR_TABLE_NAME + "("
            + FLOOR_COLUMN_NUMBER + " INTEGER PRIMARY KEY, "
            + FLOOR_COLUMN_COMPUTERS + " Int, "
            + "FOREIGN KEY(" + FLOOR_COLUMN_WORK_ID + ") REFERENCES "
            + MATERIALS_TABLE_NAME + "(Work ID) " + ")";


    public static final String CREATE_SECTION_TABLE = "CREATE TABLE"
            + SECTION_TABLE_NAME + "("
            + SECTION_COLUMN_GENRE + " STRING PRIMARY KEY, "
            + SECTION_COLUMN_FLOOR_NUMBER + " Int, "
            + SECTION_COLUMN_SHELF_NUMBER + " Int, "
            + "FOREIGN KEY(" + SECTION_COLUMN_FLOOR_NUMBER + ") REFERENCES "
            + FLOOR_TABLE_NAME + "(Floor Number) " + ")";


    public static final String CREATE_SHELF_TABLE = "CREATE TABLE"
            + SHELF_TABLE_NAME + "("
            + SHELF_COLUMN_NUMBER + " Int, "
            + SHELF_COLUMN_ISBN + " INTEGER PRIMARY KEY, "
            + "FOREIGN KEY(" + SHELF_COLUMN_GENRE + ") REFERENCES "
            + SECTION_TABLE_NAME + "(Genre) " + ")";

    public static final String SPONSOR_COLUMN_NAME = "Name";
    public static final String SPONSOR_COLUMN_REASON = "Reason";
    public static final String SPONSOR_COLUMN_SPONSOR_ID = "SponsorDefinitonsClass ID";

    public static final String CREATE_SPONSOR_TABLE = "CREATE TABLE"
            + SPONSOR_TABLE_NAME + "("
            + SPONSOR_COLUMN_NAME + " Name, "
            + SPONSOR_COLUMN_REASON + " String, "
            + SPONSOR_COLUMN_SPONSOR_ID + " INTEGER PRIMARY KEY, ) ";

    public static final String EVENT_COLUMN_DESCRIPTION = "Description";
    public static final String EVENT_COLUMN_TIME = "Time";
    public static final String EVENT_COLUMN_DATE = "Date";
    public static final String EVENT_COLUMN_TITLE = "Event Name";
    public static final String EVENT_COLUMN_SPONSOR_ID = "SponsorDefinitonsClass ID";
    public static final String EVENT_COLUMN_HOST = "Work ID";

    public static final String TIME_TABLE_NAME = "Time";
    public static final String TIME_COLUMN_START = "Start";
    public static final String TIME_COLUMN_END = "End";
    public static final String TIME_COLUMN_SPONSOR_ID = "SponsorDefinitonsClass ID";
    public static final String TIME_COLUMN_TITLE = "Title";


    public static final String CREATE_EVENT_TABLE = "CREATE TABLE"
            + EVENT_TABLE_NAME + "("
            + EVENT_COLUMN_DESCRIPTION + " String, "
            + EVENT_COLUMN_TIME  + " Int, "
            + EVENT_COLUMN_DATE + " Date, "
            + EVENT_COLUMN_TITLE + " String,"
            + EVENT_COLUMN_SPONSOR_ID + " INTEGER PRIMARY KEY, "
            + "FOREIGN KEY(" + EVENT_COLUMN_HOST + ") REFERENCES "
            + SPONSOR_TABLE_NAME + "(ID) " + ")";

    public static final String CREATE_TIME_TABLE = "CREATE TABLE"
            + TIME_TABLE_NAME + "("
            + TIME_COLUMN_START + " Int, "
            + TIME_COLUMN_END + " Int, "
            + TIME_COLUMN_TITLE + " Text, "
            + "FOREIGN KEY(" + TIME_COLUMN_SPONSOR_ID + ") REFERENCES "
            + SPONSOR_TABLE_NAME + " (ID) " + ")";

    public static final String HELPS_RELATION_TABLE = "Helps";
    public static final String HELPS_COLUMN_USER_ID = "UserDefinitionsClass ID";
    public static final String HELPS_COLUMN_WORK_ID = "Work ID";

    public static final String BORROWS_RELATION_TABLE = "Borrows";
    public static final String BORROWS_COLUMN_BORROW_DATE = "Borrow Date";
    public static final String BORROWS_COLUMN_RETURN_DATE = "Return Date";
    public static final String BORROWS_COLUMN_OVERDUE_FEE = "Overdue Fee";
    public static final String BORROWS_COLUMN_STATUS = "Status";
    public static final String BORROWS_COLUMN_ID = "ID";
    public static final String BORROWS_COLUMN_ISBN = "ISBN";

    public static final String DONATES_RELATION_TABLE = "Donates";
    public static final String DONATES_COLUMN_SID = "SponsorDefinitonsClass ID";
    public static final String DONATES_COLUMN_ISBN = "ISBN";

    public static final String ATTENDS_RELATION_TABLE = "Attends";
    public static final String ATTENDS_COLUMN_SID = "SponsorDefinitonsClass ID";
    public static final String ATTENDS_COLUMN_ID = "UserDefinitionsClass ID";


    public static final String CREATE_HELPS_TABLE = "CREATE TABLE"
            + HELPS_RELATION_TABLE + "("
            + "FOREIGN KEY(" + HELPS_COLUMN_WORK_ID + " " + + HELPS_COLUMN_USER_ID + ") REFERENCES "
            + LIBRARIAN_TABLE_NAME, USERS_TABLE_NAME + "(WORK ID, USER ID) " + ");

    public static final String CREATE_BORROWS_TABLE = "CREATE TABLE"
            + BORROWS_RELATION_TABLE + "("
            + BORROWS_COLUMN_BORROW_DATE + " Date, "
            + BORROWS_COLUMN_RETURN_DATE + " Int, "
            + BORROWS_COLUMN_OVERDUE_FEE + " Int, "
            + BORROWS_COLUMN_STATUS + " String, "
            + "FOREIGN KEY(" + BORROWS_COLUMN_ID + " " + + BORROWS_COLUMN_ISBN + ") REFERENCES "
            + USERS_TABLE_NAME, MATERIALS_TABLE_NAME + "(USER ID, ISBN) " + ");


    public static final String CREATE_DONATE_TABLE = "CREATE TABLE"
            + DONATES_RELATION_TABLE + "("
            + "FOREIGN KEY(" + DONATES_COLUMN_SID + " " + + DONATES_COLUMN_ISBN + ") REFERENCES "
            + SPONSOR_TABLE_NAME, MATERIALS_TABLE_NAME + "(SPONSOR ID, ISBN) " + ");

    public static final String CREATE_ATTENDS_TABLE = "CREATE TABLE"
            + ATTENDS_RELATION_TABLE + "("
            + "FOREIGN KEY(" + ATTENDS_COLUMN_SID + " " + + ATTENDS_COLUMN_ID + ") REFERENCES "
            + SPONSOR_TABLE_NAME, USERS_TABLE_NAME + "(SPONSOR ID, User ID) " + ");


    private static DatabaseHelper state;

    private static synchronized DatabaseHelper getHelper(Context context) {
        if (state == null)
            state = new DatabaseHelper(context);
        return state;
    }

    public DatabaseHelper(Context context){
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
