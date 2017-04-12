package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class VisualsDef extends TypeDef{
    private int hasEbook = -1;

    public VisualsDef() {
        super();
    }

    public VisualsDef(int pages, int isbn, int hasEbook) {
        super(pages, isbn);
        this.isbn = isbn;
        this.hasEbook = hasEbook;
    }

    private VisualsDef(Parcel in) {
        super();
        this.length = in.readInt();
        this.isbn = in.readInt();
        this.hasEbook = in.readInt();
    }

    public int getHasEBook() {
        return hasEbook;
    }

    public void setHasEBook(int hasEbook) {
        this.hasEbook = hasEbook;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getLength());
        parcel.writeInt(getIsbn());
        parcel.writeInt(getHasEBook());
    }

    public static final Parcelable.Creator<VisualsDef> CREATOR = new Parcelable.Creator<VisualsDef>() {
        public VisualsDef createFromParcel(Parcel in) {
            return new VisualsDef(in);
        }

        public VisualsDef[] newArray(int size) {
            return new VisualsDef[size]; }
    };
}
