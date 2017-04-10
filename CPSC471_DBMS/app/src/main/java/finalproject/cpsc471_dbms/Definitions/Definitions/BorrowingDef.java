package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class BorrowingDef {
    private int borrowDate = -1;
    private int returnDate = -1;
    private int overdueDay = -1;
    private String status = null;
    private int id = -1;
    private int isbn = -1;

    public BorrowingDef() {
        super();
    }

    public BorrowingDef(int borrowDate, int returnDate, int overdueDay, String status, int id, int isbn) {
        super();
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueDay = overdueDay;
        this.status = status;
        this.id = id;
        this.isbn = isbn;
    }

    private BorrowingDef(Parcel in) {
        super();
        this.borrowDate = in.readInt();
        this.returnDate = in.readInt();
        this.overdueDay = in.readInt();
        this.status = in.readString();
        this.id = in.readInt();
        this.isbn = in.readInt();
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

    public int getOverdueDay(){
        return overdueDay;
    }

    public void setOverdueDay(int overdueDay){
        this.overdueDay = overdueDay;
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public String getStatus() {return status;}

    public void setStatus(String stat) { this.status = stat;}

    public int getId(){return id;}

    public void setId(int id){ this.id = id; }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getBorrowDate());
        parcel.writeInt(getReturnDate());
        parcel.writeInt(getOverdueDay());
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
        return "Borrow date:" + borrowDate + ", Return date:" + returnDate + ", Overdue day:" + overdueDay + ", isbn:" + isbn + ", ID:" + id;
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
