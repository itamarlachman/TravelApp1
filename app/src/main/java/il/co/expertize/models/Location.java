package il.co.expertize.models;

import java.io.Serializable;

public class Location implements Serializable {
    protected String address;
    protected double lng;
    protected double lat;

    public Location() {
        lng=0;
        lat=0;
        address="";
    }

    public Location(String address, float lng, float lat) {
        this.address = address;
        this.lng = lng;
        this.lat = lat;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
