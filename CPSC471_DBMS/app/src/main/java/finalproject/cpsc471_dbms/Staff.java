package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class Staff {
    private int wID;
    private int sSalary;
    private int sSSN;
    private int uID;


    public Staff() {
        super();
    }

    public Staff(int wID, int sSalary, int sSSN, int uID) {
        super();
        this.wID = wID;
        this.sSalary = sSalary;
        this.sSSN = sSSN;
        this.uID = uID;
    }

    /*public Staff(String sGenre) {
        this.sGenre = sGenre;
    } */

    private Staff(Parcel in) {
        super();
        this.wID = in.readInt();
        this.sSalary = in.readInt();
        this.sSSN = in.readInt();
        this.uID = in.readInt();
    }

    public int getwID() {
        return wID;
    }

    public void setwID(int wID) {
        this.wID = wID;
    }

    public String getsSalary() {
        return sSalary;
    }

    public void setsSalary(int sSalary) {
        this.sSalary = sSalary;
    }

    public void getsSSN(){
        return sSSN;
    }

    public void setsSSN(int sSSN){
        this.sSSN = sSSN;
    }

    public void getuID(){
        return uID;
    }

    public void setuID(){
        this.uID = uID;
    }

    @Override
    public String toString() {
        return "Work ID:" + wID + ", Salary:" + sSalary + ", SSN:" + sSSN + ", User ID:" + uID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getwID());
        parcel.writeInt(getsSalary());
        parcel.writeInt(getsSSN());
        parcel.writeInt(getuID());
    }

    public static final Parcelable.Creator<Shelf> CREATOR = new Parcelable.Creator<Shelf>() {
        public Shelf createFromParcel(Parcel in) {
            return new Shelf(in);
        }

        public Shelf[] newArray(int size) {
            return new Shelf[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + wID;
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
        Shelf other = (Shelf) obj;
        if (wID != other.wID)
            return false;
        return true;
    }
}
