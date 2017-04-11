package finalproject.cpsc471_dbms.Queries;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.NotificationCompatSideChannelService;


import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.DatabaseHandler.*;
import finalproject.cpsc471_dbms.Definitions.*;
import finalproject.cpsc471_dbms.Constants.*;

/**
 * Created by farra on 2017-04-05.
 */

/**
 *
 * Queries about searches
 *
 *
 */

/*

Might need to include the shelf number in these
queries for locational reasons

 */

public class SearchQueries extends IQueries{
    private SQLiteDatabase readDB;

    public static final String DESC = "DESC";
    public static final String ASC = "ASC";

    private String[] all = new String[] {
            MaterialTable._ID, MaterialTable.TITLE, MaterialTable.COMPANY,
            MaterialTable.GENRE, MaterialTable.DESCRIPTION,
            MaterialTable.YEAR_CREATED, MaterialTable.SHELF_NO,
            AuthorTable.FIRST_NAME, AuthorTable.MINIT_NAME, AuthorTable.LAST_NAME,
            LanguageTable.LANGUAGE};

    public SearchQueries(Context context)
    {
        readDB = new _DatabaseHelper(context).getReadableDatabase();
    }

    private AudioDef getAudioInfo(int isbn)
    {
        AudioDef a = null;

        Cursor cursor = writeDB.query(true, AudioTable.TABLE_NAME,
                new String[]{AudioTable.LENGTH},
                AudioTable.ISBN + "=?",
                new String[]{Integer.toString(isbn)},
                null, null, null, null);
        if (cursor.moveToNext())
        {
            a = new AudioDef();
            a.setLength(cursor.getInt(cursor.getColumnIndex(AudioTable.LENGTH)));
            a.setIsbn(isbn);
            cursor.close();
        }

        return a;
    }

    private VisualsDef getVisualInfo(int isbn)
    {
        VisualsDef v = null;

        Cursor cursor = writeDB.query(true, VisualTable.TABLE_NAME,
                new String[]{VisualTable.PAGE_LENGTH, VisualTable.HAS_EBOOK},
                VisualTable.ISBN + "=?",
                new String[]{Integer.toString(isbn)},
                null, null, null, null);
        if (cursor.moveToNext())
        {
            v = new VisualsDef();
            v.setLength(cursor.getInt(cursor.getColumnIndex(VisualTable.PAGE_LENGTH)));
            v.setIsbn(isbn);
            v.setHasEBook(cursor.getInt(cursor.getColumnIndex(VisualTable.HAS_EBOOK)));
            cursor.close();
        }
        return v;
    }

    private List<TypeDef> handleType(String type, int isbn)
    {
        List<TypeDef> td = new ArrayList<>();
        if (type.equalsIgnoreCase(MaterialsDef.AUDIO_TYPE))
            td.add(getAudioInfo(isbn));
        else if (type.equalsIgnoreCase(MaterialsDef.VISUAL_TYPE))
            td.add(getVisualInfo(isbn));
        else
        {
            td.add(getAudioInfo(isbn));
            td.add(getVisualInfo(isbn));
        }
        return td;
    }

    private int getAtomicAttr(Cursor cursor, MaterialsDef material)
    {
        int isbn = cursor.getInt(cursor.getColumnIndex(MaterialTable._ID));
        material.setTitle(cursor.getString(cursor.getColumnIndex(MaterialTable.TITLE)));
        material.setIsbn(isbn);
        material.setType(cursor.getString(cursor.getColumnIndex(MaterialTable.TYPE)));
        material.setGenre(cursor.getString(cursor.getColumnIndex(MaterialTable.GENRE)));
        material.setYearOfCreation(cursor.getInt(cursor.getColumnIndex(MaterialTable.YEAR_CREATED)));
        material.setCompany(cursor.getString(cursor.getColumnIndex(MaterialTable.COMPANY)));
        material.setShelf(cursor.getInt(cursor.getColumnIndex(MaterialTable.SHELF_NO)));
        return isbn;
    }

    /**
     * @param cursor what will be iterating through the list
     * @return List of desired materials
     *
     */
    private List<MaterialsDef> getBasicInfo(Cursor cursor)
    {
        List<MaterialsDef> materials = new ArrayList<MaterialsDef>();

        int isbn = -1;
        String name = "";
        AuthorDef a = null;
        LanguageDef l = null;
        List<AuthorDef> as = new ArrayList<>();
        List<LanguageDef> ls = new ArrayList<>();
        MaterialsDef material = new MaterialsDef();

        while (cursor.moveToNext()) {
            if ((cursor.getInt(cursor.getColumnIndex(MaterialTable._ID))) != isbn) {
                // if the material isn't the default, then add it to the list
                if (isbn != -1) {
                    material.setLanguage(ls);
                    material.setAuthor(as);
                    materials.add(material);
                    material = new MaterialsDef();
                }
                // retrieve the new attributes
                isbn = getAtomicAttr(cursor, material);
                material.setTypeInfo(handleType(material.getType(), isbn));
            }

            // retrieve author(s)
            AuthorDef ad = null;
            if ((cursor.getString(cursor.getColumnIndex(AuthorTable.FIRST_NAME))) != null) {
                ad = new AuthorDef();
                ad.setfName(name);
                ad.setMinit(cursor.getString(cursor.getColumnIndex(AuthorTable.MINIT_NAME)));
                ad.setlName(cursor.getString(cursor.getColumnIndex(AuthorTable.LAST_NAME)));
                ad.setIsbn(isbn);

                if (! (ad.equals(a))) {
                    as.add(ad);
                    a = ad;
                }
            }

            // retrieve language(s)
            LanguageDef ld = new LanguageDef();
            ld.setLanguage(cursor.getString(cursor.getColumnIndex(LanguageTable.LANGUAGE)));
            ld.setIsbn(isbn);
            if (! (ld.equals(l))) {
                ls.add(ld);
                l = ld;
            }
        }

        return materials;
    }

    /**
     * @param attribute Retrieve items based on the order of this item
     * @param order asc/desc
     * @return List of desired items
     */
    public List<MaterialsDef> getInfoBy(String attribute, String order)
    {
        List<MaterialsDef> materials = new ArrayList<MaterialsDef>();

        String table = AuthorTable.TABLE_NAME + " LEFT OUTER JOIN "
                + MaterialTable.TABLE_NAME + " ON "
                + AuthorTable.ISBN + "=" + MaterialTable._ID
                + " INNER JOIN " + LanguageTable.TABLE_NAME + " ON "
                + MaterialTable._ID + "=" + LanguageTable.ISBN;

        Cursor cursor = readDB.query(table, all,
                null, null, null, null,
                attribute + ", " + MaterialTable._ID + " " + order);

        materials = getBasicInfo(cursor);


        cursor.close();

        return materials;
    }

    // TODO : ASK WILLIAM : ORDER BY LAST NAME, OR BY FIRST NAME?
    /**
     * @param attribute desired attribute we want from material
     * @param id attribute == id
     * @return a list of materials where attribute == id
     */
    public List<MaterialsDef> getSpecificInfoBy(String attribute, String id)
    {
        List<MaterialsDef> materials;

        String table = MaterialTable.TABLE_NAME;
        String where = attribute + "=?";

        if (attribute.equalsIgnoreCase(AuthorTable.LAST_NAME))
            table += " , " + AuthorTable.TABLE_NAME;

        Cursor cursor = readDB.query(table, all,
                where, new String[]{id}, null, null, null );

        materials = getBasicInfo(cursor);
        cursor.close();

        return materials;
    }
}
