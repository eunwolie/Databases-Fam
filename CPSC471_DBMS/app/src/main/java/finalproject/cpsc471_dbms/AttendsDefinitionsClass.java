package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class AttendsDefinitionsClass {
    private int sId;
    private int id;

    public AttendsDefinitionsClass() {
        super();
    }


    public AttendsDefinitionsClass(int id, int sId) {
        super();
        this.id = id;
        this.sId = sId;
    }

    /* public Attends(String name) {
        this.name = name;
    } */

    private AttendsDefinitionsClass(Parcel in) {
        super();
        this.id = in.readInt();
        this.sId = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getsId() {
        return sId;
    }

    public void setName(String name) {
        this.sId = sId;
    }

    @Override
    public String toString() {
        return "id:" + id + ", sponsor ID:" + sId;
}

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeInt(getsId());
    }

    public static final Parcelable.Creator<AttendsDefinitionsClass> CREATOR = new Parcelable.Creator<AttendsDefinitionsClass>() {
        public AttendsDefinitionsClass createFromParcel(Parcel in) {
            return new AttendsDefinitionsClass(in);
        }

        public AttendsDefinitionsClass[] newArray(int size) {
            return new AttendsDefinitionsClass[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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
        AttendsDefinitionsClass other = (AttendsDefinitionsClass) obj;
        if (id != other.id)
            return false;
        return true;
    }
}
