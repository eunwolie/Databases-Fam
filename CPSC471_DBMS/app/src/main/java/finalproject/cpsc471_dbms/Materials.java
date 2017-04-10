package finalproject.cpsc471_dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by evech on 2017-03-27.
 */

public class Materials extends MaterialsDefinitionsClass{
    private static final String WHERE_ISBN_EQUALS = DatabaseHelper.MATERIALS_COLUMN_IBSN
            + " =?";

    public Materials(Context context) {
        super(context);
    }

    public long save(MaterialsDefinitionsClass materials) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.MATERIALS_COLUMN_TITLE, MaterialsDefinitionsClass.getTitle());
        return database.insert(DatabaseHelper.CREATE_MATERIALS_TABLE, null, values);
    }

    public long update(MaterialsDefinitionsClass materials) {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.MATERIALS_COLUMN_TITLE, MaterialsDefinitionsClass.getTitle());

        long result = database.update(DatabaseHelper.CREATE_MATERIALS_TABLE, values,
                WHERE_ISBN_EQUALS,
                new String[] { String.valueOf(MaterialsDefinitionsClass.getIsbn()) });
        Log.d("Update Result:", "=" + result);
        return result;

    }

    public int deleteMaterials(MaterialsDefinitionsClass materials) {
        return database.delete(DatabaseHelper.CREATE_MATERIALS_TABLE,
                WHERE_ISBN_EQUALS, new String[] {MaterialsDefinitionsClass.getIsbn() + "" });
    }

    public List<MaterialsDefinitionsClass> getMaterials() {
        List<Materials> materials = new ArrayList<MaterialsDefinitionsClass>();
        Cursor cursor = database.query(DatabaseHelper.CREATE_MATERIALS_TABLE,
                new String[] { DatabaseHelper.MATERIALS_COLUMN_IBSN,
                        DatabaseHelper.MATERIALS_COLUMN_TITLE }, null, null, null, null,
                null);

        while (cursor.moveToNext()) {
            MaterialsDefinitionsClass materials = new Materials();
            materials.setIsbn(cursor.getInt(0));
            materials.setTitle(cursor.getString(1));
            materials.add(materials);
        }
        return materials;
    }

    public void loadMaterials() {
        MaterialsDefinitionsClass materials = new MaterialsDefinitionsClass("Book 1");
        MaterialsDefinitionsClass materials1 = new MaterialsDefinitionsClass("Book 2");
        MaterialsDefinitionsClass materials2 = new MaterialsDefinitionsClass("Book 3");
        MaterialsDefinitionsClass materials3 = new MaterialsDefinitionsClass("Book 4");
        MaterialsDefinitionsClass materials4 = new MaterialsDefinitionsClass("Book 5");
        MaterialsDefinitionsClass materials5 = new MaterialsDefinitionsClass("Book 6");

        List<MaterialsDefinitionsClass> material = new ArrayList<MaterialsDefinitionsClass>();
        material.add(materials);
        material.add(materials1);
        material.add(materials2);
        material.add(materials3);
        material.add(materials4);
        material.add(materials5);
        for (MaterialsDefinitionsClass mat : material) {
            ContentValues values = new ContentValues();
            values.put(DatabaseHelper.MATERIALS_COLUMN_TITLE, mat.getTitle());
            database.insert(DatabaseHelper.CREATE_MATERIALS_TABLE, null, values);
        }
    }
}
