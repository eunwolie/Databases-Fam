package finalproject.cpsc471_dbms.Queries;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.Definitions.*;
import finalproject.cpsc471_dbms.DatabaseHandler.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by farra on 2017-04-05.
 */

/**
 *
 * Queries about the user profile screen
 *
 *
 */

// TODO : Handle multivalued attribute (name, )

public class UserProfileQueries {
    private SQLiteDatabase db;
    private SQLiteDatabase writeDB;
    private int userID;

    public UserProfileQueries(Context context, int userID)
    {
        db = new _DatabaseHelper(context).getReadableDatabase();
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
        this.userID = userID;
    }

    private Cursor getUserCursor()
    {
        String where = UserTable._ID + "=?";

        return db.query(UserTable.TABLE_NAME, null, where, new String[]{Integer.toString(userID)},
                null, null, null );
    }

    private void closeUserCursor(Cursor cursor)
    {
        cursor.close();
    }

    public StaffDef getStaffInfo()
    {
        Cursor cursor = db.query(StaffTable.TABLE_NAME,
                null,
                StaffTable.USER_ID + "=?",
                new String[]{Integer.toString(userID)},
                null, null, null, null);

        StaffDef staff = new StaffDef();

        if (cursor.moveToNext()) {
            staff.setSalary(cursor.getInt(cursor.getColumnIndex(StaffTable.SALARY)));
            staff.setSsn(cursor.getInt(cursor.getColumnIndex(StaffTable.SSN)));
            staff.setWorkId(cursor.getInt(cursor.getColumnIndex(StaffTable.USER_ID)));

            cursor.close();
        }
        return staff;
    }

    public UserDef getUserInfo()
    {
        Cursor cursor = getUserCursor();

        UserDef user = new UserDef();

        if (cursor.moveToNext()) {
            user.setFirstName(cursor.getString(cursor.getColumnIndex(UserTable.FIRST_NAME)));
            //user.setMinitName(cursor.getString(cursor.getColumnIndex(UserTable.NAME)));
            user.setLastName(cursor.getString(cursor.getColumnIndex(UserTable.LAST_NAME)));
            user.setUsername(cursor.getString(cursor.getColumnIndex(UserTable.USERNAME)));
            user.setAddress(cursor.getString(cursor.getColumnIndex(UserTable.ADDRESS)));
            user.setPassword(cursor.getString(cursor.getColumnIndex(UserTable.PASSWORD)));
            user.setPhone(cursor.getInt(cursor.getColumnIndex(UserTable.PHONE)));

            closeUserCursor(cursor);
        }

        return user;
    }

    public int getTotalFees()
    {
        Cursor cursor = db.query(BorrowingTable.TABLE_NAME,
                new String[]{"COUNT(*)"},
                BorrowingTable.ID + "=? AND " + BorrowingTable.OVERDUE_DAY + ">=?",
                new String[]{Integer.toString(userID), "1"}, null, null, null);

        int days = 0;

        if (cursor.moveToNext()) {
            days = cursor.getInt(0);
            cursor.close();
        }

        return days;
    }

    /**
     * @return list of borrowed book information
     *
     * Retrieves a user's list of borrowed materials' borrow date, return date,
     * title, and ISBN.
     *
     */
    public List<_BorrowedDef> getBorrowedMaterial()
    {
        List<_BorrowedDef> materials = new ArrayList<>();

        String table = BorrowingTable.TABLE_NAME + " , "
                + MaterialTable.TABLE_NAME + " , "
                + UserTable.TABLE_NAME;

        String[] allBooks = new String[]{BorrowingTable.BORROW_DATE,
                BorrowingTable.RETURN_DATE, MaterialTable.TITLE, MaterialTable._ID};
        String where = BorrowingTable.ID + "=" + UserTable._ID
                + " AND "
                + BorrowingTable.ISBN + "=" + MaterialTable._ID
                + " AND "
                + BorrowingTable.OVERDUE_DAY + "=? AND"
                + UserTable._ID + "=?";

        Cursor cursor = db.query(table, allBooks,
                where, new String[]{Integer.toString(userID), "0"}, null, null, null);

        while (cursor.moveToNext()) {
            _BorrowedDef material = new _BorrowedDef();

            material.setBorrowDate(cursor.getInt(cursor.getColumnIndex(BorrowingTable.BORROW_DATE)));
            material.setReturnDate(cursor.getInt(cursor.getColumnIndex(BorrowingTable.RETURN_DATE)));
            material.setBookTitle(cursor.getString(cursor.getColumnIndex(MaterialTable.TITLE)));
            material.setIsbn(cursor.getInt(cursor.getColumnIndex(MaterialTable._ID)));
            materials.add(material);
        }

        cursor.close();

        return materials;
    }

