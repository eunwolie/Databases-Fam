package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class FloorDefinitionsClass {
        private int fNumber;
        private int fComputer;
        private int workId;

        public FloorDefinitionsClass() {
            super();
        }

        public FloorDefinitionsClass(int id, String name) {
            super();
            this.fNumber = fNumber;
            this.fComputer = fComputer;
            this.workId = workId;
        }

        private FloorDefinitionsClass(Parcel in) {
            super();
            this.fNumber = in.readInt();
            this.fComputer = in.readInt();
            this.workId = in.readInt();
        }

        public int getfNumber() {
            return fNumber;
        }

        public void setfNumber(int fNumber) {
            this.fNumber = fNumber;
        }

        public int getfComputer() {
            return fComputer;
        }

        public void setfComputer(int fComputer) {
            this.fComputer = fComputer;
        }

        public void getWorkId() {
            return workId;
        }

        public void setWorkId(int workId) {
            this.workId = workId;
        }

        @Override
        public String toString() {
            return "floor number:" + fNumber + ", floor computer:" + fComputer + ", work ID;" + workId;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt(getfNumber());
            parcel.writeInt(getfComputer());
            parcel.writeInt(getWorkId());
        }

        public static final Parcelable.Creator<FloorDefinitionsClass> CREATOR = new Parcelable.Creator<FloorDefinitionsClass>() {
            public FloorDefinitionsClass createFromParcel(Parcel in) {
                return new FloorDefinitionsClass(in);
            }

            public FloorDefinitionsClass[] newArray(int size) {
                return new FloorDefinitionsClass[size];
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
            FloorDefinitionsClass other = (FloorDefinitionsClass) obj;
            if (workId != other.workId)
                return false;
            return true;
        }
    }
