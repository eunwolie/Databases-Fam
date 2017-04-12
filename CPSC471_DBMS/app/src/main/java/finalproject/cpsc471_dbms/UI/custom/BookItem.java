package finalproject.cpsc471_dbms.UI.custom;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import finalproject.cpsc471_dbms.R;

/**
 * Created by wj-hong on 11/04/17.
 */

public class BookItem {

    private Uri bookImage;
    private String bookName;
    private String bookAuthor;
    private String bookGenre;

    public BookItem(Uri bookImage, String bookName, String bookAuthor, String bookGenre) {
        this.bookImage = bookImage;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
    }

    public Uri getBookImage() {
        return bookImage;
    }

    public void setBookImage(Uri bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }
}
