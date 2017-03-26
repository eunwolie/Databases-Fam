package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Sections {
    private String genre;
    private int sNo;
    private int fNo;

    public Sections() {
        super();
    }

    public Sections(String genre, int sNo, int fNo) {
        super();
        this.genre = genre;
        this.sNo = sNo;
        this.fNo = fNo;
    }

    public Sections(String genre) {
        this.genre = genre;
    }

    private Sections(Parcel in) {
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

    @Override
    public String toString() {
        return "genre:" + genre + ", floor number:" + fNo + ", shelf number:" + sNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getfNo());
        parcel.writeInt(getsNo());
        parcel.writeString(getGenre());
    }

    public static final Parcelable.Creator<Sections> CREATOR = new Parcelable.Creator<Sections>() {
        public Sections createFromParcel(Parcel in) {
            return new Sections(in);
        }

        public Sections[] newArray(int size) {
            return new Sections[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + genre;
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
        Sections other = (Sections) obj;
        if (genre != other.genre)
            return false;
        return true;
    }
}
