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

// TODO : Handle multivalued attribute (language and author)

public class SearchQueries {
    private SQLiteDatabase db;

    public static final String DESC = "DESC";
    public static final String ASC = "ASC";

    private String[] all = new String[] {
            MaterialTable._ID, MaterialTable.TITLE, MaterialTable.AUTHOR, MaterialTable.COMPANY,
            MaterialTable.GENRE, MaterialTable.DESCRIPTION, MaterialTable.LANGUAGE,
            MaterialTable.TYPE, MaterialTable.YEAR_CREATED, MaterialTable.SHELF_NO };

    public SearchQueries(Context context)
    {
        db = new _DatabaseHelper(context).getReadableDatabase();
    }

    /**
     * @param cursor what will be iterating through the list
     * @return List of desired materials
     *
     */
    private List<MaterialsDef> getInfo(Cursor cursor)
    {
        List<MaterialsDef> materials = new ArrayList<MaterialsDef>();

        while (cursor.moveToNext()) {
            MaterialsDef material = new MaterialsDef();

            material.setAuthor(cursor.getString(cursor.getColumnIndex(MaterialTable.AUTHOR)));
            material.setTitle(cursor.getString(cursor.getColumnIndex(MaterialTable.TITLE)));
            material.setType(cursor.getString(cursor.getColumnIndex(MaterialTable.TYPE)));
            material.setIsbn(cursor.getInt(cursor.getColumnIndex(MaterialTable._ID)));
            material.setGenre(cursor.getString(cursor.getColumnIndex(MaterialTable.GENRE)));
            material.setYearOfCreation(cursor.getInt(cursor.getColumnIndex(MaterialTable.YEAR_CREATED)));
            material.setLanguage(cursor.getString(cursor.getColumnIndex(MaterialTable.LANGUAGE)));
            material.setCompany(cursor.getString(cursor.getColumnIndex(MaterialTable.COMPANY)));
            material.setShelf(cursor.getInt(cursor.getColumnIndex(MaterialTable.SHELF_NO)));
            materials.add(material);
        }

        return materials;
    }

    /**
     * @param attribute Retrieve items based on the order of this item
     * @param order asc/desc
     * @return List of desired items
     */
    private List<MaterialsDef> getInfoBy(String attribute, String order)
    {
        List<MaterialsDef> materials = new ArrayList<MaterialsDef>();

        Cursor cursor = db.query(MaterialTable.TABLE_NAME, all,
                null, null, null, null, attribute + " " + order);

        materials = getInfo(cursor);

        cursor.close();

        return materials;
    }

    /**
     * @param attribute desired attribute we want from material
     * @param id attribute == id
     * @return a list of materials where attribute == id
     */
    private List<MaterialsDef> getSpecificInfoBy(String attribute, String id)
    {
        List<MaterialsDef> materials;

        String where = attribute + "=?";

        Cursor cursor = db.query(MaterialTable.TABLE_NAME, all,
                where, new String[]{id}, null, null, null );

        materials = getInfo(cursor);
        cursor.close();

        return materials;
    }

    /**
     * @param order Either ascending "ASC" or descending "DESC"
     * @return List of materials ordered by title
     *
     */
    public List<MaterialsDef> getByTitle(String order)
    {
        if (order.equalsIgnoreCase(ASC))
            return getInfoBy(MaterialTable.TITLE, ASC);
        else if (order.equalsIgnoreCase(DESC))
            return getInfoBy(MaterialTable.TITLE, DESC);
        else
            return null;
            //throw new Exception("Does not exist");
    }

    /**
     * @param order Either ascending "ASC" or descending "DESC"
     * @return List of materials ordered by author
     *
     */
    public List<MaterialsDef> getByAuthor(String order)
    {
        if (order.equalsIgnoreCase(ASC))
            return getInfoBy(MaterialTable.AUTHOR, ASC);
        else if (order.equalsIgnoreCase(DESC))
            return getInfoBy(MaterialTable.AUTHOR, DESC);
        else
            return null;
    }

    /**
     * @param order Either ascending "ASC" or descending "DESC"
     * @return List of materials ordered by genre
     *
     */
    public List<MaterialsDef> getByGenre(String order)
    {
        if (order.equalsIgnoreCase(ASC))
            return getInfoBy(MaterialTable.GENRE, ASC);
        else if (order.equalsIgnoreCase(DESC))
            return getInfoBy(MaterialTable.GENRE, DESC);
        else
            return null;
    }

    /**
     * @param order Either ascending "ASC" or descending "DESC"
     * @return List of materials ordered by year of creation
     *
     */
    public List<MaterialsDef> getByDate(String order)
    {
        if (order.equalsIgnoreCase(ASC))
            return getInfoBy(MaterialTable.YEAR_CREATED, ASC);
        else if (order.equalsIgnoreCase(DESC))
            return getInfoBy(MaterialTable.YEAR_CREATED, DESC);
        else
            return null;
    }

    /**
     * @param genre Desired genre that we're specifying
     * @return list of materials with that genre
     */
    public List<MaterialsDef> getGenre(String genre)
    {
        return getSpecificInfoBy(MaterialTable.GENRE, genre);
    }

    // TODO : Partial titles
    /**
     * @param title Desired title that we're specifying
     * @return list of materials with that title
     */
    public List<MaterialsDef> getTitle(String title)
    {
        return getSpecificInfoBy(MaterialTable.TITLE, title);
    }

    /**
     * @param ISBN Desired ISBN that we're specifying
     * @return list of materials with that ISBN
     */
    public List<MaterialsDef> getISBN(int ISBN)
    {
        return getSpecificInfoBy(MaterialTable._ID, Integer.toString(ISBN));
    }

    /**
     * @param author Desired author that we're specifying
     * @return list of materials with that author
     */
    public List<MaterialsDef> getAuthor(String author)
    {
        return getSpecificInfoBy(MaterialTable.AUTHOR, author);
    }
}
