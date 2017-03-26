package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Time {private int id;
    private int start;
    private int end;
    private int sponsorID;
    private String title;

    public Time() {
        super();
    }

    public Time(int start, int end, int sponsorID, String title) {
        super();
        this.start = start;
        this.end = end;
        this.sponsorID = sponsorID;
        this.title = title;
    }

    /* public Time(int start) {
        this.start = name;
    } */

    private Time(Parcel in) {
        super();
        this.start = in.readInt();
        this.end = in.readInt();
        this.sponsorID = in.readInt();
        this.title = in.readString()
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.id = id;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(int sponsorID) {
        this.sponsorID = sponsorID;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "start:" + start + ", end:" + end + ", sponsor ID:" + sponsorID + ", title:" + title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getStart());
        parcel.writeInt(getEnd());
        parcel.writeInt(getSponsorID());
        parcel.writeString(getTitle());
    }

    public static final Parcelable.Creator<Time> CREATOR = new Parcelable.Creator<Time>() {
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sponsorID;
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
        Time other = (Time) obj;
        if (sponsorID != other.sponsorID)
            return false;
        return true;
    }

}
