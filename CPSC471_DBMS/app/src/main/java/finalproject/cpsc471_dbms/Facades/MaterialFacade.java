package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import java.util.List;

import finalproject.cpsc471_dbms.Constants.MaterialTable;
import finalproject.cpsc471_dbms.DatabaseHandler.Materials;
import finalproject.cpsc471_dbms.Definitions.*;
import finalproject.cpsc471_dbms.Queries.*;


/**
 * Created by farra on 2017-04-08.
 */

public class MaterialFacade {

    private SearchQueries s;
    private MaterialQueries mq;
    private Materials m;

    private static int materialID = 500;

    public MaterialFacade(Context context)
    {
        s = new SearchQueries(context);
        mq = new MaterialQueries(context);
        m = new Materials(context);
    }

    public static void incrementID(){ materialID++; }

    public void insertMaterial(MaterialsDef md)
    { m.add(md); }

    public int deleteMaterial(int ISBN)
    { return m.delete(ISBN); }

    public int updateMaterial(MaterialsDef md)
    { return m.update(md); }

    // physically available
    public boolean materialIsAvailable(int ISBN)
    { return mq.isAvailable(ISBN); }

    public _LocationDef getMaterialLocation(int ISBN)
    { return mq.getLocation(ISBN); }

    /**
     * @param order Either ascending "SearchQueries.ASC" or descending "SearchQueries.DESC"
     * @return List of materials ordered by title
     *
     */
    public List<MaterialsDef> getByTitle(String order)
    {
        if (order.equalsIgnoreCase(SearchQueries.ASC))
            return s.getInfoBy(MaterialTable.TITLE, SearchQueries.ASC);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(MaterialTable.TITLE, SearchQueries.DESC);
        else
            return null;
    }

    /**
     * @param order Either ascending "SearchQueries.ASC" or descending "SearchQueries.DESC"
     * @return List of materials ordered by author
     *
     */
    public List<MaterialsDef> getByAuthor(String order)
    {
        if (order.equalsIgnoreCase(SearchQueries.ASC))
            return s.getInfoBy(MaterialTable.AUTHOR, SearchQueries.ASC);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(MaterialTable.AUTHOR, SearchQueries.DESC);
        else
            return null;
    }

    /**
     * @param order Either ascending "SearchQueries.ASC" or descending "SearchQueries.DESC"
     * @return List of materials ordered by genre
     *
     */
    public List<MaterialsDef> getByGenre(String order)
    {
        if (order.equalsIgnoreCase(SearchQueries.ASC))
            return s.getInfoBy(MaterialTable.GENRE, SearchQueries.ASC);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(MaterialTable.GENRE, SearchQueries.DESC);
        else
            return null;
    }

    /**
     * @param order Either ascending "SearchQueries.ASC" or descending "SearchQueries.DESC"
     * @return List of materials ordered by year of creation
     *
     */
    public List<MaterialsDef> getByDate(String order)
    {
        if (order.equalsIgnoreCase(SearchQueries.ASC))
            return s.getInfoBy(MaterialTable.YEAR_CREATED, SearchQueries.ASC);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(MaterialTable.YEAR_CREATED, SearchQueries.DESC);
        else
            return null;
    }

    /**
     * @param genre Desired genre that we're specifying
     * @return list of materials with that genre
     */
    public List<MaterialsDef> getGenre(String genre)
    { return s.getSpecificInfoBy(MaterialTable.GENRE, genre + "%"); }

    // TODO : Partial titles
    /**
     * @param title Desired title that we're specifying
     * @return list of materials with that title
     */
    public List<MaterialsDef> getTitle(String title)
    { return s.getSpecificInfoBy(MaterialTable.TITLE, title + "%"); }

    /**
     * @param author Desired author that we're specifying
     * @return list of materials with that author
     */
    public List<MaterialsDef> getAuthor(String author)
    { return s.getSpecificInfoBy(MaterialTable.AUTHOR, author + "%"); }

    /**
     * @param ISBN Desired ISBN that we're specifying
     * @return list of materials with that ISBN - should only be one item tbh
     */
    public List<MaterialsDef> getISBN(int ISBN)
    { return s.getSpecificInfoBy(MaterialTable._ID, Integer.toString(ISBN)); }

}