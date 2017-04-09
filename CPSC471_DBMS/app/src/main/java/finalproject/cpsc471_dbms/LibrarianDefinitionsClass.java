package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class LibrarianDefinitionsClass {
        private int deskNo;
        private int workId;

        public LibrarianDefinitionsClass() {
            super();
        }

        public LibrarianDefinitionsClass(int deskNo, int workId) {
            super();
            this.deskNo = deskNo;
            this.workId = workId;
        }

        private LibrarianDefinitionsClass(Parcel in) {
            super();
            this.deskNo = in.readInt();
            this.workId = in.readInt();
        }

        public int getdeskNo() {
            return deskNo;
        }

        public void setDeskNo(int deskNo) {
            this.deskNo = deskNo;
        }

        public String getWorkId() {
            return workId;
        }

        public void setWorkId(int workId) {
            this.workId = workId;
        }

        @Override
        public String toString() {
            return "desk No:" + deskNo + ", work ID:" + workId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt(getdeskNo());
            parcel.writeInt(getWorkId());
        }

        public static final Parcelable.Creator<LibrarianDefinitionsClass> CREATOR = new Parcelable.Creator<LibrarianDefinitionsClass>() {
            public LibrarianDefinitionsClass createFromParcel(Parcel in) {
                return new LibrarianDefinitionsClass(in);
            }

            public LibrarianDefinitionsClass[] newArray(int size) {
                return new LibrarianDefinitionsClass[size];
            }
        };

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + deskNo;
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
            LibrarianDefinitionsClass other = (LibrarianDefinitionsClass) obj;
            if (deskNo != other.deskNo)
                return false;
            return true;
        }
    }