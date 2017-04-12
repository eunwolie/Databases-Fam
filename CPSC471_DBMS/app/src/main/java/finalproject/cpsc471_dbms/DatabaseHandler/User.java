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

public class User extends IHandler<UserDef, UserTable>{
    private static final String WHERE_KEY_EQUALS = UserTable._ID + "=?";
    private static int userID = 0;

    public User(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = _DatabaseHelper.getHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(UserDef user)
    {
        ContentValues values = new ContentValues();

        values.put(UserTable.USERNAME, user.getUsername());
        values.put(UserTable.PASSWORD, user.getPassword());
        values.put(UserTable.FIRST_NAME, user.getFirstName());
        values.put(UserTable.LAST_NAME, user.getLastName());
        values.put(UserTable.ADDRESS, user.getAddress());
        values.put(UserTable.PHONE, user.getPhone());
        values.put(UserTable.EMAIL, user.getEmail());
        values.put(UserTable.IMAGE, user.getImage());

        // set the userID within the system
        userID++;

        return values;
    }

    public long add(UserDef def)
    { return writeDB.insert(UserTable.TABLE_NAME, null, innerAdd(def)); }

    public int delete(int id)
    { return delete(new String[]{Integer.toString(id)}); }

    public long update(UserDef user) {

        ContentValues values = new ContentValues();

        if (user.getFirstName() != null)
            values.put(UserTable.FIRST_NAME, user.getFirstName());
        if (user.getLastName() != null)
            values.put(UserTable.LAST_NAME, user.getLastName());
        if (user.getAddress() != null)
            values.put(UserTable.ADDRESS, user.getAddress());
        if (user.getPassword() != null)
            values.put(UserTable.PASSWORD, user.getPassword());
        if (user.getPhone() != -1)
            values.put(UserTable.PHONE, user.getPhone());
        if (user.getImage() != null)
            values.put(UserTable.IMAGE, user.getImage());
        if (user.getEmail() != null)
            values.put(UserTable.EMAIL, user.getEmail());

        String where = UserTable._ID + "=?";

        return writeDB.update(UserTable.TABLE_NAME, values, where,
                new String[]{Integer.toString(user.getId())});

    }

    protected List<UserDef> genEntities() {
        List<UserDef> users = new ArrayList<>();

        
        String[] firstNames = new String[]{
                "Spencer", "Farrah", "Eve", "William", "Erica", "Aerjay" };
        String[] lastNames = new String[]{
                "IsCute", "Urmeneta", "Chen", "Hong", "Aguete", "Italia" };

        String[] usernames = new String[]{
                "theCutest", "noWillToLive", "admin", "oneMoreDay", "ericaa", "NoOneCares"};
        String[] address = new String[]{
                "143 Farrah's Heart Avenue", "404 Spencer's Pants Road",
                "Angel Boulevard", "Tina's Puke", "Anime Con", "Marlborough's Ghetto"
        };
        String[] passwords = new String[]{
                "password", "ilovespencer", "STOP_3dw4rd5puns", "tinalovesme", "imAspyshh", "putangIN4mo"
        };

        int[] phoneNums = new int[]{
                6322348, 911, 3123121, 696969, 4412341, 2070111
        };
        String[] emails = new String[]{
                "spencer.manzon@yahi.com", "farrah.urmeneta@jeemail.com",
                "eve.chen@gg.ca", "william.hong@hottestmail.com",
                "erica.aguete@inlook.com", "aerjay.italia@youcloud.com"
        };

        for (int i = 0; i < firstNames.length; i++)
        {
            users.add(new UserDef(i, firstNames[i], lastNames[i],
                    usernames[i], address[i], passwords[i], phoneNums[i], new byte[]{}, emails[i]));
        }

        return users;
    }

}
