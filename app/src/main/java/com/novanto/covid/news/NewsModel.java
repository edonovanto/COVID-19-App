package com.novanto.covid.news;

import android.os.Parcel;
import android.os.Parcelable;

public class NewsModel implements Parcelable {
    private String title;
    private String description;
    private String photo;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.photo);
    }

    public NewsModel(){

    }

    private NewsModel(Parcel in){
        this.title=in.readString();
        this.description=in.readString();
        this.photo=in.readString();
    }

    public static final Parcelable.Creator<NewsModel> CREATOR = new Parcelable.Creator<NewsModel>() {
        @Override
        public NewsModel createFromParcel(Parcel source) {
            return new NewsModel(source);
        }
        @Override
        public NewsModel[] newArray(int size) {
            return new NewsModel[size];
        }
    };
}
