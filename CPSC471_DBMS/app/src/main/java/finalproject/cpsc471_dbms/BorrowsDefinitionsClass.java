package finalproject.cpsc471_dbms;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class BorrowsDefinitionsClass {
    String borrowDate;
    String returnDate;
    String overdueDate;
    String status;
    int id;
    int isbn;

    public BorrowsDefinitionsClass() {
        super();
    }

    public BorrowsDefinitionsClass(String borrowDate, String returnDate, String overdueDate, String status, int id, int isbn) {
        super();
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.overdueDate = overdueDate;
        this.id = id;
        this.isbn = isbn;
    }

    private BorrowsDefinitionsClass(Parcel in) {
        super();
        this.borrowDate = in.readString();
        this.returnDate = in.readString();
        this.overdueDate = in.readString();
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

    @Override
    public String toString() {
        return "Borrow date:" + borrowDate + ", Return date:" + returnDate + ", Overdue date:" + overdueDate + ", isbn:" + isbn + ", ID:" + id;
    }

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

    public static final Parcelable.Creator<BorrowsDefinitionsClass> CREATOR = new Parcelable.Creator<BorrowsDefinitionsClass>() {
        public BorrowsDefinitionsClass createFromParcel(Parcel in) {
            return new BorrowsDefinitionsClass(in);
        }

        public BorrowsDefinitionsClass[] newArray(int size) {
            return new BorrowsDefinitionsClass[size];
        }
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
        BorrowsDefinitionsClass other = (BorrowsDefinitionsClass) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}
