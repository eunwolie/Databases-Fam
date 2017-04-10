package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class EventTimeDef {
    private int id;
    private int start;
    private int end;
    private int sponsorID;
    private String title;

    public EventTimeDef() {
        super();
    }

    public EventTimeDef(int id, int start, int end, int sponsorID, String title) {
        super();
        this.id = id;
        this.start = start;
        this.end = end;
        this.sponsorID = sponsorID;
        this.title = title;
    }

    private EventTimeDef(Parcel in) {
        super();
        this.id = in.readInt();
        this.start = in.readInt();
        this.end = in.readInt();
        this.sponsorID = in.readInt();
        this.title = in.readString();
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.id = id;
    }

    public int getEnd() {
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel) {
        parcel.writeInt(getStart());
        parcel.writeInt(getEnd());
        parcel.writeInt(getSponsorID());
        parcel.writeString(getTitle());
    }

    public static final Parcelable.Creator<EventTimeDef> CREATOR = new Parcelable.Creator<EventTimeDef>() {
        public EventTimeDef createFromParcel(Parcel in) {
            return new EventTimeDef(in);
        }

        public EventTimeDef[] newArray(int size) {
            return new EventTimeDef[size];
        }
    };

    @Override
    public String toString() {
        return "start:" + start + ", end:" + end + ", sponsor ID:" + sponsorID + ", title:" + title;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sponsorID;
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
        return (sponsorID == ((EventTimeDef) obj).sponsorID);
    }

}
