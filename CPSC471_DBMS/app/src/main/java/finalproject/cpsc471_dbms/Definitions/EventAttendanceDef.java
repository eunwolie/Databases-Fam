package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class EventAttendanceDef {
    private int id = -1;
    private int startTime = -1;
    private int date = -1;
    private int workID = -1;

    public EventAttendanceDef() {
        super();
    }


    public EventAttendanceDef(int id, int startTime, int date, int workID) {
        super();
        this.id = id;
        this.startTime = startTime;
        this.date = date;
        this.workID = workID;
    }

    private EventAttendanceDef(Parcel in) {
        super();
        this.id = in.readInt();
        this.startTime = in.readInt();
        this.date = in.readInt();
        this.workID = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getWorkID() {
        return workID;
    }

    public void setWorkID(int workID) {
        this.workID = workID;
    }

    @Override
    public String toString() {
        return "id:" + id + ", start time:" + startTime + ", date:" + date;
}

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeInt(getStartTime());
        parcel.writeInt(getDate());
        parcel.writeInt(getWorkID());
    }

    public static final Parcelable.Creator<EventAttendanceDef> CREATOR = new Parcelable.Creator<EventAttendanceDef>() {
        public EventAttendanceDef createFromParcel(Parcel in) {
            return new EventAttendanceDef(in);
        }

        public EventAttendanceDef[] newArray(int size) {
            return new EventAttendanceDef[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        return (id == ((EventAttendanceDef) obj).id);
    }
}
