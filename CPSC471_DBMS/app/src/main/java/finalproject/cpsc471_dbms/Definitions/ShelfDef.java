package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class ShelfDef {
    private int shelfNumber = -1;
    private int sectId = -1;

    public ShelfDef() {
        super();
    }

    public ShelfDef(int sectId, int shelfNumber) {
        super();
        this.sectId = sectId;
        this.shelfNumber = shelfNumber;
    }

    public ShelfDef(int sectId) {
        this.sectId = sectId;
    }

    private ShelfDef(Parcel in) {
        super();
        this.shelfNumber = in.readInt();
        this.sectId = in.readInt();
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public int getSectId() {
        return sectId;
    }

    public void setSectId(int sectId) {
        this.sectId = sectId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getShelfNumber());
        parcel.writeInt(getSectId());
    }

    public static final Parcelable.Creator<ShelfDef> CREATOR = new Parcelable.Creator<ShelfDef>() {
        public ShelfDef createFromParcel(Parcel in) {
            return new ShelfDef(in);
        }

        public ShelfDef[] newArray(int size) {
            return new ShelfDef[size];
        }
    };

    @Override
    public String toString() {
        return "sectId:" + sectId + ", shelf number:" + shelfNumber;
    }

    //public int getHashCode(int i);
    //public int getHashCode(String i);

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sectId;
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

        return (sectId == ((ShelfDef) obj).sectId);
    }
}
