package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class SponsorDefinitionsClass {
    private int sponsorId;
    private String reason;
    private String name;

    public SponsorDefinitionsClass() {
        super();
    }

    public SponsorDefinitionsClass(int sponsorId, String reason, String name) {
        super();
        this.reason = reason;
        this.name = name;
        this.sponsorId = sponsorId;
    }

    public SponsorDefinitionsClass(String name, String reason) {
        this.reason = reason;
        this.name = name;
    }

    private SponsorDefinitionsClass(Parcel in) {
        super();
        this.sponsorId = in.readInt();
        this.reason = in.readString();
        this.name = in.readString();
    }

    public int getSponsorId() {
        return sponsorId;
    }

    public void setSponsorId(int sponsorId) {
        this.sponsorId = sponsorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "Sponsor ID:" + sponsorId + ", name:" + name + ", reason:" + reason;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getSponsorId());
        parcel.writeString(getName());
        parcel.writeString(getReason());
    }

    public static final Parcelable.Creator<SponsorDefinitionsClass> CREATOR = new Parcelable.Creator<SponsorDefinitionsClass>() {
        public SponsorDefinitionsClass createFromParcel(Parcel in) {
            return new SponsorDefinitionsClass(in);
        }

        public SponsorDefinitionsClass[] newArray(int size) {
            return new SponsorDefinitionsClass[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sponsorId;
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
        SponsorDefinitionsClass other = (SponsorDefinitionsClass) obj;
        if (sponsorId != other.sponsorId)
            return false;
        return true;
    }
}