package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class UserDef {
    private int id = -1;
    private String firstName = null;
    private String minit = null;
    private String lastName = null;
    private String username = null;
    private String address = null;
    private String password = null;
    private int phone = -1;

    public UserDef() {
        super();
    }

    public UserDef(int id, String name) {
        super();
        this.id = id;
        this.firstName = name;
    }

    public UserDef(int id, String firstName, String lastName, String username, String address, String password, int phone) {
        super();
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.password = password;
        this.phone = phone;
    }

    public UserDef(String firstName, String lastName, String username, String address, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.address = address;
        this.password = password;
    }

    private UserDef(Parcel in) {
        super();
        this.id = in.readInt();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.address = in.readString();
        this.password = in.readString();
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getId());
        parcel.writeInt(getPhone());
        parcel.writeString(getFirstName());
        parcel.writeString(getLastName());
        parcel.writeString(getAddress());
        parcel.writeString(getUsername());
        parcel.writeString(getPassword());
    }

    public static final Parcelable.Creator<UserDef> CREATOR = new Parcelable.Creator<UserDef>() {
        public UserDef createFromParcel(Parcel in) {
            return new UserDef(in);
        }

        public UserDef[] newArray(int size) {
            return new UserDef[size];
        }
    };

    @Override
    public String toString() {
        return "id:" + id + ", first name:" + firstName + ", last name:" + lastName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
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

        return (id == ((UserDef) obj).id);
    }

}