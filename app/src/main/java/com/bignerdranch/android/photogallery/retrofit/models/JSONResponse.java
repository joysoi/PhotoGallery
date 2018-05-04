package com.bignerdranch.android.photogallery.retrofit.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JSONResponse {

    @SerializedName("photos")
    @Expose
    private Photos mPhotos;

    public JSONResponse(Photos photos) {
        this.mPhotos = photos;
    }

    public Photos getPhotos() {
        return mPhotos;
    }
}
