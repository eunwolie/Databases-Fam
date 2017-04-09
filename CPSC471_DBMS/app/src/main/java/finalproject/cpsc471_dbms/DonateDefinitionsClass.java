package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class DonateDefinitionsClass {

        private int sId;
        private int isbn;

        public DonateDefinitionsClass() {
            super();
        }

        public DonateDefinitionsClass(int sId, int isbn) {
            super();
            this.sId = sId;
            this.isbn = isbn;
        }


        private DonateDefinitionsClass(Parcel in) {
            super();
            this.sId = in.readInt();
            this.isbn = in.readInt();
        }

        public int getsId() {
            return sId;
        }

        public void setId(int sId) {
            this.sId = sId;
        }

        public int getIsbn() {
            return isbn;
        }

        public void setIsbn(int isbn) {
            this.isbn = isbn;
        }

        @Override
        public String toString() {
            return "sponsor id:" + sId + ", isbn:" + isbn;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel parcel, int flags) {
            parcel.writeInt(getsId());
            parcel.writeInt(getIsbn());
        }

        public static final Parcelable.Creator<DonateDefinitionsClass> CREATOR = new Parcelable.Creator<DonateDefinitionsClass>() {
            public DonateDefinitionsClass createFromParcel(Parcel in) {
                return new DonateDefinitionsClass(in);
            }

            public DonateDefinitionsClass[] newArray(int size) {
                return new DonateDefinitionsClass[size];
            }
        };

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + sId;
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
            DonateDefinitionsClass other = (DonateDefinitionsClass) obj;
            if (sId != other.sId)
                return false;
            return true;
        }
    }
