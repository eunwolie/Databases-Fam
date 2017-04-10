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

public class Sponsor {
    private static final String WHERE_ID_EQUALS = DatabaseHelper.SPONSOR_COLUMN_SPONSOR_ID
            + " =?";

    public Sponsor(Context context) {
        super(context);
    }

    public long save(SponsorDefinitionsClass sponsor) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.SPONSOR_COLUMN_NAME, SponsorDefinitionsClass.getName());
        return database.insert(DatabaseHelper.CREATE_SPONSOR_TABLE, null, values);
    }

    public long update(SponsorDefinitionsClass sponsor) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.SPONSOR_COLUMN_NAME, SponsorDefinitionsClass.getName());

        long result = database.update(DatabaseHelper.CREATE_SPONSOR_TABLE, values,
                WHERE_ID_EQUALS,
                new String[] { String.valueOf(SponsorDefinitionsClass.getSponsorId()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteUser(SponsorDefinitionsClass sponsor) {
        return database.delete(DatabaseHelper.CREATE_SPONSOR_TABLE,
                WHERE_ID_EQUALS, new String[] {SponsorDefinitionsClass.getSponsorId() + "" });
    }

    public List<UserDefinitionsClass> getUsers() {
        List<SponsorDefinitionsClass> users = new ArrayList<SponsorDefinitionsClass>();
        Cursor cursor = database.query(DatabaseHelper.CREATE_SPONSOR_TABLE,
                new String[] { DatabaseHelper.SPONSOR_COLUMN_SPONSOR_ID,
                        DatabaseHelper.SPONSOR_COLUMN_NAME}, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            SponsorDefinitionsClass users = new SponsorDefinitionsClass();
            users.setSponsorId(cursor.getInt(0));
            users.setName(cursor.getString(1));
            users.add(users);
        }
        return users;
    }

    public void loadUsers() {
        SponsorDefinitionsClass user = new SponsorDefinitionsClass("User 1");
        SponsorDefinitionsClass user1 = new SponsorDefinitionsClass("User 2");
        SponsorDefinitionsClass user2 = new SponsorDefinitionsClass("User 3");
        SponsorDefinitionsClass user3 = new SponsorDefinitionsClass("User 4");
        SponsorDefinitionsClass user4 = new SponsorDefinitionsClass("User 5");
        SponsorDefinitionsClass user5 = new SponsorDefinitionsClass("User 6");

        List<SponsorDefinitionsClass> users = new ArrayList<SponsorDefinitionsClass>();
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        for (SponsorDefinitionsClass use : users) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.SPONSOR_COLUMN_NAME, use.getName());
            database.insert(DatabaseHelper.CREATE_SPONSOR_TABLE, null, values);
        }
    }


}
