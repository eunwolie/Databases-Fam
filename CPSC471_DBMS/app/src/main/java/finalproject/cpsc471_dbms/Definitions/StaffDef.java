package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class StaffDef {
    private int uId = -1;
    private int salary = -1;
    private int ssn = -1;
    private int workId = -1;

    public StaffDef() {
        super();
    }

    public StaffDef(int uId, int salary, int ssn, int workId) {
        super();
        this.uId = uId;
        this.salary = salary;
        this.ssn = ssn;
        this.workId = workId;
    }

    private StaffDef(Parcel in) {
        super();
        this.uId = in.readInt();
        this.salary = in.readInt();
        this.ssn = in.readInt();
        this.workId = in.readInt();
    }

    public int getUId() {
        return uId;
    }

    public void setUId(int uId) {
        this.uId = uId;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public int getWorkId() {
        return workId;
    }

    public void setWorkId(int workId) {
        this.workId = workId;
    }


    @Override
    public String toString() {
        return "User ID:" + uId + ", Salary:" + salary + ", SSN:" + ssn + ", Word ID:" + workId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getUId());
        parcel.writeInt(getSalary());
        parcel.writeInt(getSsn());
        parcel.writeInt(getWorkId());
    }

    public static final Parcelable.Creator<StaffDef> CREATOR = new Parcelable.Creator<StaffDef>() {
        public StaffDef createFromParcel(Parcel in) {
            return new StaffDef(in);
        }

        public StaffDef[] newArray(int size) {
            return new StaffDef[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + workId;
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

        return (workId == ((StaffDef) obj).workId);
    }

}

