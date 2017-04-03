package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class NameDef {
    private String fName;
    private String minit;
    private String lName;
    private int userID;

    public NameDef() {
        super();
    }

    // TODO : I actually wasnt sure what Eve was doing here earlier, so pls compare
    public NameDef(String fName, String minit, String lName, int userID) {
        super();
        this.fName = fName;
        this.minit = minit;
        this.lName = lName;
        this.userID = userID;
    }

    public NameDef(String fName, String minit, String lName) {
        this.fName = fName;
        this.minit = minit;
        this.lName = lName;
    }

    private NameDef(Parcel in) {
        super();
        this.fName = in.readString();;
        this.minit = in.readString();;
        this.lName = in.readString();;
        this.userID = in.readInt();
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getMinit() {
        return minit;
    }

    public void setMinit(String minit) {
        this.minit = minit;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public int getuID() {
        return userID;
    }

    public void setuID(int userID) {
        this.userID = userID;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getfName());
        parcel.writeString(getMinit());
        parcel.writeString(getlName());
        parcel.writeInt(getuID());
    }

    public static final Parcelable.Creator<NameDef> CREATOR = new Parcelable.Creator<NameDef>() {
        public NameDef createFromParcel(Parcel in) {
            return new NameDef(in);
        }

        public NameDef[] newArray(int size) {
            return new NameDef[size];
        }
    };

    @Override
    public String toString() {
        return "first name:" + fName + ", middle name/initials:" + minit + ", last name:" + lName + ", user ID:" + userID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;

        return (userID == ((NameDef) obj).userID);
    }
}

