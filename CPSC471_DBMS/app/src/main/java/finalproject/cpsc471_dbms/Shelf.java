package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class Shelf {
    private int sNumber;
    private int sIsbn;
    private String sGenre;


    public Shelf() {
        super();
    }

    public Shelf(int sNumber, int sIsbn, String sGenre) {
        super();
        this.sNumber = sNumber;
        this.sIsbn = sIsbn;
        this.sGenre = sGenre;
    }

    public Shelf(String sGenre) {
        this.sGenre = sGenre;
    }

    private Shelf(Parcel in) {
        super();
        this.sNumber = in.readInt();
        this.sIsbn = in.readInt();
        this.sGenre = in.readString();
    }

    public int getsNumber() {
        return sNumber;
    }

    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }

    public String getsIsbn() {
        return sIsbn;
    }

    public void setsIsbn(int sIsbn) {
        this.sIsbn = sIsbn;
    }

    public void getsGenre(){
        return sGenre;
    }

    public void setsGenre(String sGenre){
        this.sGenre = sGenre;
    }

    @Override
    public String toString() {
        return "Number:" + sNumber + ", ISBN:" + sIsbn + ", Genre:" + sGenre;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getsNumber());
        parcel.writeInt(getsIsbn());
        parcel.writeString(getsGenre());
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
        result = prime * result + sIsbn;
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
        if (sIsbn != other.sIsbn)
            return false;
        return true;
    }
}