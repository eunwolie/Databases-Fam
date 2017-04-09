package finalproject.cpsc471_dbms.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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


public class LoginQueries {
    SQLiteDatabase readDB;

    public LoginQueries(Context context)
    {
        readDB = new _DatabaseHelper(context).getReadableDatabase();
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
    public int checkCredentials(String username, int password)
    {
        int id = -1;

        String[] want = new String[] {UserTable._ID};
        String where = UserTable.USERNAME + "=? AND " + UserTable.PASSWORD + "=?";
        String[] selectArgs = new String[]{username, Integer.toString(password)};

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
}
