package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class AudioDefinitionsClass {
    private int length;
    private int isbn;

    public AudioDefinitionsClass() {
        super();
    }

    public AudioDefinitionsClass(int length, int isbn) {
        super();
        this.length = length;
        this.isbn = isbn;
    }

    private AudioDefinitionsClass(Parcel in) {
        super();
        this.length = in.readInt();
        this.isbn = in.readInt();
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {this.length = length;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "length:" + length + ", isbn:" + isbn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getLength());
        parcel.writeString(getIsbn());
    }

    public static final Parcelable.Creator<AudioDefinitionsClass> CREATOR = new Parcelable.Creator<AudioDefinitionsClass>() {
        public AudioDefinitionsClass createFromParcel(Parcel in) {
            return new AudioDefinitionsClass(in);
        }

        public AudioDefinitionsClass[] newArray(int size) {
            return new AudioDefinitionsClass[size]; }
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
        AudioDefinitionsClass other = (AudioDefinitionsClass) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}
