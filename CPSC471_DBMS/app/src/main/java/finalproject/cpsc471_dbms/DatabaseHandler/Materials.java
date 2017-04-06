package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;

/**
 * Created by evech on 2017-03-27.
 */

public class Materials extends MaterialsDef {
    private static final String WHERE_ISBN_EQUALS = MaterialTable._ID
            + " =?";

    _DatabaseHelper materialdbHelper;
    SQLiteDatabase writeDB;
    SQLiteDatabase readDB;

    public Materials(Context context) {
        materialdbHelper = _DatabaseHelper.getHelper(context);
        writeDB = materialdbHelper.getWritableDatabase();
        readDB = materialdbHelper.getWritableDatabase();
    }

    public long save(MaterialsDef materials) {
        ContentValues values = new ContentValues();
        values.put(MaterialTable.TITLE, materials.getTitle());
        return writeDB.insert(_DatabaseHelper.CREATE_MATERIALS_TABLE, null, values);
    }

    public long update(MaterialsDef materials) {
        ContentValues values = new ContentValues();
        values.put(MaterialTable.TITLE, materials.getTitle());

        long result = readDB.update(_DatabaseHelper.CREATE_MATERIALS_TABLE, values,
                WHERE_ISBN_EQUALS,
                new String[] { String.valueOf(materials.getIsbn()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteMaterials(MaterialsDef materials) {
        return writeDB.delete(_DatabaseHelper.CREATE_MATERIALS_TABLE,
                WHERE_ISBN_EQUALS, new String[] {materials.getIsbn() + "" });
    }

    // TODO :
    public List<MaterialsDef> getMaterials() {
        List<MaterialsDef> materials = new ArrayList<MaterialsDef>();
        Cursor cursor = readDB.query(_DatabaseHelper.CREATE_MATERIALS_TABLE,
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
            writeDB.insert(_DatabaseHelper.CREATE_MATERIALS_TABLE, null, values);
        }
    }
}
