package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class EventDef {
    private int endTime;
    private int startTime;
    private int date;
    private String title;
    private int sponsorID;
    private int workID;
    private String description;

    public EventDef() {
        super();
    }

    public EventDef(int startTime, int endTime, int date, String title, int sponsorID, int workID, String description) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.title = title;
        this.sponsorID = sponsorID;
        this.workID = workID;
        this.description = description;
    }

    public EventDef(int date, String title) {
        this.date = date;
        this.title = title;
    }

    private EventDef(Parcel in) {
        super();
        this.startTime = in.readInt();
        this.endTime = in.readInt();
        this.date = in.readInt();
        this.title = in.readString();
        this.sponsorID = in.readInt();
        this.workID = in.readInt();
        this.description = in.readString();
    }

    public int getStartTime() { return startTime; }

    public void setStartTime(int startTime) { this.startTime = startTime; }

    public int getEndTime() { return endTime; }

    public void setEndTime(int endTime) { this.endTime = endTime; }

    public int getDate() { return date; }

    public void setDate(int date) { this.date = date; }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "start time:" + startTime
                + ", end time:" + endTime
                + ", date:" + date
                + ", title:" + title
                + ", sponsor ID:" + sponsorID
                + ", work ID:" + workID
                + ", description:" + description;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getStartTime());
        parcel.writeInt(getEndTime());
        parcel.writeString(getTitle());
        parcel.writeInt(getDate());
        parcel.writeInt(getSponsorID());
        parcel.writeInt(getWorkID());
        parcel.writeString(getDescription());
    }

    public static final Parcelable.Creator<EventDef> CREATOR = new Parcelable.Creator<EventDef>() {
        public EventDef createFromParcel(Parcel in) {
            return new EventDef(in);
        }

        public EventDef[] newArray(int size) {
            return new EventDef[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sponsorID;
        return result;
    }

    // TODO : Fix the equals of this to include date, time, and hostid
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;

        return (sponsorID == ((EventDef) obj).sponsorID);
    }
}

