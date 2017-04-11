package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by evech on 2017-03-27.
 */

public class Materials extends IHandler<MaterialsDef, MaterialTable> {
    private static final String WHERE_ISBN_EQUALS = MaterialTable._ID
            + " =?";

    public Materials(Context context) {
        writeDB = _DatabaseHelper.getHelper(context).getWritableDatabase();
    }

    protected void innerAdd(MaterialsDef material, ContentValues values)
    {
        values.put(MaterialTable._ID, material.getIsbn());
        values.put(MaterialTable.TITLE, material.getTitle());
        values.put(MaterialTable.DESCRIPTION, material.getDescription());
        values.put(MaterialTable.TYPE, material.getType());
        values.put(MaterialTable.GENRE, material.getGenre());
        values.put(MaterialTable.YEAR_CREATED, material.getYearOfCreation());
        values.put(MaterialTable.COMPANY, material.getCompany());
        values.put(MaterialTable.SHELF_NO, material.getShelf());
        values.put(MaterialTable.IMAGE, material.getImage());

        writeDB.insert(MaterialTable.TABLE_NAME, null, values);

        if (material.getLanguage() != null) {
            for (LanguageDef l : material.getLanguage())
            {
                values = new ContentValues();
                values.put(LanguageTable.LANGUAGE,l.getLanguage());
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

        if(material.getType().equalsIgnoreCase("audio")) {
            AudioDef audio = new AudioDef(material.getIsbn(), -1);
            Audio a = new Audio(writeDB);
            a.add(audio);
        } else if(material.getType().equalsIgnoreCase("visual")) {
            VisualsDef vis = new VisualsDef(material.getIsbn(), -1, 0);
            Visuals v = new Visuals(writeDB);
            v.add(vis);
        }
    }

    public int delete(int ISBN)
    { return delete(new String[]{Integer.toString(ISBN)}); }

    public int update(MaterialsDef material) {
        ContentValues values = new ContentValues();

        if (material.getType() != null)
            values.put(MaterialTable.TYPE, material.getType());
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
            for (LanguageDef l : material.getLanguage())
            {
                values = new ContentValues();
                values.put(LanguageTable.LANGUAGE,l.getLanguage());
                values.put(LanguageTable.ISBN, material.getIsbn());
                writeDB.insert(LanguageTable.TABLE_NAME, null, values);
            }
        }
        return 0;
    }

    // TODO : GENERATE ENTITIES
    protected List<MaterialsDef> genEntities() {
        List<MaterialsDef> materials = new ArrayList<>();

        int total = 12;

        for (int i = 0; i < total; i++) {
            MaterialsDef m = new MaterialsDef();
            m.setIsbn(1111 + i);
            m.setDescription("");
            m.setTitle("");
            m.setGenre("");
            m.setType("");
            m.setYearOfCreation(1);
            m.setCompany("");
            //m.setImage(};
            m.setShelf(-1);
            materials.add(m);
        }

        return materials;
    }
}
