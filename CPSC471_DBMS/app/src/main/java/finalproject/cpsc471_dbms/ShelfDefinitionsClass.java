package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class ShelfDefinitionsClass {
    int shelfNumber;
    int isbn;
    int genre;

    public ShelfDefinitionsClass() {
        super();
    }

    public ShelfDefinitionsClass(String genre, int shelfNumber, int isbn) {
        super();
        this.genre = genre;
        this.shelfNumber = shelfNumber;
        this.isbn = isbn;
    }

    public ShelfDefinitionsClass(String genre) {
        this.genre = genre;
    }

    private ShelfDefinitionsClass(Parcel in) {
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

    @Override
    public String toString() {
        return "genre:" + genre + ", isbn:" + isbn + ", shelf number:" + shelfNumber;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getIsbn());
        parcel.writeInt(getShelfNumber());
        parcel.writeString(getGenre());
    }

    public static final Parcelable.Creator<ShelfDefinitionsClass> CREATOR = new Parcelable.Creator<ShelfDefinitionsClass>() {
        public ShelfDefinitionsClass createFromParcel(Parcel in) {
            return new ShelfDefinitionsClass(in);
        }

        public ShelfDefinitionsClass[] newArray(int size) {
            return new ShelfDefinitionsClass[size];
        }
    };

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + genre;
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
        ShelfDefinitionsClass other = (ShelfDefinitionsClass) obj;
        if (genre != other.genre)
            return false;
        return true;
    }
}
