package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import finalproject.cpsc471_dbms.DatabaseHandler.*;

/**
 * Created by farra on 2017-04-08.
 *
 * Populates the main list
 *
 */

public class MainFacade {

    private Context context;

    private List<IBasicHandler> handlers;

    public MainFacade(Context context)
    {
        handlers = new ArrayList<>();
        this.context = context;
    }

    private void createUsers()
    {
        handlers.add(new User(context));
        handlers.add(new Staff(context));
        handlers.add(new Librarian(context));
    }

    private void createLocations()
    {
        handlers.add(new Floor(context));
        handlers.add(new Sections(context));
        handlers.add(new Shelf(context));
    }

    private void createMaterials()
    {
        handlers.add(new Materials(context));
        handlers.add(new Audio(context));
        handlers.add(new Visuals(context));
        handlers.add(new Author(context));
    }

    private void createEvents()
    {
        handlers.add(new Event(context));
    }

    private void createSponsors()
    {
        handlers.add(new Sponsor(context));
    }

    private void createMaterialRelations()
    {
        handlers.add(new Borrows(context));
        handlers.add(new Holds(context));
    }

    private void createEventRelations()
    {
        handlers.add(new Donation(context));
        handlers.add(new EventAttendance(context));
        handlers.add(new Helps(context));
    }

    public void getTestLists()
    {
        createUsers();
        createMaterials();
        createSponsors();
        createLocations();
        //createEvents();
        //createMaterialRelations();
        //createEventRelations();

    }

    public void populateLists()
    {
        for (IBasicHandler i : handlers)
            i.generate();
    }

    public void close()
    {
        for (IBasicHandler i : handlers)
            i.close();
    }


}
