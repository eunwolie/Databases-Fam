package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class HelpsDefinitionsClass {
        private int userId;
        private int workId;

        public HelpsDefinitionsClass() {
            super();
        }

        public HelpsDefinitionsClass(int userId, int workId) {
            super();
            this.userId = userId;
            this.workId = workId;
        }

        private HelpsDefinitionsClass(Parcel in) {
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

        public static final Parcelable.Creator<HelpsDefinitionsClass> CREATOR = new Parcelable.Creator<HelpsDefinitionsClass>() {
            public HelpsDefinitionsClass createFromParcel(Parcel in) {
                return new HelpsDefinitionsClass(in);
            }

            public HelpsDefinitionsClass[] newArray(int size) {
                return new HelpsDefinitionsClass[size];
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
            HelpsDefinitionsClass other = (HelpsDefinitionsClass) obj;
            if (userId != other.userId)
                return false;
            return true;
        }
    }
