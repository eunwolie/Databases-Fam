package finalproject.cpsc471_dbms.UI.custom;

import android.net.Uri;

/**
 * Created by wj-hong on 10/04/17.
 */

public class DashItem {

    private Uri eventImage;
    private String date;
    private String eventName;
    private String sponsor;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSponsor() {
        return sponsor;
    }

    public void setSponsor(String sponsor) {
        this.sponsor = sponsor;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Uri getEventImage() {
        return eventImage;
    }

    public void setEventImage(Uri eventImage) {
        this.eventImage = eventImage;
    }
}
