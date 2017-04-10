package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class VisualsDefinitionsClass {
    private int pages;
    private int isbn;

    public VisualsDefinitionsClass() {
        super();
    }

    public VisualsDefinitionsClass(int pages, int isbn) {
        super();
        this.pages = pages;
        this.isbn = isbn;
    }

    private VisualsDefinitionsClass(Parcel in) {
        super();
        this.pages = in.readInt();
        this.isbn = in.readInt();
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {this.pages = pages;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "pages:" + pages + ", isbn:" + isbn;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getPages());
        parcel.writeString(getIsbn());
    }

    public static final Parcelable.Creator<VisualsDefinitionsClass> CREATOR = new Parcelable.Creator<VisualsDefinitionsClass>() {
        public VisualsDefinitionsClass createFromParcel(Parcel in) {
            return new VisualsDefinitionsClass(in);
        }

        public VisualsDefinitionsClass[] newArray(int size) {
            return new VisualsDefinitionsClass[size]; }
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
        VisualsDefinitionsClass other = (VisualsDefinitionsClass) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}
