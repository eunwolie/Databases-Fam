package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.UserTable;
import finalproject.cpsc471_dbms.Constants.VisualTable;
import finalproject.cpsc471_dbms.Definitions.UserDef;
import finalproject.cpsc471_dbms.Definitions.VisualsDef;

/**
 * Created by evech on 2017-03-27.
 */

public class User {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = UserTable._ID + "=?";

    public User(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
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

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int updateUser(UserDef user) {
        ContentValues values = new ContentValues();
        values.put(UserTable._ID, user.getId());
        values.put(UserTable.NAME, user.getName());
        values.put(UserTable.USERNAME, user.getUsername());
        values.put(UserTable.PASSWORD, user.getPassword());
        values.put(UserTable.ADDRESS, user.getAddress());
        values.put(UserTable.PHONE, user.getPhone());
        // update row
        int result = db.update(UserTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(user.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteUser(int id) {
        db.delete(UserTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{Integer.toString(id)});
    }

    public int deleteUser(UserDef user) {
        return db.delete(UserTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {user.getId() + "" });
    }

    public List<UserDef> getUsers() {
        List<UserDef> users = new ArrayList<UserDef>();
        Cursor cursor = readDB.query(_DatabaseHelper.CREATE_USER_TABLE,
                new String[] { UserTable._ID,
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
