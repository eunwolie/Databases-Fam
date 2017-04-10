package finalproject.cpsc471_dbms.Facades;

import android.content.Context;

import java.util.List;

import finalproject.cpsc471_dbms.Queries.*;
import finalproject.cpsc471_dbms.Definitions.*;
/**
 * Created by farra on 2017-04-08.
 */

public class AccountFacade {

    private UserProfileQueries q;

    public AccountFacade(Context context, int userId)
    { q = new UserProfileQueries(context, userId); }

    public UserDef getProfile()
    { return q.getUserInfo(); }

    public StaffDef getStaffProfile()
    { return q.getStaffInfo(); }

    public void updateProfile(UserDef user)
    { q.update(user); }

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
}
