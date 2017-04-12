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

public class UserProfileQueries extends IQueries{
    private SQLiteDatabase writeDB;
    private int userID;

    public UserProfileQueries(Context context, int userID)
    {
        writeDB = new _DatabaseHelper(context).getWritableDatabase();
        this.userID = userID;
    }

    private Cursor getUserCursor()
    {
        String where = UserTable._ID + "=?";

        return writeDB.query(UserTable.TABLE_NAME, null, where, new String[]{Integer.toString(userID)},
                null, null, null );
    }

    private void closeUserCursor(Cursor cursor)
    {
        cursor.close();
    }

    public UserDef getUserInfo()
    {
        Cursor cursor = getUserCursor();

        UserDef user = new UserDef();

        if (cursor.moveToNext()) {
            user.setFirstName(cursor.getString(cursor.getColumnIndex(UserTable.FIRST_NAME)));
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
        Cursor cursor = writeDB.query(BorrowingTable.TABLE_NAME,
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

        Cursor cursor = writeDB.query(table, allBooks,
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

        Cursor cursor = writeDB.query(table, allBooks,
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

        Cursor cursor = writeDB.query(table, allBooks,
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

    public boolean hasAlreadyApproved(int uID, int wID)
    {
        String where = LibHelpTable.USER_ID + "=? AND "
                + LibHelpTable.WORK_ID + "=?";
        String[] whereArgs = new String[]{
                Integer.toString(uID), Integer.toString(wID)
        };

        Cursor cursor = writeDB.query(LibHelpTable.TABLE_NAME,
                new String[]{"COUNT(*)"}, where, whereArgs,
                null, null, null, null);

        boolean hasApproved = false;

        if (cursor.moveToNext()) hasApproved = true;

        cursor.close();

        return hasApproved;
    }

    // ONLY USE IF A STAFF
    public int countApproval(StaffDef s)
    {
        Cursor cursor = writeDB.query(LibHelpTable.TABLE_NAME,
                new String[]{"COUNT(*)"},
                LibHelpTable.WORK_ID + "=?",
                new String[]{Integer.toString(s.getWorkId())},
                null, null, null, null);

        int approval = 0;

        if (cursor.moveToNext())
            approval = cursor.getInt(0);

        cursor.close();

        return approval;
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
        Cursor cursor = writeDB.query(UserTable.TABLE_NAME, new String[]{UserTable.IMAGE},
                UserTable._ID + "=?", new String[]{Integer.toString(userID)},
                null, null, null);

        byte[] image = null;

        if (cursor.moveToNext())
            image = cursor.getBlob(0);

        cursor.close();

        return image;
    }

}
