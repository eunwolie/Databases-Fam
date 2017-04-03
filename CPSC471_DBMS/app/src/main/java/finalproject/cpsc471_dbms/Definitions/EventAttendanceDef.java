package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class EventAttendanceDef {
    private int sId;
    private int id;

    public EventAttendanceDef() {
        super();
    }


    public EventAttendanceDef(int id, int sId) {
        super();
        this.id = id;
        this.sId = sId;
    }

    /* public EventAttendance(String name) {
        this.name = name;
    } */

    private EventAttendanceDef(Parcel in) {
        super();
        this.id = in.readInt();
        this.sId = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsId() {
        return sId;
    }

    public void setName(String name) {
        this.sId = sId;
    }

    @Override
    public String toString() {
        return "id:" + id + ", sponsor ID:" + sId;
}

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeInt(getsId());
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
