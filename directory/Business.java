package org.jackie.corvallisrecycler_final_project.directory;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Jackie on 5/24/2015.
 */
public class Business implements Parcelable{

    private int mId;
    private String mName;
    private String mStreetAddress;
    private String mCity;
    private String mState;
    private String mZipCode;
    private String mPhoneNumber;
    private String mHours;
    private String mWebpage;
    private Double mLatitude;
    private Double mLongitude;

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public Business(){}

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getStreetAddress() {
        return mStreetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        mStreetAddress = streetAddress;
    }

    public String getCity() {
        return mCity;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getState() {
        return mState;
    }

    public void setState(String state) {
        mState = state;
    }

    public String getZipCode() {
        return mZipCode;
    }

    public void setZipCode(String zipCode) {
        mZipCode = zipCode;
    }

    public String getPhoneNumber() {
        return mPhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        mPhoneNumber = phoneNumber;
    }

    public String getHours() {
        return mHours;
    }

    public void setHours(String hours) {
        mHours = hours;
    }

    public String getWebpage() {
        return mWebpage;
    }

    public void setWebpage(String webpage) {
        mWebpage = webpage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mName);
        dest.writeString(mStreetAddress);
        dest.writeString(mCity);
        dest.writeString(mState);
        dest.writeString(mZipCode);
        dest.writeString(mPhoneNumber);
        dest.writeString(mHours);
        dest.writeString(mWebpage);
        if(mLatitude != null && mLongitude != null){
            dest.writeDouble(mLatitude);
            dest.writeDouble(mLongitude);
        }

    }

    private Business(Parcel in){
        mId = in.readInt();
        mName = in.readString();
        mStreetAddress = in.readString();
        mCity = in.readString();
        mState = in.readString();
        mZipCode = in.readString();
        mPhoneNumber = in.readString();
        mHours = in.readString();
        mWebpage = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public static final Creator<Business> CREATOR = new Creator<Business>() {
        @Override
        public Business createFromParcel(Parcel source) {
            return new Business(source);
        }

        @Override
        public Business[] newArray(int size) {
            return new Business[size];
        }
    };
}
