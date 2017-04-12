package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.DatabaseHandler.Sponsor;
import finalproject.cpsc471_dbms.DatabaseHandler.User;
import finalproject.cpsc471_dbms.Queries.*;
import finalproject.cpsc471_dbms.Definitions.*;
import java.util.*;

/**
 * Created by farra on 2017-04-08.
 */

public class LoginFacade {

    private LoginQueries lq;
    private User u;
    private Sponsor s;

    public LoginFacade(Context context)
    {
        lq = new LoginQueries(context);
        u = new User(context);
        s = new Sponsor(context);
    }

    /**
     * @param username
     * @param password
     * @return UserID if it is the correct user, -1 otherwise
     *
     * Makes query in database to check if the corresponding
     * username and password exist
     *
     */
    public int getUserID(String username, String password)
    { return lq.checkCredentials(username, password); }

    public boolean isUserRegular(int uID)
    { return lq.isRegular(uID); }

    public boolean isUsernameUnique(String username)
    { return lq.usernameExists(username); }

    public boolean isEmailUnique(String email)
    { return lq.emailExists(email); }

    public boolean isExistingSponsor(String username, int sponsorID)
    { return lq.isExistingSponsor(sponsorID, username); }

    public void createSponsor(SponsorDef sd)
    { s.add(sd); }

    public void createAccount(UserDef ud)
    { u.add(ud); }

    public void close()
    {
        lq.close();
        u.close();
        s.close();
    }

}
