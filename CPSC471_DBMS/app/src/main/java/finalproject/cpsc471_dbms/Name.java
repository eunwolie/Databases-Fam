package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Name {
    private String fName;
    private String minit;
    private String lName;
    private int userID;

    public Name{
        super();
    }

    public Name(int id, String name) {
        super();
        this.fName = fName;
        this.minit = minit;
        this.lName = lName;
        this.userID = userID;
    }

    public Name(String name) {
        this.fName = fName;
        this.minit = minit;
        this.lName = lName;
    }

    private Name(Parcel in) {
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

    public String getuID() {
        return userID;
    }

    public void setuID(int userID) {
        this.userID = userID;
    }
    @Override
    public String toString() {
        return "first name:" + fName + ", middle name/initials:" + minit + ", last name:" + lName + ", user ID:" + userID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getfName());
        parcel.writeString(getMinit());
        parcel.writeString(getlName());
        parcel.writeInt(getuID());
    }

    public static final Parcelable.Creator<Name> CREATOR = new Parcelable.Creator<Name>() {
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        public Name[] newArray(int size) {
            return new Name[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Name other = (Name) obj;
        if (userID != other.userID)
            return false;
        return true;
    }
}
