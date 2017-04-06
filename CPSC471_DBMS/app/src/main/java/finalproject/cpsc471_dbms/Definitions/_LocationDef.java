package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

/**
 *
 * Handles the location of books
 *
 *
 */

public class _LocationDef {
    private int floor;
    private int section;
    private int shelf;
    private int ISBN;

    public _LocationDef() {
        super();
    }

    public _LocationDef(int floor, int section, int shelf, int isbn) {
        super();
        this.floor = floor;
        this.section = section;
        this.shelf = shelf;
        this.ISBN = isbn;
    }

    private _LocationDef(Parcel in) {
        super();
        this.floor = in.readInt();
        this.section = in.readInt();
        this.shelf = in.readInt();
        this.ISBN = in.readInt();
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) { this.floor = floor; }

    public int getSection() {
        return section;
    }

    public void setSection(int section) { this.section = section; }

    public int getShelf(){
        return shelf;
    }

    public void setShelf(int shelf){
        this.shelf = shelf;
    }

    public int getIsbn(){
        return ISBN;
    }

    public void setIsbn(int isbn){
        this.ISBN = isbn;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getFloor());
        parcel.writeInt(getSection());
        parcel.writeInt(getShelf());
        parcel.writeInt(ISBN);
    }

    public static final Parcelable.Creator<_LocationDef> CREATOR = new Parcelable.Creator<_LocationDef>() {
        public _LocationDef createFromParcel(Parcel in) {
            return new _LocationDef(in);
        }

        public _LocationDef[] newArray(int size) {
            return new _LocationDef[size];
        }
    };

    @Override
    public String toString() {
        return "floor number:" + floor
                + ", section number:" + section
                + ", shelf number:" + shelf
                + ", isbn:" + ISBN;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ISBN;
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

        return (ISBN == ((_LocationDef) obj).ISBN);
    }
}
