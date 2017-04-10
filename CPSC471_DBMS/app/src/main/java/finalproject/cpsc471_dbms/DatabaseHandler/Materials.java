package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Constants.SectionTable;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;
import finalproject.cpsc471_dbms.Definitions.SectionDef;

/**
 * Created by evech on 2017-03-27.
 */

public class Materials extends MaterialsDef {
    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = MaterialTable._ID + "=?";

    public Materials(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    public void addMaterial(MaterialsDef x)
    {
        ContentValues values = new ContentValues();
        values.put(MaterialTable._ID, x.getIsbn());
        values.put(MaterialTable.DESCRIPTION, x.getDescription());
        values.put(MaterialTable.AUTHOR, x.getAuthor());
        values.put(MaterialTable.TITLE, x.getTitle());
        values.put(MaterialTable.TYPE, x.getType());
        values.put(MaterialTable.GENRE, x.getGenre());
        values.put(MaterialTable.YEAR_CREATED, x.getYearOfCreation());
        db.insert(MaterialTable.TABLE_NAME, null, values);
    }

    public int updateMaterial(MaterialsDef x) {
        ContentValues values = new ContentValues();
        values.put(MaterialTable._ID, x.getIsbn());
        values.put(MaterialTable.DESCRIPTION, x.getDescription());
        values.put(MaterialTable.AUTHOR, x.getAuthor());
        values.put(MaterialTable.TITLE, x.getTitle());
        values.put(MaterialTable.TYPE, x.getType());
        values.put(MaterialTable.GENRE, x.getGenre());
        values.put(MaterialTable.YEAR_CREATED, x.getYearOfCreation());
        values.put(MaterialTable.IMAGE, x.getImage());
        // update row
        int result = db.update(MaterialTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { String.valueOf(x.getIsbn()) });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteMaterial(int isbn) {
        return db.delete(MaterialTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{isbn+""});
    }

    public int deleteMaterial(MaterialsDef x) {
        return db.delete(MaterialTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getIsbn() + "" });
    }

    // TODO :
    public List<MaterialsDef> getMaterials() {
        List<MaterialsDef> materials = new ArrayList<MaterialsDef>();
        Cursor cursor = db.query(MaterialTable.TABLE_NAME,
                new String[] { MaterialTable._ID,
                        MaterialTable.TITLE }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            MaterialsDef material = new MaterialsDef();
            material.setIsbn(cursor.getInt(0));
            material.setTitle(cursor.getString(1));
            materials.add(material);
        }
        cursor.close();


        return materials;
    }

    public void loadMaterials() {
        MaterialsDef materials = new MaterialsDef("Book 1");
        MaterialsDef materials1 = new MaterialsDef("Book 2");
        MaterialsDef materials2 = new MaterialsDef("Book 3");
        MaterialsDef materials3 = new MaterialsDef("Book 4");
        MaterialsDef materials4 = new MaterialsDef("Book 5");
        MaterialsDef materials5 = new MaterialsDef("Book 6");

        List<MaterialsDef> material = new ArrayList<MaterialsDef>();
        material.add(materials);
        material.add(materials1);
        material.add(materials2);
        material.add(materials3);
        material.add(materials4);
        material.add(materials5);
        for (MaterialsDef mat : material) {
            ContentValues values = new ContentValues();
            values.put(MaterialTable.TITLE, mat.getTitle());
            db.insert(MaterialTable.TABLE_NAME, null, values);
        }
    }
}
