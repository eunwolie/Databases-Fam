package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class VisualsDef {
    private int pages;
    private int isbn;

    public VisualsDef() {
        super();
    }

    public VisualsDef(int pages, int isbn) {
        super();
        this.pages = pages;
        this.isbn = isbn;
    }

    private VisualsDef(Parcel in) {
        super();
        this.pages = in.readInt();
        this.isbn = in.readInt();
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {this.pages = pages; }

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
        parcel.writeInt(getPages());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<VisualsDef> CREATOR = new Parcelable.Creator<VisualsDef>() {
        public VisualsDef createFromParcel(Parcel in) {
            return new VisualsDef(in);
        }

        public VisualsDef[] newArray(int size) {
            return new VisualsDef[size]; }
    };

    @Override
    public String toString() {
        return "pages:" + pages + ", isbn:" + isbn;
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

        return (isbn == ((VisualsDef) obj).isbn);
    }
}
