package il.co.expertize.utils;

import com.google.gson.Gson;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import il.co.expertize.models.Travel;

public class SharedPrefUtils {

   private Context _context;
   private SharedPreferences _pref;

    public SharedPrefUtils(Context _context) {
        this._context = _context;
        _pref = PreferenceManager.getDefaultSharedPreferences(this._context);
    }

    public void saveTravel(Travel travel) {
        _pref.edit()
             .putString("travel", new Gson().toJson(travel))
                .apply();
    }

    public Travel loadTravel() {
        Travel travel = null;
        String json = _pref.getString("travel", null);
        if (json!=null) {
            travel =  new Gson().fromJson(json, Travel.class);
        }
        return  travel;
    }

}
