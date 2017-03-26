package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Event {
    private int time;
    private String date;
    private String title;
    private int sponsorID;
    private int workID;

    public Event() {
        super();
    }

    public Event(int time, String date, String title, int sponsorID, int workID) {
        super();
        this.time = time;
        this.date = date;
        this.title = title;
        this.sponsorID = sponsorID;
        this.workID = workID;
    }

    public Event(String date, String title) {
        this.date = date;
        this.title = title;
    }

    private Event(Parcel in) {
        super();
        this.time = in.readInt();
        this.date = in.readString();
        this.title = in.readString();
        this.sponsorID = in.readInt();
        this.workID = in.readInt()
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSponsorID() {
        return sponsorID;
    }

    public void setSponsorID(int sponsorID) {
        this.sponsorID = sponsorID;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    @Override
    public String toString() {
        return "time:" + time + ", date:" + date + ", title:" + title + ", sponsor ID:" + sponsorID + ", work ID:" + workID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getTime());
        parcel.writeString(getTitle());
        parcel.writeString(getDate());
        parcel.writeInt(getSponsorID());
        parcel.writeInt(getWorkID());
    }

    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        public Event[] newArray(int size) {
            return new Event[size];
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
        Event other = (Event) obj;
        if (sponsorID != other.sponsorID)
            return false;
        return true;
    }
}
