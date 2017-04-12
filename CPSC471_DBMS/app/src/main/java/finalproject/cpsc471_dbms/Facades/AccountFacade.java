package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import java.util.List;

import finalproject.cpsc471_dbms.DatabaseHandler.Helps;
import finalproject.cpsc471_dbms.DatabaseHandler.Staff;
import finalproject.cpsc471_dbms.DatabaseHandler.User;
import finalproject.cpsc471_dbms.Queries.*;
import finalproject.cpsc471_dbms.Definitions.*;
/**
 * Created by farra on 2017-04-08.
 */

public class AccountFacade {

    private UserProfileQueries q;
    private User u;
    private Staff s;
    private Helps h;

    private int userId;

    public AccountFacade(Context context, int userId)
    {
        q = new UserProfileQueries(context, userId);
        u = new User(context);
        s = new Staff(context);
        h = new Helps(context);

        this.userId = userId;
    }

    public UserDef getProfile()
    { return q.getUserInfo(); }

    public StaffDef getStaffProfile()
    { return s.getByUID(userId); }

    public void updateProfile(UserDef user)
    { u.update(user); }

    public List<_BorrowedDef> getBorrowedMaterial()
    { return q.getBorrowedMaterial(); }

    public List<_HoldDef> getOnHoldMaterial()
    { return q.getOnHoldMaterial(); }

    public List<_OverdueDef> getOverDueMaterial()
    { return q.getOverdueMaterial(); }

    public byte[] getProfilePicture()
    { return q.getImage(); }

    public int getTotalFees(int feePerDay)
    { return q.getTotalFees() * feePerDay; }

    /**
     * @param wID the work ID of the librarian
     *
     * Approve of a specific librarian
     *
     */
    public void approve(int wID)
    { h.add(new LibHelpDef(userId, wID)); }

    /**
     * @param wID the work ID of the librarian
     *
     * Remove one's approval of a specific librarian
     *
     */
    public int removeApproval(int wID)
    { return h.delete(wID, userId); }

    public boolean hasApproved(int wID)
    { return q.hasAlreadyApproved(userId, wID); }

    public int getApprovalRate()
    { return q.countApproval(getStaffProfile()); }

    public void close()
    {
        q.close();
        u.close();
        s.close();
        h.close();
    }
}
