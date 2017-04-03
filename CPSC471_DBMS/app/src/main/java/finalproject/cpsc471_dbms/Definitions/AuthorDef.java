package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class AuthorDef {
    private String fName;
    private String minit;
    private String lName;
    private int isbn;

    public AuthorDef() {
        super();
    }

    public AuthorDef(String fName, String minit, String lName, int isbn) {
        super();
        this.fName = fName;
        this.minit = minit;
        this.lName = lName;
        this.isbn = isbn;
    }

    /* public AuthorDef(String name) {
        this.name = name;
    } */
    private AuthorDef(Parcel in) {
        super();
        this.fName = in.readString();
        this.minit = in.readString();
        this.lName = in.readString();
        this.isbn = in.readInt();
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

    public String getlName(){
        return lName;
    }

    public void setlName(String lName){
        this.lName = lName;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getfName());
        parcel.writeString(getMinit());
        parcel.writeString(getlName());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<AuthorDef> CREATOR = new Parcelable.Creator<AuthorDef>() {
        public AuthorDef createFromParcel(Parcel in) {
            return new AuthorDef(in);
        }

        public AuthorDef[] newArray(int size) {
            return new AuthorDef[size];
        }
    };

    @Override
    public String toString() {
        return "first name:" + fName + ", middle name:" + minit + ", last name:" + lName + ", isbn:" + isbn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + isbn;
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

        return (isbn == ((AuthorDef) obj).isbn);
    }
}