    public List<_HoldDef> getOnHoldMaterial()
    {
        List<_HoldDef> materials = new ArrayList<>();

        String table = OnHoldTable.TABLE_NAME + " , "
                + MaterialTable.TABLE_NAME + " , "
                + UserTable.TABLE_NAME;

        String[] allBooks = new String[]{OnHoldTable.HOLD_DATE,
                OnHoldTable.END_DATE, MaterialTable.TITLE, MaterialTable._ID};
        String where = OnHoldTable.ID + "=" + UserTable._ID
                + " AND "
                + OnHoldTable.ISBN + "=" + MaterialTable._ID;

        Cursor cursor = db.query(table, allBooks,
                where, null, null, null, null);

        while (cursor.moveToNext()) {
            _HoldDef material = new _HoldDef();

            material.setHoldDate(cursor.getInt(cursor.getColumnIndex(OnHoldTable.HOLD_DATE)));
            material.setEndDate(cursor.getInt(cursor.getColumnIndex(OnHoldTable.END_DATE)));
            material.setBookTitle(cursor.getString(cursor.getColumnIndex(MaterialTable.TITLE)));
            material.setIsbn(cursor.getInt(cursor.getColumnIndex(MaterialTable._ID)));
            materials.add(material);
        }

        cursor.close();

        return materials;
    }

    public List<_OverdueDef> getOverdueMaterial()
    {
        List<_OverdueDef> materials = new ArrayList<>();

        String table = BorrowingTable.TABLE_NAME + " , "
                + MaterialTable.TABLE_NAME + " , "
                + UserTable.TABLE_NAME;

        String[] allBooks = new String[]{BorrowingTable.BORROW_DATE,
                BorrowingTable.RETURN_DATE, MaterialTable.TITLE, MaterialTable._ID};
        String where = BorrowingTable.ID + "=" + UserTable._ID
                + " AND "
                + BorrowingTable.ISBN + "=" + MaterialTable._ID
                + " AND "
                + BorrowingTable.OVERDUE_DAY + ">? AND "
                + UserTable._ID + "=?";

        Cursor cursor = db.query(table, allBooks,
                where, new String[]{Integer.toString(userID), "0"}, null, null, null);

        while (cursor.moveToNext()) {
            _OverdueDef material = new _OverdueDef();

            material.setBookTitle(cursor.getString(cursor.getColumnIndex(MaterialTable.TITLE)));
            material.setIsbn(cursor.getInt(cursor.getColumnIndex(MaterialTable._ID)));
            material.setDaysLate(cursor.getInt(cursor.getColumnIndex(BorrowingTable.OVERDUE_DAY)));
            materials.add(material);
        }

        cursor.close();

        return materials;
    }

    /**
     * @param user the user that will be update
     *
     * Updates a specific user
     */
    public void update(UserDef user)
    {
        ContentValues values = new ContentValues();

        if (user.getFirstName() != null)
            values.put(UserTable.FIRST_NAME, user.getFirstName());
        if (user.getLastName() != null)
            values.put(UserTable.LAST_NAME, user.getLastName());
        if (user.getUsername() != null)
            values.put(UserTable.USERNAME, user.getUsername());
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

        writeDB.update(UserTable.TABLE_NAME, values, where,
                new String[]{Integer.toString(user.getId())});
    }

    public long addImage(byte[] image)
    {
        ContentValues values = new ContentValues();
        values.put(UserTable.IMAGE, image);

        return writeDB.update(UserTable.TABLE_NAME, values,
                UserTable._ID + "=?", new String[]{Integer.toString(userID)});
    }

    public byte[] getImage()
    {
        Cursor cursor = db.query(UserTable.TABLE_NAME, new String[]{UserTable.IMAGE},
                UserTable._ID + "=?", new String[]{Integer.toString(userID)},
                null, null, null);

        byte[] image = null;

        if (cursor.moveToNext())
            image = cursor.getBlob(0);

        cursor.close();

        return image;
    }

}
