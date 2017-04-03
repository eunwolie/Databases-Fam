package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class AudioDef {
    private int length;
    private int isbn;

    public AudioDef() {
        super();
    }

    public AudioDef(int length, int isbn) {
        super();
        this.length = length;
        this.isbn = isbn;
    }

    private AudioDef(Parcel in) {
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

    public static final Parcelable.Creator<AudioDef> CREATOR = new Parcelable.Creator<AudioDef>() {
        public AudioDef createFromParcel(Parcel in) { return new AudioDef(in); }

        public AudioDef[] newArray(int size) {return new AudioDef[size]; }
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

        return (isbn == ((AudioDef) obj).isbn);
    }
}
