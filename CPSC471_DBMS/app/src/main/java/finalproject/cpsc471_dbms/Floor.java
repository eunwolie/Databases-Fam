package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Floor {
    private int fNumber;
    private int fComputer;
    private int workId;

    public Floor() {
        super();
    }

    public Floor(int id, String name) {
        super();
        this.fNumber = fNumber;
        this.fComputer = fComputer;
        this.workId = workId;
    }

    /*public Floor(String name) {
        this.name = name;
    }*/

    private Floor(Parcel in) {
        super();
        this.fNumber = in.readInt();
        this.fComputer = in.readInt();
        this.workId = in.readInt();
    }

    public int getfNumber() {
        return fNumber;
    }

    public void setfNumber(int fNumber) {
        this.fNumber = fNumber;
    }

    public String getfComputer() {
        return fComputer;
    }

    public void setfComputer(int fComputer) {
        this.fComputer = fComputer;
    }

    public void getWorkId(int workId) {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId;
    }

    @Override
    public String toString() {
        return "floor number:" + fNumber + ", floor computer:" + fComputer + ", work ID;" + workId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getfNumber());
        parcel.writeInt(getfComputer());
        parcel.writeInt(getWorkId());
    }

    public static final Parcelable.Creator<Floor> CREATOR = new Parcelable.Creator<Floor>() {
        public Floor createFromParcel(Parcel in) {
            return new Floor(in);
        }

        public Floor[] newArray(int size) {
            return new Floor[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + workId;
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
        Floor other = (Floor) obj;
        if (workId != other.workId)
            return false;
        return true;
    }
}
