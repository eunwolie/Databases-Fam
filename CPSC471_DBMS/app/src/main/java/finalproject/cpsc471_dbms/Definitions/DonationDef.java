package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class DonationDef {

    private int sId = -1;
    private int isbn = -1;

    public DonationDef() {
        super();
    }

    public DonationDef(int sId, int isbn) {
        super();
        this.sId = sId;
        this.isbn = isbn;
    }


    private DonationDef(Parcel in) {
        super();
        this.sId = in.readInt();
        this.isbn = in.readInt();
    }

    public int getsId() {
        return sId;
    }

    public void setId(int sId) {
        this.sId = sId;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getsId());
        parcel.writeInt(getIsbn());
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
        return "sponsor id:" + sId + ", isbn:" + isbn;
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
