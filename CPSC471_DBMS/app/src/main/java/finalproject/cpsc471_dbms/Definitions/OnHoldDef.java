package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

/*

 */

public class OnHoldDef {
    private int holdDate = -1;
    private int endDate = -1;
    private int id = -1;
    private int isbn = -1;

    public OnHoldDef() {
        super();
    }

    public OnHoldDef(int holdDate, int endDate, int id, int isbn) {
        super();
        this.holdDate = holdDate;
        this.endDate = endDate;
        this.id = id;
        this.isbn = isbn;
    }

    private OnHoldDef(Parcel in) {
        super();
        this.holdDate = in.readInt();
        this.endDate = in.readInt();
        this.id = in.readInt();
        this.isbn = in.readInt();
    }

    public int getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(int holdDate) {
        this.holdDate = holdDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int returnDate) {
        this.endDate = endDate;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public int getId(){return id;}

    public void setId(int id){ this.id = id; }

    public int describeContents() { return 0; }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getHoldDate());
        parcel.writeInt(getEndDate());
        parcel.writeInt(getId());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<OnHoldDef> CREATOR = new Parcelable.Creator<OnHoldDef>() {
        public OnHoldDef createFromParcel(Parcel in) {
            return new OnHoldDef(in);
        }

        public OnHoldDef[] newArray(int size) {
            return new OnHoldDef[size];
        }
    };

    @Override
    public String toString() {
        return "Hold date:" + holdDate + ", Hold end date:" + endDate + ", isbn:" + isbn + ", ID:" + id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + isbn;
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

        return (isbn != ((OnHoldDef) obj).isbn);
    }
}
