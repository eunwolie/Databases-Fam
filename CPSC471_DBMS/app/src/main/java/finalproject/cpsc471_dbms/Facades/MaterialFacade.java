package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import java.util.List;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.DatabaseHandler.*;
import finalproject.cpsc471_dbms.Definitions.*;
import finalproject.cpsc471_dbms.Queries.*;


/**
 * Created by farra on 2017-04-08.
 *
 * The language and type filters are strictly for title, genre, and author or I will
 * kill myself
 */

public class MaterialFacade {

    private SearchQueries s;
    private MaterialQueries mq;
    private Materials m;
    private Borrows b;
    private Donation d;
    private Holds h;

    private static int materialID = 500;

    public MaterialFacade(Context context)
    {
        s = new SearchQueries(context);
        mq = new MaterialQueries(context);
        m = new Materials(context);
        b = new Borrows(context);
        d = new Donation(context);
        h = new Holds(context);
    }

    public static void incrementID(){ materialID++; }

    public void insertMaterial(MaterialsDef md)
    { m.add(md); }

    // DO NOT HAVE TO READ
    public int deleteMaterial(int ISBN)
    { return m.delete(ISBN); }

    // DO NOT HAVE TO READ
    public int updateMaterial(MaterialsDef md)
    { return m.update(md); }

    // DO NOT HAVE TO READ
    public long borrowMaterial(BorrowingDef bd)
    { return b.add(bd); }

    // DO NOT HAVE TO READ
    public long returnMaterial(BorrowingDef bd)
    { return b.delete(bd.getIsbn(), bd.getId()); }

    // DO NOT HAVE TO READ
    public long holdMaterial(OnHoldDef hd)
    { return h.add(hd);}

    // DO NOT HAVE TO READ
    public int expireHold(OnHoldDef hd)
    { return h.delete(hd.getIsbn(), hd.getId()); }

    // DO NOT HAVE TO READ
    public long donateMaterial(DonationDef dd)
    { return d.update(dd); }

    // physically available
    public boolean materialIsAvailable(int ISBN)
    { return mq.isAvailable(ISBN); }

    public boolean materialIsElectronic(int ISBN)
    { return mq.isElectronic(ISBN); }

    public _LocationDef getMaterialLocation(int ISBN)
    { return mq.getLocation(ISBN); }

    /**
     * @param order Either ascending "SearchQueries.ASC" or descending "SearchQueries.DESC"
     * @return List of materials ordered by title
     *
     */
    public List<MaterialsDef> getByTitle(String order, String langFilter, String typeFilter)
    {
        if (order.equalsIgnoreCase(SearchQueries.ASC))
            return s.getInfoBy(MaterialTable.TITLE, SearchQueries.ASC, langFilter, typeFilter);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(MaterialTable.TITLE, SearchQueries.DESC, langFilter, typeFilter);
        else
            return null;
    }

    /**
     * @param order Either ascending "SearchQueries.ASC" or descending "SearchQueries.DESC"
     * @return List of materials ordered by author
     *
     */
    public List<MaterialsDef> getByAuthor(String order, String langFilter, String typeFilter)
    {
        if (order.equalsIgnoreCase(SearchQueries.ASC))
            return s.getInfoBy(AuthorTable.LAST_NAME, SearchQueries.ASC, langFilter, typeFilter);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(AuthorTable.LAST_NAME, SearchQueries.DESC, langFilter, typeFilter);
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
            return s.getInfoBy(MaterialTable.GENRE, SearchQueries.ASC, null, null);
        else if (order.equalsIgnoreCase(SearchQueries.DESC))
            return s.getInfoBy(MaterialTable.GENRE, SearchQueries.DESC, null, null);
        else
            return null;
    }

    /**
     * @param genre Desired genre that we're specifying
     * @return list of materials with that genre
     */
    public List<MaterialsDef> getSpecificGenre(String genre)
    { return s.getSpecificInfoBy(MaterialTable.GENRE, genre + "%", null, null); }

    /**
     * @param title Desired title that we're specifying
     * @return list of materials with that title
     */
    public List<MaterialsDef> getSpecificTitle(String title, String langFilter, String typeFilter)
    { return s.getSpecificInfoBy(MaterialTable.TITLE, title + "%", langFilter, typeFilter); }

    /**
     * @param author Desired author that we're specifying
     * @return list of materials with that author
     */
    public List<MaterialsDef> getSpecificAuthor(String author, String langFilter, String typeFilter)
    { return s.getSpecificInfoBy(AuthorTable.LAST_NAME, author + "%", langFilter, typeFilter); }

    /**
     * @param ISBN Desired ISBN that we're specifying
     * @return list of materials with that ISBN - should only be one item tbh
     */
    public List<MaterialsDef> getSpecificISBN(int ISBN)
    { return s.getSpecificInfoBy(MaterialTable._ID, Integer.toString(ISBN), null, null); }

    /**
     * @return list of materials with that author
     */
    public List<AuthorDef> getAuthors()
    { return s.getAuthors(); }

    /**
     * @param langFilter Can be null - the filter for language
     * @param typeFilter Can be null - the filter for types
     * @return Get list of genres
     */
    public List<String> getGenres(String langFilter, String typeFilter)
    { return s.getAttribute(MaterialTable.TABLE_NAME, MaterialTable.GENRE, langFilter, typeFilter); }

    public void close()
    {
        s.close();
        mq.close();
        m.close();
        b.close();
        d.close();
        h.close();
    }

}