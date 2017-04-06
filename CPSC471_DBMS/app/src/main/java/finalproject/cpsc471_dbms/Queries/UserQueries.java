package finalproject.cpsc471_dbms.Queries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.DatabaseHandler.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by farra on 2017-04-05.
 */

/**
 *
 * handles deletion and update of user
 *
 *
 */

// TODO : Handle multivalued attribute (name, phone?, address?)
// TODO : Ask william what would be the cleanest way to handle information? By ID or by the class?

public class UserQueries {
    SQLiteDatabase db;

    public UserQueries(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param user the user class containing all the information of a new user
     */
    public void addUser(UserDef user)
    {
        ContentValues values = new ContentValues();
        values.put(UserTable._ID, user.getId());
        values.put(UserTable.NAME, user.getName());
        values.put(UserTable.USERNAME, user.getUsername());
        values.put(UserTable.PASSWORD, user.getPassword());
        values.put(UserTable.ADDRESS, user.getAddress());
        values.put(UserTable.PHONE, user.getPhone());
        db.insert(UserTable.TABLE_NAME, null, values);
    }

    /**********************************************************/
    /************************ ASK HERE ************************/
    /**********************************************************/

    /**
     * @param id the id of the desired user
     */
    public void deleteUser(int id)
    {
        db.delete(UserTable.TABLE_NAME, UserTable._ID + "=?",
                new String[]{Integer.toString(id)});
    }

    /**
     * @param user the id of the desired user
     */
    public void deleteUser(UserDef user)
    {
        db.delete(UserTable.TABLE_NAME, UserTable._ID + "=?",
                new String[]{Integer.toString(user.getId())});
    }
}
