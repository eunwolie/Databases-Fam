package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class SponsorDef {
    private int sponsorId = -1;
    private String reason = null;
    private String name = null;

    public SponsorDef() {
        super();
    }

    public SponsorDef(int sponsorId, String reason, String name) {
        super();
        this.reason = reason;
        this.name = name;
        this.sponsorId = sponsorId;
    }

    public SponsorDef(String name, String reason) {
        this.reason = reason;
        this.name = name;
    }

    private SponsorDef(Parcel in) {
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

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getSponsorId());
        parcel.writeString(getName());
        parcel.writeString(getReason());
    }

    public static final Parcelable.Creator<SponsorDef> CREATOR = new Parcelable.Creator<SponsorDef>() {
        public SponsorDef createFromParcel(Parcel in) {
            return new SponsorDef(in);
        }

        public SponsorDef[] newArray(int size) {
            return new SponsorDef[size];
        }
    };

    @Override
    public String toString() {
        return "Sponsor ID:" + sponsorId + ", name:" + name + ", reason:" + reason;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + sponsorId;
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

        return (sponsorId == ((SponsorDef) obj).sponsorId);
    }
}