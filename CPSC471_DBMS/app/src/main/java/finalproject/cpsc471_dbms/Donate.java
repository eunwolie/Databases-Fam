package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Donate {
    private int sId;
    private int isbn;

    public Donate() {
        super();
    }

    public Donate(int sId, int isbn) {
        super();
        this.sId = sId;
        this.isbn = isbn;
    }

    /*public Donate(String name) {
        this.name = name;
    }*/

    private Donate(Parcel in) {
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

    @Override
    public String toString() {
        return "sponsor id:" + sId + ", isbn:" + isbn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getsId());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<Donate> CREATOR = new Parcelable.Creator<Donate>() {
        public Donate createFromParcel(Parcel in) {
            return new Donate(in);
        }

        public Donate[] newArray(int size) {
            return new Donate[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sId;
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
        Donate other = (Donate) obj;
        if (sId != other.sId)
            return false;
        return true;
    }
}
