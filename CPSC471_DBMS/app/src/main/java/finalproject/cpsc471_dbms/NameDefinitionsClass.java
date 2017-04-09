package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class NameDefinitionsClass {
        private String fName;
        private String minit;
        private String lName;
        private int userID;

        public NameDefinitionsClass() {
            super();
        }

        public NameDefinitionsClass(int id, String name) {
            super();
            this.fName = fName;
            this.minit = minit;
            this.lName = lName;
            this.userID = userID;
        }

        public NameDefinitionsClass(String name) {
            this.fName = fName;
            this.minit = minit;
            this.lName = lName;
        }

        private NameDefinitionsClass(Parcel in) {
            super();
            this.fName = in.readString();;
            this.minit = in.readString();;
            this.lName = in.readString();;
            this.userID = in.readInt();
        }

        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }

        public String getMinit() {
            return minit;
        }

        public void setMinit(String minit) {
            this.minit = minit;
        }

        public String getlName() {
            return lName;
        }

        public void setlName(String lName) {
            this.lName = lName;
        }

        public int getuID() {
            return userID;
        }

        public void setuID(int userID) {
            this.userID = userID;
        }
        @Override
        public String toString() {
            return "first name:" + fName + ", middle name/initials:" + minit + ", last name:" + lName + ", user ID:" + userID;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeString(getfName());
            parcel.writeString(getMinit());
            parcel.writeString(getlName());
            parcel.writeInt(getuID());
        }

        public static final Parcelable.Creator<NameDefinitionsClass> CREATOR = new Parcelable.Creator<NameDefinitionsClass>() {
            public NameDefinitionsClass createFromParcel(Parcel in) {
                return new NameDefinitionsClass(in);
            }

            public NameDefinitionsClass[] newArray(int size) {
                return new NameDefinitionsClass[size];
            }
        };

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + userID;
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
            NameDefinitionsClass other = (NameDefinitionsClass) obj;
            if (userID != other.userID)
                return false;
            return true;
        }
    }

