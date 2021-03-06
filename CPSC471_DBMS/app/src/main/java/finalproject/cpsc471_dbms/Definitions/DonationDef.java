package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class DonationDef {

    private int sId = -1;
    private int bookAmount = -1;

    public DonationDef() {
        super();
    }

    public DonationDef(int sId, int bookAmount) {
        super();
        this.sId = sId;
        this.bookAmount = bookAmount;
    }


    private DonationDef(Parcel in) {
        super();
        this.sId = in.readInt();
        this.bookAmount = in.readInt();
    }

    public int getsId() {
        return sId;
    }

    public void setId(int sId) {
        this.sId = sId;
    }
    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getsId());
        parcel.writeInt(getBookAmount());
    }

    public static final Parcelable.Creator<DonationDef> CREATOR = new Parcelable.Creator<DonationDef>() {
        public DonationDef createFromParcel(Parcel in) {
            return new DonationDef(in);
        }

        public DonationDef[] newArray(int size) {
            return new DonationDef[size];
        }
    };

    @Override
    public String toString() {
        return "sponsor id:" + sId;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sId;
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
        DonationDef other = (DonationDef) obj;

        return (sId == ((DonationDef) obj).sId);
    }
}
