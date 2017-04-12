package finalproject.cpsc471_dbms.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import finalproject.cpsc471_dbms.Constants.SponsorTable;
import finalproject.cpsc471_dbms.Constants.StaffTable;
import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.DatabaseHandler.*;
/**
 * Created by farra on 2017-04-05.
 */

/**
 *
 * Queries about the login screen
 *
 *
 */


public class LoginQueries extends IQueries{
    private SQLiteDatabase readDB;

    public LoginQueries(Context context)
    {
        readDB = new _DatabaseHelper(context).getReadableDatabase();
    }

    public boolean usernameExists(String username)
    {
        Cursor cursor = readDB.query(true, UserTable.TABLE_NAME,
                new String[]{"COUNT(" + UserTable.USERNAME + ")"},
                UserTable.USERNAME + "=?",
                new String[]{username},
                null, null, null, null);

        if (cursor.moveToNext())
        {
            cursor.close();
            return true;
        }
        return false;
    }

    public boolean emailExists(String email)
    {
        Cursor cursor = readDB.query(true, UserTable.TABLE_NAME,
                new String[]{"COUNT(" + UserTable.EMAIL + ")"},
                UserTable.EMAIL + "=?",
                new String[]{email},
                null, null, null, null);

        if (cursor.moveToNext())
        {
            cursor.close();
            return true;
        }
        return false;
    }

    /**
     * @param username
     * @param password
     * @return UserID if it is the correct user, -1 otherwise
     *
     * Makes query in database to check if the corresponding
     * username and password match
     *
     */
    public int checkCredentials(String username, String password)
    {
        int id = -1;

        String[] want = new String[] {UserTable._ID};
        String where = UserTable.USERNAME + "=? AND " + UserTable.PASSWORD + "=?";
        String[] selectArgs = new String[]{username, password};

        Cursor cursor = readDB.query(UserTable.TABLE_NAME, want, where, selectArgs,
                null, null, null );

        if (cursor != null)
        {
            id = cursor.getInt(cursor.getColumnIndex(UserTable._ID));
            cursor.close();
        }

        return id;
    }

    /**
     * @param userID
     * @return True if they are a regular user, False if they are staff
     */
    public boolean isRegular(int userID)
    {
        String[] wanted = new String[] {UserTable._ID};
        String where = UserTable._ID + "=? AND " + StaffTable.USER_ID + "=?";
        String[] selectArgs = new String[]{Integer.toString(userID),Integer.toString(userID)};

        Cursor cursor = readDB.query(UserTable.TABLE_NAME + " , " + StaffTable.TABLE_NAME,
                wanted, where, selectArgs,
                null, null, null );

        if (cursor != null)
        {
            cursor.close();
            return false;
        }
        return true;
    }

    public boolean isExistingSponsor(int sponsorID, String username)
    {
        String where = SponsorTable._ID + "=? AND " + SponsorTable.NAME + "=?";
        String[] selectArgs = new String[]{Integer.toString(sponsorID),username};

        Cursor cursor = readDB.query(UserTable.TABLE_NAME + " , " + StaffTable.TABLE_NAME,
                null, where, selectArgs,
                null, null, null );

        if (cursor != null)
        {
            cursor.close();
            return false;
        }
        return true;
    }
}
