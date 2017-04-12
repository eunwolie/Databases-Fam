package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import java.util.List;

import finalproject.cpsc471_dbms.DatabaseHandler.Event;
import finalproject.cpsc471_dbms.Definitions.EventAttendanceDef;
import finalproject.cpsc471_dbms.Definitions.EventDef;
import finalproject.cpsc471_dbms.Queries.EventQueries;

/**
 * Created by farra on 2017-04-08.
 */

public class EventFacade {
    private EventQueries eq;
    private Event e;

    public EventFacade(Context context)
    {
        eq = new EventQueries(context);
        e = new Event(context);
    }

    public List<EventDef> getAllEvents()
    { return eq.getAllEventInfo(); }

    public EventDef getEvent(EventDef ev)
    { return e.get(ev.getWorkID(), ev.getDate(), ev.getStartTime()); }

    public byte[] getEventImage(String title)
    { return eq.getImage(title); }

    public long setEventImage(String title, byte[] image)
    { return eq.setImage(title, image); }

    public void addEvent(EventDef ev)
    { e.add(ev); }

    public long deleteEvent(EventDef ev)
    { return e.delete(ev); }

    // IF UPDATE EVENT, PEOPLE HAVE TO RE-GO FOR IT
    public void updateEvent(EventDef ev)
    {
        deleteEvent(ev);
        addEvent(ev);
    }

    public long attendEvent(EventAttendanceDef ea)
    { return eq.addUserToEvent(ea.getDate(), ea.getStartTime(), ea.getId(), ea.getWorkID()); }

    public int leaveEvent(int uID)
    { return eq.removeUserFromEvent(uID); }

    public boolean isGoingToEvent(int uID, EventDef ev)
    { return eq.isInEvent(uID, ev); }

    public int getAttendeeAmount(EventDef e)
    { return eq.eventAttendeeAmount(e); }

    public void close()
    {
        eq.close();
        e.close();
    }
}
