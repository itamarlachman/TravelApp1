package il.co.expertize.utils;

import il.co.expertize.models.Location;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;

import androidx.core.app.ActivityCompat;

public class LocationUtils {
    private static final int PERMISSION_REQUEST_CODE = 1;
    protected Context _context;
    protected LocationManager _manager;

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
        android.location.Location loc = _manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (loc !=null) {
            location.setLng(loc.getLongitude());
            location.setLat(loc.getLatitude());
        }

        return  location;
    }
}