package il.co.expertize.utils;

import il.co.expertize.models.Location;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;

import java.util.List;
import java.util.Locale;

public class LocationUtils {
    private static final int PERMISSION_REQUEST_CODE = 1;
    protected Context _context;
    protected LocationManager _manager;
    Geocoder geocoder;

    public LocationUtils(Context _context) {
        this._context = _context;
        this._manager = (LocationManager) _context.getSystemService(Context.LOCATION_SERVICE);

    }

    public Location getLocation(String address) {
        if (ActivityCompat.checkSelfPermission(_context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(_context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) _context, new String[] {Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_CODE);
        }
        Location location = new Location();
        location.setAddress(address);
        try {
            geocoder = new Geocoder(_context, Locale.getDefault());
            List<Address> results =  geocoder.getFromLocationName(address,1);
            if (results !=null) {
                location.setLng(results.get(0).getLongitude());
                location.setLat(results.get(0).getLatitude());
            }
        } catch(Exception e) {
            Log.d(Globals.TAG,"Error to find the long/lat for address: " + address);
        }
        return  location;
    }
}