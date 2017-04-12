package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class LibrarianDef {
    private int deskNo = -1;
    private int workId = -1;

    public LibrarianDef() {
        super();
    }

    public LibrarianDef(int deskNo, int workId) {
        super();
        this.deskNo = deskNo;
        this.workId = workId;
    }

    private LibrarianDef(Parcel in) {
        super();
        this.deskNo = in.readInt();
        this.workId = in.readInt();
    }

    public int getdeskNo() {
        return deskNo;
    }

    public void setDeskNo(int deskNo) {
        this.deskNo = deskNo;
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

    public void writeToParcel(Parcel parcel) {
        parcel.writeInt(getdeskNo());
        parcel.writeInt(getWorkId());
    }

    public static final Parcelable.Creator<LibrarianDef> CREATOR = new Parcelable.Creator<LibrarianDef>() {
        public LibrarianDef createFromParcel(Parcel in) {
            return new LibrarianDef(in);
        }

        public LibrarianDef[] newArray(int size) {
            return new LibrarianDef[size];
        }
    };

    @Override
    public String toString() {
        return "desk No:" + deskNo + ", work ID:" + workId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + deskNo;
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

        return (deskNo == ((LibrarianDef) obj).deskNo);
    }
}