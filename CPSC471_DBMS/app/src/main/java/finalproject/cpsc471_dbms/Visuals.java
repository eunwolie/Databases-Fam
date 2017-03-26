package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class Visuals {
    private int pages;
    private int isbn;

    public Visuals() {
        super();
    }

    public Visuals(int pages, int isbn) {
        super();
        this.pages = pages;
        this.isbn = isbn;
    }

    public Visuals(int pages) {
        this.pages = pages;
    }

    private Visuals(Parcel in) {
        super();
        this.isbn = in.readInt();
        this.pages = in.readInt();
    }

    public int getISBN() {
        return isbn;
    }

    public void setISBN(int isbn) {
        this.isbn = isbn;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "isbn:" + isbn + ", pages:" + pages;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getISBN());
        parcel.writeString(getPages());
    }

    public static final Parcelable.Creator<Visuals> CREATOR = new Parcelable.Creator<Visuals>() {
        public Visuals createFromParcel(Parcel in) {
            return new Visuals(in);
        }

        public Visuals[] newArray(int size) {
            return new Visuals[size];
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
        Visuals other = (Visuals) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}

