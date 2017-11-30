package com.androidsalad.bonfire.Model;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Photo {

    private String photoId;
    private String photoImagePath;
    private String photoThumbPath;
    private Object photoDate;
    private String photoCaption;

    public Photo() {
    }

    public Photo(String photoId, String photoImagePath, String photoThumbPath, Object photoDate, String photoCaption) {
        this.photoId = photoId;
        this.photoImagePath = photoImagePath;
        this.photoThumbPath = photoThumbPath;
        this.photoDate = photoDate;
        this.photoCaption = photoCaption;
    }

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getPhotoImagePath() {
        return photoImagePath;
    }

    public void setPhotoImagePath(String photoImagePath) {
        this.photoImagePath = photoImagePath;
    }

    public String getPhotoThumbPath() {
        return photoThumbPath;
    }

    public void setPhotoThumbPath(String photoThumbPath) {
        this.photoThumbPath = photoThumbPath;
    }

    public Object getPhotoDate() {
        return photoDate;
    }

    public void setPhotoDate(Object photoDate) {
        this.photoDate = photoDate;
    }

    public String getPhotoCaption() {
        return photoCaption;
    }

    public void setPhotoCaption(String photoCaption) {
        this.photoCaption = photoCaption;
    }
}
