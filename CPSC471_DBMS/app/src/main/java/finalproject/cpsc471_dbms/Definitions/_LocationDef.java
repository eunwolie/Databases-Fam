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
    private int floor = -1;
    private int section = -1;
    private int shelf = -1;

    public _LocationDef() {
        super();
    }

    public _LocationDef(int floor, int section, int shelf) {
        super();
        this.floor = floor;
        this.section = section;
        this.shelf = shelf;
    }

    private _LocationDef(Parcel in) {
        super();
        this.floor = in.readInt();
        this.section = in.readInt();
        this.shelf = in.readInt();
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getFloor());
        parcel.writeInt(getSection());
        parcel.writeInt(getShelf());
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
                + ", shelf number:" + shelf;
    }

}
