package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class TypeDef {
    protected int length = -1;
    protected int isbn = -1;

    public TypeDef() {
        super();
    }

    public TypeDef(int length, int isbn) {
        super();
        this.length = length;
        this.isbn = isbn;
    }

    private TypeDef(Parcel in) {
        super();
        this.length = in.readInt();
        this.isbn = in.readInt();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {this.length = length;}

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getLength());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<TypeDef> CREATOR = new Parcelable.Creator<TypeDef>() {
        public TypeDef createFromParcel(Parcel in) { return new TypeDef(in); }

        public TypeDef[] newArray(int size) {return new TypeDef[size]; }
    };

    @Override
    public String toString() {
        return "length:" + length + ", isbn:" + isbn;
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

        return (isbn == ((TypeDef) obj).isbn);
    }
}
