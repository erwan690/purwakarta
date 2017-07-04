package purwakarta.kota.kuliner.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by aomcenter on 6/28/2017.
 */

public class SessionManager {

    public static final String KEY_ID = "idwr";
    public static final String KEY_NAMA = "nama";
    public static final String KEY_LONGITUDE = "longitude";
    public static final String KEY_LATITUDE = "latitude";
    public static final String KEY_LATI = "lokasilati";
    public static final String KEY_LONI = "lokasiloni";
    private static final String PREF_NAME = "Warung";


    // LogCat tag
    private static String TAG = SessionManager.class.getSimpleName();
    // Shared Preferences
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;


    public SessionManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setKeyId(String id,String nama) {

        // Storing name in pref
        editor.putString(KEY_ID, id);
        editor.putString(KEY_NAMA, nama);

        // commit changes
        editor.commit();
    }

    public void setMapwarung(String lt,String lg) {

        // Storing name in pref
        editor.putString(KEY_LATITUDE, lt);
        editor.putString(KEY_LONGITUDE, lg);

        // commit changes
        editor.commit();
    }
    public void setLokasi(String lt,String lg) {

        // Storing name in pref
        editor.putString(KEY_LATI, lt);
        editor.putString(KEY_LONI, lg);

        // commit changes
        editor.commit();
    }
    public void clearAll() {
        // Clearing all data from Shared Preferences
        editor.clear();
        editor.commit();

    }


    public HashMap<String,String> getKeyId() {
        HashMap<String, String> keyid = new HashMap<String, String>();
        // user name
        keyid.put(KEY_ID, pref.getString(KEY_ID, null));
        keyid.put(KEY_NAMA, pref.getString(KEY_NAMA, null));
        // return user
        return keyid;
    }
    public HashMap<String,String> getMapWarung() {
        HashMap<String, String> mapwr = new HashMap<String, String>();
        // user name
        mapwr.put(KEY_LATITUDE, pref.getString(KEY_LATITUDE, null));
        mapwr.put(KEY_LONGITUDE, pref.getString(KEY_LONGITUDE, null));
        // return user
        return mapwr;
    }
    public HashMap<String,String> getLokasi() {
        HashMap<String, String> lokasi = new HashMap<String, String>();
        // user name
        lokasi.put(KEY_LATI, pref.getString(KEY_LATI, null));
        lokasi.put(KEY_LONI, pref.getString(KEY_LONI, null));
        // return user
        return lokasi;
    }
}
