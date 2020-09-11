package com.example.googledeveloper;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private String userName;
    private String userCountry;
    private String userHours;
    private int userImage;
    private String userDetail;

    public User(String userName, String userCountry, String userHours, int userImage, String userDetail) {
        this.userName = userName;
        this.userCountry = userCountry;
        this.userHours = userHours;
        this.userImage = userImage;
        this.userDetail = userDetail;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserCountry() {
        return userCountry;
    }

    public String getUserHours() {
        return userHours;
    }

    public int getUserImage() {
        return userImage;
    }

    public String getUserDetail() {
        return userDetail;
    }

    public static Creator<User> getCREATOR() {
        return CREATOR;
    }

    public User(){

    }


    protected User(Parcel in) {
        userName = in.readString();
        userCountry = in.readString();
        userHours = in.readString();
        userImage = in.readInt();
        userDetail = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userName);
        parcel.writeString(userCountry);
        parcel.writeString(userHours);
        parcel.writeInt(userImage);
        parcel.writeString(userDetail);
    }
}
