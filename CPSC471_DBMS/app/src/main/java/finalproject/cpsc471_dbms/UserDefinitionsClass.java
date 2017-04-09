package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class UserDefinitionsClass {
    private int id;
    private String name;
    private String username;
    private String address;
    private String password;
    private int phone;

    public UserDefinitionsClass() {
        super();
    }

    public UserDefinitionsClass(int id, String name, String username, String address, String password, int phone) {
        super();
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public UserDefinitionsClass(String name, String username, String address, String password) {
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
    }

    private UserDefinitionsClass(Parcel in) {
        super();
        this.id = in.readInt();
        this.name = in.readString();
        this.address = in.readString();
        this.password = in.readInt();
        this.username = in.readString();
        this.phone = in.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsernameName() {
        return username;
    }

    public void setUsernameName(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.name = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "id:" + id + ", name:" + name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeInt(getPhone());
        parcel.writeString(getName());
        parcel.writeString(getAddress());
        parcel.writeString(getUsernameName());
        parcel.writeString(getPassword());
    }

    public static final Parcelable.Creator<UserDefinitionsClass> CREATOR = new Parcelable.Creator<UserDefinitionsClass>() {
        public UserDefinitionsClass createFromParcel(Parcel in) {
            return new UserDefinitionsClass(in);
        }

        public UserDefinitionsClass[] newArray(int size) {
            return new UserDefinitionsClass[size];
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
        Department other = (Department) obj;
        if (id != other.id)
            return false;
        return true;
    }

}