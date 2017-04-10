package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.AuthorTable;
import finalproject.cpsc471_dbms.Constants.LanguageTable;
import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.Definitions.AudioDef;
import finalproject.cpsc471_dbms.Definitions.AuthorDef;
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

    public long[] add(MaterialsDef material) {
        long[] result = {9999,9999,9999};
        ContentValues values = new ContentValues();

        values.put(MaterialTable._ID, material.getIsbn());
        values.put(MaterialTable.TITLE, material.getTitle());
        values.put(MaterialTable.DESCRIPTION, material.getDescription());
        values.put(MaterialTable.TYPE, material.getType());
        values.put(MaterialTable.GENRE, material.getGenre());
        values.put(MaterialTable.YEAR_CREATED, material.getYearOfCreation());
        values.put(MaterialTable.COMPANY, material.getCompany());
        values.put(MaterialTable.SHELF_NO, material.getShelf());

        writeDB.insert(MaterialTable.TABLE_NAME, null, values);

        if (material.getLanguage() != null) {
            for (String l : material.getLanguage())
            {
                values = new ContentValues();
                values.put(LanguageTable.LANGUAGE,l);
                values.put(LanguageTable.ISBN, material.getIsbn());
                writeDB.insert(LanguageTable.TABLE_NAME, null, values);
            }
        }

        if (material.getAuthor() != null) {
            for (AuthorDef a : material.getAuthor())
            {
                values = new ContentValues();
                values.put(AuthorTable.FIRST_NAME, a.getfName());
                values.put(AuthorTable.MINIT_NAME, a.getMinit());
                values.put(AuthorTable.LAST_NAME,a.getlName());
                values.put(AuthorTable.ISBN, material.getIsbn());
                writeDB.insert(AuthorTable.TABLE_NAME, null, values);
            }
        }

        // TODO: MULTIPLE TYPES
        if(material.getType().equalsIgnoreCase("audio")) {
            AudioDef audio = new AudioDef(material.getIsbn(), -1);
            Audio a = new Audio(context);
            result[0]= a.add(audio);
        } else if(material.getType().equalsIgnoreCase("visual")) {
            VisualsDef vis = new VisualsDef(material.getIsbn(), -1);
            Visuals v = new Visuals(context);
            result[1]= v.add(vis);
        }

        result[2] = writeDB.insert(MaterialTable.TABLE_NAME, null, values);
        return result;
    }

    public int update(MaterialsDef material) {
        ContentValues values = new ContentValues();

        if (material.getIsbn() != -1)
            values.put(MaterialTable._ID, material.getIsbn());
        if (material.getTitle() != null)
            values.put(MaterialTable.TITLE, material.getTitle());
        if (material.getDescription() != null)
            values.put(MaterialTable.DESCRIPTION, material.getDescription());
        if (material.getType() != null)
            values.put(MaterialTable.TYPE, material.getType());
        if (material.getGenre() != null)
            values.put(MaterialTable.GENRE, material.getGenre());
        if (material.getYearOfCreation() != -1)
            values.put(MaterialTable.YEAR_CREATED, material.getYearOfCreation());
        if (material.getCompany() != null)
            values.put(MaterialTable.COMPANY, material.getCompany());
        if (material.getImage() != null)
            values.put(MaterialTable.IMAGE, material.getImage());
        if (material.getShelf() != -1)
            values.put(MaterialTable.SHELF_NO, material.getShelf());

        writeDB.update(MaterialTable.TABLE_NAME, values,
                MaterialTable._ID + "=?",
                new String[]{Integer.toString(material.getIsbn())});

        if (material.getLanguage() != null) {
            writeDB.delete(LanguageTable.TABLE_NAME, LanguageTable.ISBN + "=?",
                    new String[]{Integer.toString(material.getIsbn())});
            for (String l : material.getLanguage())
            {
                values = new ContentValues();
                values.put(LanguageTable.LANGUAGE,l);
                values.put(LanguageTable.ISBN, material.getIsbn());
                writeDB.insert(LanguageTable.TABLE_NAME, null, values);
            }
        }
        return 0;
    }

    public int delete(int ISBN) {
        return writeDB.delete(MaterialTable.TABLE_NAME,
                WHERE_ISBN_EQUALS, new String[] {ISBN + "" });
    }

    public List<MaterialsDef> getMaterials() {
        List<MaterialsDef> materials = new ArrayList<>();
        Cursor cursor = readDB.query(MaterialTable.TABLE_NAME,
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
            writeDB.insert(MaterialTable.TABLE_NAME, null, values);
        }
    }
}
