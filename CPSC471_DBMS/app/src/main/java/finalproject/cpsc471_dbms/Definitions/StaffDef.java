package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class StaffDef {
    private int columnId;
    private int salary;
    private int ssn;
    private int workId;

    public StaffDef() {
        super();
    }

    public StaffDef(int columnId, int salary, int ssn, int workId) {
        super();
        this.columnId = columnId;
        this.salary = salary;
        this.ssn = ssn;
        this.workId = workId;
    }

    private StaffDef(Parcel in) {
        super();
        this.columnId = in.readInt();
        this.salary = in.readInt();
        this.ssn = in.readInt();
        this.workId = in.readInt();
    }

    public int getColumnId() {
        return columnId;
    }

    public void setColumnId(int columnId) {
        this.columnId = columnId;
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
        return "Column ID:" + columnId + ", Salary:" + salary + ", SSN:" + ssn + ", Word ID:" + workId;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getColumnId());
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

