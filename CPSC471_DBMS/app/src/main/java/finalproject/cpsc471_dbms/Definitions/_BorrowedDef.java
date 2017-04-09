package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class _BorrowedDef {
    private int borrowDate = -1;
    private int returnDate = -1;
    private String bookTitle = null;
    private int ISBN = -1;

    /*
        String[] allBooks = new String[]{BorrowingTable.BORROW_DATE,
                BorrowingTable.RETURN_DATE, MaterialTable.TITLE, MaterialTable._ID};
     */

    public _BorrowedDef() {
        super();
    }

    public _BorrowedDef(int borrowDate, int returnDate, String bookTitle, int isbn) {
        super();
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.bookTitle = bookTitle;
        this.ISBN = isbn;
    }

    private _BorrowedDef(Parcel in) {
        super();
        this.borrowDate = in.readInt();
        this.returnDate = in.readInt();
        this.bookTitle = in.readString();
        this.ISBN = in.readInt();
    }

    public int getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(int borrowDate) {
        this.borrowDate = borrowDate;
    }

    public int getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(int returnDate) {
        this.returnDate = returnDate;
    }

    public String getBookTitle(){
        return bookTitle;
    }

    public void setBookTitle(String bookTitle){
        this.bookTitle = bookTitle;
    }

    public int getIsbn(){
        return ISBN;
    }

    public void setIsbn(int isbn){
        this.ISBN = isbn;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(borrowDate);
        parcel.writeInt(returnDate);
        parcel.writeString(bookTitle);
        parcel.writeInt(ISBN);
    }

    public static final Parcelable.Creator<_BorrowedDef> CREATOR = new Parcelable.Creator<_BorrowedDef>() {
        public _BorrowedDef createFromParcel(Parcel in) {
            return new _BorrowedDef(in);
        }

        public _BorrowedDef[] newArray(int size) {
            return new _BorrowedDef[size];
        }
    };

    @Override
    public String toString() {
        return "borrow date:" + borrowDate
                + ", return date:" + returnDate
                + ", book title:" + bookTitle
                + ", isbn:" + ISBN;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ISBN;
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

        return (ISBN == ((_BorrowedDef) obj).ISBN);
    }
}
