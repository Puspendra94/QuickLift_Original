package com.example.pandey.quicklift_master.model;

public class Destination {

    final String DRAWABLE = "drawable/";
    public String getImageURL(){
        return DRAWABLE + locationImageUrl;

    }
    public float getLongitude() {
        return longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    private float longitude;

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    private float latitude;

    public String getLocationTitle() {
        return locationTitle;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public String getLocationAddress() {
        return locationAddress;
    }

    public void setLocationAddress(String locationAddress) {
        this.locationAddress = locationAddress;
    }

    public String getLocationImageUrl() {
        return locationImageUrl;
    }

    public void setLocationImageUrl(String locationImageUrl) {
        this.locationImageUrl = locationImageUrl;
    }

    private String locationTitle;
    private String locationAddress;
    private String locationImageUrl;

    public Destination(float latitude,float longitude,String locationTitle,String locationAddress,String locationImageUrl){
        this.longitude = longitude;
        this.latitude = latitude;
        this.locationTitle = locationTitle;
        this.locationAddress = locationAddress;
        this.locationImageUrl = locationImageUrl;
    }

}
