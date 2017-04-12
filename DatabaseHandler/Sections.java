package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.BorrowingTable;
import finalproject.cpsc471_dbms.Constants.SectionTable;
import finalproject.cpsc471_dbms.Definitions.BorrowingDef;
import finalproject.cpsc471_dbms.Definitions.SectionDef;

/**
 * Created by evech on 2017-03-25.
 */

public class Sections extends IHandler<SectionDef, SectionTable>{
    private static final String WHERE_KEY_EQUALS = SectionTable._ID + "=?";

    public Sections(Context context){
        writeDB = (new _DatabaseHelper(context)).getWritableDatabase();
    }

    protected void innerAdd(SectionDef s, ContentValues values)
    {
        values.put(SectionTable._ID, s.getGenre());
        values.put(SectionTable.FLOOR_NUMBER, s.getfNo());
    }

    public int delete(int id)
    { return delete(new String[]{Integer.toString(id)}); }

    protected List<SectionDef> genEntities() {
        List<SectionDef> list = new ArrayList<>();

        String[] genres = new String[]{"fantasy", "horror", "humour",
            "mystery", "non-fiction", "erotica"};

        int sectionNums = 1; // increase
        int[] floorNums = new int[]{ 1, 2, 2, 3, 3, 4 };

        for (int i = 0; i < floorNums.length; i++)
            list.add(new SectionDef(genres[i], sectionNums + i, floorNums[i]));

        return list;
    }
}
