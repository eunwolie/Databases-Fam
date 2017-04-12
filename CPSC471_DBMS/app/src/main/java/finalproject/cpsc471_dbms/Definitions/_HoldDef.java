package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

/*

 */

public class _HoldDef {
    private int holdDate = -1;
    private int endDate = -1;
    private String bookTitle = null;
    private int isbn = -1;

    public _HoldDef() {
        super();
    }

    public _HoldDef(int holdDate, int endDate, String bookTitle, int isbn) {
        super();
        this.holdDate = holdDate;
        this.endDate = endDate;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
    }

    private _HoldDef(Parcel in) {
        super();
        this.holdDate = in.readInt();
        this.endDate = in.readInt();
        this.bookTitle = in.readString();
        this.isbn = in.readInt();
    }

    public int getHoldDate() {
        return holdDate;
    }

    public void setHoldDate(int holdDate) {
        this.holdDate = holdDate;
    }

    public int getEndDate() {
        return endDate;
    }

    public void setEndDate(int returnDate) {
        this.endDate = endDate;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public String getBookTitle(){return bookTitle;}

    public void setBookTitle(String bookTitle){ this.bookTitle = bookTitle; }

    public int describeContents() { return 0; }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getHoldDate());
        parcel.writeInt(getEndDate());
        parcel.writeString(getBookTitle());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<_HoldDef> CREATOR = new Parcelable.Creator<_HoldDef>() {
        public _HoldDef createFromParcel(Parcel in) {
            return  new _HoldDef(in);
        }

        public _HoldDef[] newArray(int size) {
            return new _HoldDef[size];
        }
    };

    @Override
    public String toString() {
        return "Hold date:" + holdDate + ", hold end date:" + endDate + ", isbn:" + isbn + ", book title:" + bookTitle;
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
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        return (isbn != ((_HoldDef) obj).isbn);
    }
}
