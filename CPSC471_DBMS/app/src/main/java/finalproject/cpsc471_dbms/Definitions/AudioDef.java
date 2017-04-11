package finalproject.cpsc471_dbms.Definitions;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by evech on 2017-03-27.
 */

public class AudioDef extends TypeDef{

    public AudioDef() {
        super();
    }

    public AudioDef(int length, int isbn) {
        super(length, isbn);
        this.length = length;
        this.isbn = isbn;
    }

    private AudioDef(Parcel in) {
        this.length = in.readInt();
        this.isbn = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(getLength());
        parcel.writeInt(getIsbn());
    }

    public static final Parcelable.Creator<AudioDef> CREATOR = new Parcelable.Creator<AudioDef>() {
        public AudioDef createFromParcel(Parcel in) { return new AudioDef(in); }

        public AudioDef[] newArray(int size) {return new AudioDef[size]; }
    };
}
