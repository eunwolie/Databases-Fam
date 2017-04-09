package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class LibHelpDef {
        private int userId;
        private int workId;

        public LibHelpDef() {
            super();
        }

        public LibHelpDef(int userId, int workId) {
            super();
            this.userId = userId;
            this.workId = workId;
        }

        private LibHelpDef(Parcel in) {
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

        public int getWorkId() {
            return workId;
        }

        public void setWorkId(int workId) {
            this.workId = workId;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt(getUserId());
            parcel.writeInt(getWorkId());
        }

        public static final Parcelable.Creator<LibHelpDef> CREATOR = new Parcelable.Creator<LibHelpDef>() {
            public LibHelpDef createFromParcel(Parcel in) {
                return new LibHelpDef(in);
            }

            public LibHelpDef[] newArray(int size) {
                return new LibHelpDef[size];
            }
        };

        @Override
        public String toString() {
        return "user ID:" + userId + ", work ID:" + workId;
    }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + userId + workId;
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

            return (userId != ((LibHelpDef) obj).userId);
        }
    }
