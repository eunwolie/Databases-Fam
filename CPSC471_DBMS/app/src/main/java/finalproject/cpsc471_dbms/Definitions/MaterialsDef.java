package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by evech on 2017-03-24.
 */

public class MaterialsDef implements Parcelable{
    private List<AuthorDef> author = null;
    private String title = null;
    private String type = null;
    private int isbn = -1;;
    private String genre = null;
    private int yearOfCreation = -1;;
    private List<String> language = null;
    private String company = null;
    private String description = null;
    private int shelf = -1;
    private byte[] image = null;

    public MaterialsDef() {
        super();
    }

    public MaterialsDef(String title) {
        super();
        this.title = title;
    }

    public MaterialsDef(String description, List<AuthorDef> author, String title, String type, int isbn,
                        String genre, int yearOfCreation, List<String> language, String company,
                        int shelf, byte[] image){
        super();
        this.description = description;
        this.author = author;
        this.title = title;
        this.type = type;
        this.isbn = isbn;
        this.genre = genre;
        this.yearOfCreation = yearOfCreation;
        this.language = language;
        this.company = company;
        this.shelf = shelf;
        this.image = image;
    }

    private MaterialsDef(Parcel in) {
        super();
        this.description = in.readString();
        this.title = in.readString();
        this.type = in.readString();
        this.isbn = in.readInt();
        this.genre = in.readString();
        this.yearOfCreation = in.readInt();
        this.company = in.readString();
        this.shelf = in.readInt();
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public List<AuthorDef> getAuthor() {
        return author;
    }

    public void setAuthor(List<AuthorDef> author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
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

    public int getYearOfCreation() {
        return yearOfCreation;
    }

    public void setYearOfCreation(int yearOfCreation) {
        this.yearOfCreation = yearOfCreation;
    }

    public List<String> getLanguage() {
        return language;
    }

    public void setLanguage(List<String> language) {
        this.language = language;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) { this.shelf = shelf; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getIsbn());
        parcel.writeInt(getYearOfCreation());
        parcel.writeString(getTitle());
        parcel.writeString(getType());
        parcel.writeString(getGenre());
        parcel.writeString(getCompany());
        parcel.writeInt(getShelf());
    }

    public static final Parcelable.Creator<MaterialsDef> CREATOR = new Parcelable.Creator<MaterialsDef>() {
        public MaterialsDef createFromParcel(Parcel in) {
            return new MaterialsDef(in);
        }

        public MaterialsDef[] newArray(int size) {
            return new MaterialsDef[size];
        }
    };

    @Override
    public String toString() {
        return "Author:" + author
                + ", Title:" + title
                + ", Type:" + type
                + ", ISBN:" + isbn
                + ", Genre:" + genre
                + ", Year of Creation:" + yearOfCreation
                + ", Company:" + company;
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

        return (isbn == ((MaterialsDef) obj).isbn);
    }
}