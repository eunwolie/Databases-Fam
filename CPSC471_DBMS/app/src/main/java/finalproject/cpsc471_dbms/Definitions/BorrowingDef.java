package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class BorrowingDef {
    private String borrowDate;
    private String returnDate;
    private String overdueDate;
    private String status;
    private int id;
    private int isbn;

    public BorrowingDef() {
        super();
    }

    public BorrowingDef(String borrowDate, String returnDate, String overdueDate, String status, int id, int isbn) {
        super();
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueDate = overdueDate;
        this.status = status;
        this.id = id;
        this.isbn = isbn;
    }

    private BorrowingDef(Parcel in) {
        super();
        this.borrowDate = in.readString();
        this.returnDate = in.readString();
        this.overdueDate = in.readString();
        this.status = in.readString();
        this.id = in.readInt();
        this.isbn = in.readInt();
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getOverdueDate(){
        return overdueDate;
    }

    public void setOverdueDate(String overdueDate){
        this.overdueDate = overdueDate;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }
    public int getId(){return id;}
    public void setId(int id){ this.id = id; }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getBorrowDate());
        parcel.writeString(getReturnDate());
        parcel.writeString(getOverdueDate());
        parcel.writeInt(getId());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<BorrowingDef> CREATOR = new Parcelable.Creator<BorrowingDef>() {
        public BorrowingDef createFromParcel(Parcel in) {
            return new BorrowingDef(in);
        }

        public BorrowingDef[] newArray(int size) {
            return new BorrowingDef[size];
        }
    };

    @Override
    public String toString() {
        return "Borrow date:" + borrowDate + ", Return date:" + returnDate + ", Overdue date:" + overdueDate + ", isbn:" + isbn + ", ID:" + id;
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

        return (isbn != ((BorrowingDef) obj).isbn);
    }
}
