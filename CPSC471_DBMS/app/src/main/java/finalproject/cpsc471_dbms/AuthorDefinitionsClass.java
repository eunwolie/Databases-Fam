package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class AuthorDefinitionsClass {
    private String fName;
    private String minit;
    private String lName;
    private int isbn;

    public AuthorDefinitionsClass() {
        super();
    }

    public AuthorDefinitionsClass(String fName, String minit, String lName, int isbn) {
        super();
        this.fName = fName;
        this.minit = minit;
        this.lName = lName;
        this.isbn = isbn;
    }

    /* public AuthorDefinitionsClass(String name) {
        this.name = name;
    } */
    private AuthorDefinitionsClass(Parcel in) {
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

    @Override
    public String toString() {
        return "first name:" + fName + ", middle name:" + minit + ", last name:" + lName + ", isbn:" + isbn;
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

    public static final Parcelable.Creator<AuthorDefinitionsClass> CREATOR = new Parcelable.Creator<AuthorDefinitionsClass>() {
        public AuthorDefinitionsClass createFromParcel(Parcel in) {
            return new AuthorDefinitionsClass(in);
        }

        public AuthorDefinitionsClass[] newArray(int size) {
            return new AuthorDefinitionsClass[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + isbn;
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
        AuthorDefinitionsClass other = (AuthorDefinitionsClass) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}
