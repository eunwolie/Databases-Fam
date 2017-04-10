package finalproject.cpsc471_dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evech on 2017-03-27.
 */

public class User {
    private static final String WHERE_ID_EQUALS = DatabaseHelper.USERS_COLUMN_ID
            + " =?";

    public User(Context context) {
        super(context);
    }

    public long save(UserDefinitionsClass user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USERS_COLUMN_NAME, UserDefinitionsClass.getName());
        return database.insert(DatabaseHelper.CREATE_USER_TABLE, null, values);
    }

    public long update(UserDefinitionsClass user) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.USERS_COLUMN_NAME, UserDefinitionsClass.getName());

        long result = database.update(DatabaseHelper.CREATE_USER_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(UserDefinitionsClass.getId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteUser(UserDefinitionsClass user) {
        return database.delete(DatabaseHelper.CREATE_USER_TABLE,
                WHERE_ID_EQUALS, new String[] {UserDefinitionsClass.getId() + "" });
    }

    public List<UserDefinitionsClass> getUsers() {
        List<UserDefinitionsClass> users = new ArrayList<UserDefinitionsClass>();
        Cursor cursor = database.query(DatabaseHelper.CREATE_USER_TABLE,
                new String[] { DatabaseHelper.USERS_COLUMN_ID,
                        DatabaseHelper.USERS_COLUMN_NAME}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            UserDefinitionsClass users = new UserDefinitionsClass();
            users.setId(cursor.getInt(0));
            users.setName(cursor.getString(1));
            users.add(users);
        }
        return users;
    }

    public void loadUsers() {
        UserDefinitionsClass user = new UserDefinitionsClass("User 1");
        UserDefinitionsClass user1 = new UserDefinitionsClass("User 2");
        UserDefinitionsClass user2 = new UserDefinitionsClass("User 3");
        UserDefinitionsClass user3 = new UserDefinitionsClass("User 4");
        UserDefinitionsClass user4 = new UserDefinitionsClass("User 5");
        UserDefinitionsClass user5 = new UserDefinitionsClass("User 6");

        List<UserDefinitionsClass> users = new ArrayList<UserDefinitionsClass>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        for (UserDefinitionsClass use : users) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.USERS_COLUMN_NAME, use.getName());
            database.insert(DatabaseHelper.CREATE_MATERIALS_TABLE, null, values);
        }
    }

}
