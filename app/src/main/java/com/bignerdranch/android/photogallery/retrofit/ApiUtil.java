package com.bignerdranch.android.photogallery.retrofit;

public class ApiUtil {

    public static final String BASE_URL = "https://api.flickr.com/services/rest/";

    public static ApiService getApiService(){
        return ApiManager.getClient(BASE_URL).create(ApiService.class);
    }

}
