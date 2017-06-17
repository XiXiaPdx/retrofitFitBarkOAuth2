package com.example.macbook.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.macbook.retrofit.Constants.UNSPLASH_CLIENT_ID;

/**
 * Created by macbook on 6/16/17.
 */

public interface SplashPictures {
    @GET("/photos/random/?query=food&count=30")
    Call<List<Picture>> splashPictures(
            @Query(UNSPLASH_CLIENT_ID) String unSplashID

    );
}
