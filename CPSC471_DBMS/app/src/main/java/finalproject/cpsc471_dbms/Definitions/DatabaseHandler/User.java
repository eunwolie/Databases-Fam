package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.Definitions.UserDef;

/**
 * Created by evech on 2017-03-27.
 */

public class User {
    _DatabaseHelper userdbHelper;
    SQLiteDatabase writeDB;
    SQLiteDatabase readDB;
    private Context aContext;

    private static final String WHERE_ID_EQUALS = UserTable._ID
            + " =?";

    public User(Context context) {
        userdbHelper = _DatabaseHelper.getHelper(aContext);
        writeDB = userdbHelper.getWritableDatabase();
        readDB = userdbHelper.getReadableDatabase();
    }

    public long add(UserDef user) {
        ContentValues values = new ContentValues();
        values.put(UserTable._ID, user.getId());
        values.put(UserTable.USERNAME, user.getUsername());
        values.put(UserTable.PASSWORD, user.getUsername());
        values.put(UserTable.FIRST_NAME, user.getFirstName());
        values.put(UserTable.LAST_NAME, user.getLastName());
        values.put(UserTable.ADDRESS, user.getAddress());
        values.put(UserTable.PHONE, user.getPhone());

        return writeDB.insert(UserTable.TABLE_NAME, null, values);
    }

    public long update(UserDef user) {
        ContentValues values = new ContentValues();
        values.put(UserTable._ID, user.getId());
        values.put(UserTable.USERNAME, user.getUsername());
        values.put(UserTable.PASSWORD, user.getUsername());
        values.put(UserTable.FIRST_NAME, user.getFirstName());
        values.put(UserTable.LAST_NAME, user.getLastName());
        values.put(UserTable.ADDRESS, user.getAddress());
        values.put(UserTable.PHONE, user.getPhone());

        long result = writeDB.update(UserTable.TABLE_NAME, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(user.getId()) });
        return result;

    }

    public int delete(UserDef user) {
        return writeDB.delete(_DatabaseHelper.CREATE_USER_TABLE,
                WHERE_ID_EQUALS, new String[] {user.getId() + "" });
    }

    public List<UserDef> getUsers() {
        List<UserDef> users = new ArrayList<UserDef>();
        Cursor cursor = readDB.query(_DatabaseHelper.CREATE_USER_TABLE,
                new String[] { UserTable._ID,
                        UserTable.FIRST_NAME}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            UserDef u = new UserDef();
            u.setId(cursor.getInt(0));
            u.setFirstName(cursor.getString(1));
            users.add(u);
        }

        cursor.close();

        return users;
    }

    public void loadUsers() {
        List<UserDef> users = getTempUser();

        for (UserDef use : users) {
            add(use);
        }
    }

    private List<UserDef> getTempUser()
    {
        List<UserDef> users = new ArrayList<UserDef>();

        users.add(new UserDef(9001, "alice","ant"));
        users.add(new UserDef(9002, "bob","cat"));
        users.add(new UserDef(9003, "caleb","lee"));
        users.add(new UserDef(9004, "matcha", "tea"));
        users.add(new UserDef(9005, "jake","long"));
        users.add(new UserDef(9006, "anna","marie"));

        return users;
    }

}
