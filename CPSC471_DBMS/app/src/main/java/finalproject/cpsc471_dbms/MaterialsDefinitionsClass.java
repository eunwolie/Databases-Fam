package finalproject.cpsc471_dbms;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-24.
 */

public class MaterialsDefinitionsClass implements Parcelable{
    private String author;
    private String title;
    private String type;
    private int isbn;
    private String genre;
    private int yearOfCreation;
    private String language;
    private String company;

    public MaterialsDefinitionsClass() {
        super();
    }

    public MaterialsDefinitionsClass(String author, String title, String type, int isbn, String genre, int yearOfCreation, String language, String company){
        super();
        this.author = author;
        this.title = title;
        this.type = type;
        this.isbn = isbn;
        this.genre = genre;
        this.yearOfCreation = yearOfCreation;
        this.language = language;
        this.company = company;
    }

    private MaterialsDefinitionsClass(Parcel in) {
        super();
        this.author = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.isbn = in.readInt();
        this.genre = in.readString();
        this.yearOfCreation = in.readInt();
        this.language = in.readString();
        this.company = in.readString();
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Author:" + author
                + ", Title:" + title
                + ", Type:" + type
                + ", ISBN:" + isbn
                + ", Genre:" + genre
                + ", Year of Creation:" + yearOfCreation
                + ", Language:" + language
                + ", Company:" + company;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getIsbn());
        parcel.writeInt(getYearOfCreation());
        parcel.writeString(getAuthor());
        parcel.writeString(getTitle());
        parcel.writeString(getType());
        parcel.writeString(getGenre());
        parcel.writeString(getLanguage());
        parcel.writeString(getCompany());
    }

    public static final Parcelable.Creator<MaterialsDefinitionsClass> CREATOR = new Parcelable.Creator<MaterialsDefinitionsClass>() {
        public MaterialsDefinitionsClass createFromParcel(Parcel in) {
            return new MaterialsDefinitionsClass(in);
        }

        public MaterialsDefinitionsClass[] newArray(int size) {
            return new MaterialsDefinitionsClass[size];
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
        MaterialsDefinitionsClass other = (MaterialsDefinitionsClass) obj;
        if (isbn != other.isbn)
            return false;
        return true;
    }
}