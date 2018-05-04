package com.bignerdranch.android.photogallery.retrofit;

import com.bignerdranch.android.photogallery.retrofit.models.JSONResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("?method=flickr.photos.getRecent&api_key=f2bb0488070a4d5e08c224bdfd0d03cb&format=json&nojsoncallback=1")
    Call<JSONResponse> getPhotos();
}
