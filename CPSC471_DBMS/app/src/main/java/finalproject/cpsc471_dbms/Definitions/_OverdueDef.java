package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

/*

 */

public class _OverdueDef {
    private String bookTitle = null;
    private int isbn = -1;
    private int days = -1;

    public _OverdueDef() {
        super();
    }

    public _OverdueDef(String bookTitle, int isbn, int days) {
        super();
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.days = days;
    }

    private _OverdueDef(Parcel in) {
        super();
        this.bookTitle = in.readString();
        this.isbn = in.readInt();
        this.days = in.readInt();
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public String getBookTitle(){return bookTitle;}

    public void setBookTitle(String bookTitle){ this.bookTitle = bookTitle; }

    public int getDaysLate(){return days;}

    public void setDaysLate(int days){ this.days = days; }

    public int describeContents() { return 0; }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getBookTitle());
        parcel.writeInt(getIsbn());
        parcel.writeInt(getDaysLate());
    }

    public static final Parcelable.Creator<_OverdueDef> CREATOR = new Parcelable.Creator<_OverdueDef>() {
        public _OverdueDef createFromParcel(Parcel in) {
            return  new _OverdueDef(in);
        }

        public _OverdueDef[] newArray(int size) {
            return new _OverdueDef[size];
        }
    };

    @Override
    public String toString() {
        return "isbn:" + isbn + ", book title:" + bookTitle + ", days late:" + days;
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

        return (isbn != ((_OverdueDef) obj).isbn);
    }
}
