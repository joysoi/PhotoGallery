package com.bignerdranch.android.photogallery.retrofit.models;

import java.util.List;

public class Photos {

    private final List<Photo> photo;

    public Photos(List<Photo> photos) {
        this.photo = photos;
    }

    public List<Photo> getRecentPhoto() {
        return photo;
    }
}

