package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

/*

 */

public class LanguageDef {
    private String language = null;
    private int isbn = -1;

    public LanguageDef() {
        super();
    }

    public LanguageDef(String language, int isbn) {
        super();
        this.language = language;
        this.isbn = isbn;
    }

    private LanguageDef(Parcel in) {
        super();
        this.language = in.readString();
        this.isbn = in.readInt();
    }

    public int getIsbn(){
        return isbn;
    }

    public void setIsbn(int isbn){
        this.isbn = isbn;
    }

    public String getLanguage(){return language;}

    public void setLanguage(String language){ this.language = language; }

    public int describeContents() { return 0; }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(getLanguage());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<LanguageDef> CREATOR = new Parcelable.Creator<LanguageDef>() {
        public LanguageDef createFromParcel(Parcel in) {
            return  new LanguageDef(in);
        }

        public LanguageDef[] newArray(int size) {
            return new LanguageDef[size];
        }
    };

    @Override
    public String toString() {
        return "Isbn:" + isbn + ", language:" + language;
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

        return (isbn != ((LanguageDef) obj).isbn);
    }
}
