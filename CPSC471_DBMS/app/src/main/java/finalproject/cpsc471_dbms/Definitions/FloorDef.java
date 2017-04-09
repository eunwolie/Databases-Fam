package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class FloorDef {
    private int fNumber = -1;
    private int fComputer = -1;
    private int workId = -1;

    public FloorDef() {
        super();
    }

    public FloorDef(int fNumber, int fComputer, int workId) {
        super();
        this.fNumber = fNumber;
        this.fComputer = fComputer;
        this.workId = workId;
    }

    private FloorDef(Parcel in) {
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

    public int getfComputer() {
        return fComputer;
    }

    public void setfComputer(int fComputer) {
        this.fComputer = fComputer;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getfNumber());
        parcel.writeInt(getfComputer());
        parcel.writeInt(getWorkId());
    }

    public static final Parcelable.Creator<FloorDef> CREATOR = new Parcelable.Creator<FloorDef>() {
        public FloorDef createFromParcel(Parcel in) {
            return new FloorDef(in);
        }

        public FloorDef[] newArray(int size) {
            return new FloorDef[size];
        }
    };

    @Override
    public String toString() {
        return "floor number:" + fNumber + ", floor computer:" + fComputer + ", work ID;" + workId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + workId;
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
        return (workId == ((FloorDef) obj).workId);
    }
}
