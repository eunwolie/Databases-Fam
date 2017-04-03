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

    private static final String WHERE_ID_EQUALS = UserTable.ID
            + " =?";

    public User(Context context) {
        userdbHelper = _DatabaseHelper.getHelper(aContext);
        writeDB = userdbHelper.getWritableDatabase();
        readDB = userdbHelper.getReadableDatabase();
    }

    public long save(UserDef user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.NAME, user.getName());
        return writeDB.insert(_DatabaseHelper.CREATE_USER_TABLE, null, values);
    }

    public long update(UserDef user) {
        ContentValues values = new ContentValues();
        values.put(UserTable.NAME, user.getName());

        long result = readDB.update(_DatabaseHelper.CREATE_USER_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(user.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteUser(UserDef user) {
        return writeDB.delete(_DatabaseHelper.CREATE_USER_TABLE,
                WHERE_ID_EQUALS, new String[] {user.getId() + "" });
    }

    public List<UserDef> getUsers() {
        List<UserDef> users = new ArrayList<UserDef>();
        Cursor cursor = readDB.query(_DatabaseHelper.CREATE_USER_TABLE,
                new String[] { UserTable.ID,
                        UserTable.NAME}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            UserDef u = new UserDef();
            u.setId(cursor.getInt(0));
            u.setName(cursor.getString(1));
            users.add(u);
        }

        cursor.close();

        return users;
    }

    public void loadUsers() {
        List<UserDef> users = getTempUser();

        for (UserDef use : users) {
            ContentValues values = new ContentValues();
            values.put(UserTable.NAME, use.getName());
            writeDB.insert(_DatabaseHelper.CREATE_USER_TABLE, null, values);
        }
    }

    private List<UserDef> getTempUser()
    {
        List<UserDef> users = new ArrayList<UserDef>();

        String r1 = "Nelson Wong";
        String r2 = "M Eve P";
        String r3 = "Erican Handeldis";
        String r4 = "Wilyam Right";
        String r5 = "Fera Shells";
        String r6 = "Spencer is Cute";

        users.add(new UserDef(1, r1));
        users.add(new UserDef(2, r2));
        users.add(new UserDef(3, r3));
        users.add(new UserDef(4, r4));
        users.add(new UserDef(5, r5));
        users.add(new UserDef(6, r6));

        return users;
    }

}
