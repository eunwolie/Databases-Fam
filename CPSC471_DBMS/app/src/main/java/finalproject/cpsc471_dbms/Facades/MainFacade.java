package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import finalproject.cpsc471_dbms.DatabaseHandler.*;

/**
 * Created by farra on 2017-04-08.
 */

public class MainFacade {

    private Context context;

    public MainFacade(Context context)
    {
        this.context = context;
    }

    private void createUsers()
    {
        (new User(context)).loadUsers();
        (new Staff(context)).loadEntities();
        (new Librarian(context)).loadEntities();
    }

    private void createLocations()
    {

        // create floor
        // create section
        // create shelf
    }

    private void createMaterials()
    {
        // create material
        // create audio
        // create visual
        // create author
    }

    private void createEvents()
    {
        // create events
    }

    private void createSponsors()
    {
        // create sponsor
    }

    private void createMaterialRelations()
    {
        // create borrowing relations
        // create on hold relations
    }

    private void createEventRelations()
    {
        // create donation relations
        // create event attendance relations
        // create libHelp relation
    }

    public void populateLists()
    {
        createUsers();
        createEvents();
        createMaterials();
        createSponsors();
        createMaterialRelations();
        createEventRelations();
    }


}
