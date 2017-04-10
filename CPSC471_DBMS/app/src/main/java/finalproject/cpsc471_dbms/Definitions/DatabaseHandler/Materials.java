package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Definitions.AudioDef;
import finalproject.cpsc471_dbms.Definitions.MaterialsDef;
import finalproject.cpsc471_dbms.Definitions.VisualsDef;

/**
 * Created by evech on 2017-03-27.
 */

public class Materials extends MaterialsDef {
    private static final String WHERE_ISBN_EQUALS = MaterialTable._ID
            + " =?";

    _DatabaseHelper materialdbHelper;
    SQLiteDatabase writeDB;
    SQLiteDatabase readDB;
    Context context;

    public Materials(Context context) {
        this.context = context;
        materialdbHelper = _DatabaseHelper.getHelper(context);
        writeDB = materialdbHelper.getWritableDatabase();
        readDB = materialdbHelper.getWritableDatabase();
    }

    public long[] add(MaterialsDef materials) {
        long[] result = {9999,9999,9999};
        ContentValues values = new ContentValues();
        values.put(MaterialTable.AUTHOR, materials.getAuthor());
        values.put(MaterialTable.TITLE, materials.getTitle());
        values.put(MaterialTable.TYPE, materials.getType());

        if(materials.getType()=="audio") {
            AudioDef audio = new AudioDef(materials.getIsbn(), -1);
            Audio a = new Audio(context);
            result[0]= a.add(audio);
        } else if(materials.getType()=="visual") {
            VisualsDef vis = new VisualsDef(materials.getIsbn(), -1);
            Visuals v = new Visuals(context);
            result[1]= v.add(vis);
        }

        result[2] = writeDB.insert(MaterialTable.TABLE_NAME, null, values);
        return result;
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

    public int delete(MaterialsDef materials) {
        return writeDB.delete(_DatabaseHelper.CREATE_MATERIALS_TABLE,
                WHERE_ISBN_EQUALS, new String[] {materials.getIsbn() + "" });
    }

    public List<MaterialsDef> getMaterials() {
        List<MaterialsDef> materials = new ArrayList<>();
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

        List<MaterialsDef> material = new ArrayList<>();
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
