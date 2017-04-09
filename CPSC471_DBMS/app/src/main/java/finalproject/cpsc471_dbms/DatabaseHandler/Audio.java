package finalproject.cpsc471_dbms.DatabaseHandler;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import finalproject.cpsc471_dbms.Constants.*;
import finalproject.cpsc471_dbms.Definitions.*;

/**
 * Created by evech on 2017-03-24.
 */

public class Audio {

    protected SQLiteDatabase db;
    private static final String WHERE_KEY_EQUALS = AudioTable.ISBN + "=?";

    public Audio(Context context)
    {
        db = new _DatabaseHelper(context).getWritableDatabase();
    }

    /**
     * @param x the audio class containing all the information of a new audio material
     */
    public void addAudio(AudioDef x) {
        ContentValues values = new ContentValues();
        values.put(AudioTable.ISBN, x.getIsbn());
        values.put(AudioTable.LENGTH, x.getLength());
        db.insert(AudioTable.TABLE_NAME, null, values);
    }

    /**
     * @param x the audio class containing all the information of a new audio material
     */
    public int updateAudio (AudioDef x) {
        ContentValues values = new ContentValues();
        values.put(AudioTable.ISBN, x.getIsbn());
        values.put(AudioTable.LENGTH, x.getLength());
        // update row
        int result = db.update(AudioTable.TABLE_NAME, values,
                WHERE_KEY_EQUALS,
                new String[] { x.getIsbn()+"" });
        Log.d("Update Result:", "=" + result);
        return result;
    }

    /**
     * @param isbn the key of the audio class containing all the information of a new audio material
     */
    public int deleteAudio(int isbn) {
        db.delete(AudioTable.TABLE_NAME, WHERE_KEY_EQUALS,
                new String[]{isbn+""});
    }

    /**
     * @param audio the audio class containing all the information of a new audio material
     */
    public int deleteAudio(AudioDef x) {
        return db.delete(AudioTable.TABLE_NAME,
                WHERE_KEY_EQUALS, new String[] {x.getIsbn() + "" });
    }

    /* Unused methods

    public Audio(Context context) {
        this.aContext = context;
        dataHelper = _DatabaseHelper.getHelper(aContext);
        open();

        public void open() throws SQLException {
        if(dataHelper == null)
            dataHelper = _DatabaseHelper.getHelper(aContext);
        database = dataHelper.getWritableDatabase();
    }
    */
}