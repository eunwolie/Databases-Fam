package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class ShelfDef {
    private int shelfNumber = -1;
    private int isbn = -1;
    private String genre = null;

    public ShelfDef() {
        super();
    }

    public ShelfDef(String genre, int shelfNumber, int isbn) {
        super();
        this.genre = genre;
        this.shelfNumber = shelfNumber;
        this.isbn = isbn;
    }

    public ShelfDef(String genre) {
        this.genre = genre;
    }

    private ShelfDef(Parcel in) {
        super();
        this.shelfNumber = in.readInt();
        this.isbn = in.readInt();
        this.genre = in.readString();
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getIsbn());
        parcel.writeInt(getShelfNumber());
        parcel.writeString(getGenre());
    }

    public static final Parcelable.Creator<ShelfDef> CREATOR = new Parcelable.Creator<ShelfDef>() {
        public ShelfDef createFromParcel(Parcel in) {
            return new ShelfDef(in);
        }

        public ShelfDef[] newArray(int size) {
            return new ShelfDef[size];
        }
    };

    @Override
    public String toString() {
        return "genre:" + genre + ", isbn:" + isbn + ", shelf number:" + shelfNumber;
    }

    //public int getHashCode(int i);
    //public int getHashCode(String i);

    // TODO : Need to adjust genre because it's the pk but it ain't a number
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        //result = prime * result + genre;
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

        return (genre == ((ShelfDef) obj).genre);
    }
}
