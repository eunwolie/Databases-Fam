package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class SectionDef {
    private String genre = null;
    private int sNo = -1;
    private int fNo = -1;

    public SectionDef() {
        super();
    }

    public SectionDef(String genre, int sNo, int fNo) {
        super();
        this.genre = genre;
        this.sNo = sNo;
        this.fNo = fNo;
    }

    public SectionDef(String genre) {
        this.genre = genre;
    }

    private SectionDef(Parcel in) {
        super();
        this.fNo = in.readInt();
        this.sNo = in.readInt();
        this.genre = in.readString();
    }

    public int getfNo() {
        return fNo;
    }

    public void setfNo(int fNo) {
        this.fNo = fNo;
    }

    public int getsNo() {
        return sNo;
    }

    public void setsNo(int sNo) {
        this.sNo = sNo;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getfNo());
        parcel.writeInt(getsNo());
        parcel.writeString(getGenre());
    }

    public static final Parcelable.Creator<SectionDef> CREATOR = new Parcelable.Creator<SectionDef>() {
        public SectionDef createFromParcel(Parcel in) {
            return new SectionDef(in);
        }

        public SectionDef[] newArray(int size) {
            return new SectionDef[size];
        }
    };

    @Override
    public String toString() {
        return "genre:" + genre + ", floor number:" + fNo;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + fNo;
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
        SectionDef other = (SectionDef) obj;
        if (sNo != other.sNo)
            return false;
        return true;
    }
}

