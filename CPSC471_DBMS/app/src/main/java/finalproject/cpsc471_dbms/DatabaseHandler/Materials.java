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
    private static final String WHERE_KEY_EQUALS = MaterialTable._ID
            + " =?";

    public Materials(Context context) {
        super(WHERE_KEY_EQUALS);
        writeDB = _DatabaseHelper.getHelper(context).getWritableDatabase();
    }

    protected ContentValues innerAdd(MaterialsDef material)
    {
        ContentValues values = new ContentValues();
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

        ContentValues tempValues = new ContentValues();

        if (material.getLanguage() != null) {
            for (LanguageDef l : material.getLanguage())
            {
                tempValues = new ContentValues();
                tempValues.put(LanguageTable.LANGUAGE,l.getLanguage());
                tempValues.put(LanguageTable.ISBN, material.getIsbn());
                writeDB.insert(LanguageTable.TABLE_NAME, null, tempValues);
            }
        }

        if (material.getAuthor() != null) {
            for (AuthorDef a : material.getAuthor())
            {
                tempValues = new ContentValues();
                tempValues.put(AuthorTable.FIRST_NAME, a.getfName());
                tempValues.put(AuthorTable.MINIT_NAME, a.getMinit());
                tempValues.put(AuthorTable.LAST_NAME,a.getlName());
                tempValues.put(AuthorTable.ISBN, material.getIsbn());
                writeDB.insert(AuthorTable.TABLE_NAME, null, tempValues);
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
        return values;
    }

    public long add(MaterialsDef def)
    { return writeDB.insert(MaterialTable.TABLE_NAME, null, innerAdd(def)); }

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

    public long addImage(int isbn, byte[] image)
    {
        ContentValues values = new ContentValues();
        values.put(MaterialTable.IMAGE, image);

        return writeDB.update(MaterialTable.TABLE_NAME, values,
                MaterialTable._ID + "=?", new String[]{Integer.toString(isbn)});
    }

    // TODO : GENERATE ENTITIES
    protected List<MaterialsDef> genEntities() {
        List<MaterialsDef> materials = new ArrayList<>();

        int total = 9;
        int baseIsbn = 1111;
        String descriptions = "";
        String[] titles = new String[]{
                "The Thief Lord", "All-Nighters with 471", "The Looking Glass",
                "Farrah's Life", "When Did She Leave", "Forest Bound",
                "Updating Bottom and Top", "Sexual Adventures with Ashley",
                "The Secret Lives of University Students "
                + "and Their Struggles"
        };
        String[] genres = new String[]{"fantasy","horror", "horror", "humour",
            "mystery", "fantasy", "erotica", "erotica", "non-fiction"};
        String[] types = new String[]{MaterialsDef.VISUAL_TYPE,
            MaterialsDef.BOTH_TYPE, MaterialsDef.AUDIO_TYPE, MaterialsDef.BOTH_TYPE,
            MaterialsDef.VISUAL_TYPE, MaterialsDef.VISUAL_TYPE, MaterialsDef.AUDIO_TYPE,
                MaterialsDef.BOTH_TYPE, MaterialsDef.VISUAL_TYPE
        };
        int[] years = new int[]{2000, 1990, 1994, 1995, 2002, 2014, 1990, 2003, 2015};
        String[] companies = new String[]{"Scholastic", "Horror Enterprise", "Horror Enterprise",
            "Duck's Books", "Duck's Books", "Younger Scrolls", "Luscious Whispers",
            "Luscious Whispers", "Scholastic"};
        int[] shelves = new int[]{};


        for (int i = 0; i < total; i++) {
            MaterialsDef m = new MaterialsDef();
            m.setIsbn(baseIsbn + i);
            m.setDescription("");
            m.setTitle("");
            m.setGenre("fantasy");
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
