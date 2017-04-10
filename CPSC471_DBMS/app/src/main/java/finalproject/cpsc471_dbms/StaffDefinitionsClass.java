package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class StaffDefinitionsClass {
    private int columnId;
    private int salary;
    private int ssn;
    private int workId;

    public StaffDefinitionsClass() {
        super();
    }

    public StaffDefinitionsClass(int columnId, int salary, int ssn, int workId) {
        super();
        this.columnId = columnId;
        this.salary = salary;
        this.ssn = ssn;
        this.workId = workId;
    }

    private StaffDefinitionsClass(Parcel in) {
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

    public String getSalary() {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getColumnId());
        parcel.writeInt(getSalary());
        parcel.writeInt(getSsn());
        parcel.writeInt(getWorkId());
    }

    public static final Parcelable.Creator<StaffDefinitionsClass> CREATOR = new Parcelable.Creator<StaffDefinitionsClass>() {
        public StaffDefinitionsClass createFromParcel(Parcel in) {
            return new StaffDefinitionsClass(in);
        }

        public StaffDefinitionsClass[] newArray(int size) {
            return new StaffDefinitionsClass[size];
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StaffDefinitionsClass other = (StaffDefinitionsClass) obj;
        if (workId != other.workId)
            return false;
        return true;
    }

}

