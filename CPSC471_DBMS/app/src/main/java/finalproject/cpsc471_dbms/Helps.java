package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-25.
 */

public class Helps {
    private int userId;
    private int workId;

    public Department() {
        super();
    }

    public Department(int userId, int workId) {
        super();
        this.userId = userId;
        this.workId = workId;
    }

    /*public Department(String name) {
        this.name = name;
    }*/

    private Department(Parcel in) {
        super();
        this.userId = in.readInt();
        this.workId = in.readInt();
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }

    @Override
    public String toString() {
        return "user ID:" + userId + ", work ID:" + workId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getUserId());
        parcel.writeInt(getWorkId());
    }

    public static final Parcelable.Creator<Helps> CREATOR = new Parcelable.Creator<Helps>() {
        public Helps createFromParcel(Parcel in) {
            return new Helps(in);
        }

        public Helps[] newArray(int size) {
            return new Helps[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
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
        Helps other = (Helps) obj;
        if (userId != other.userId)
            return false;
        return true;
    }
}
