package com.example.macbook.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.macbook.retrofit.Constants.FITBARK_CLIENT_ID;
import static com.example.macbook.retrofit.Constants.UNSPLASH_CLIENT_ID;

/**
 * Created by macbook on 6/17/17.
 */

public interface FitBarkAPI {
    @GET("authorize?response_type=code&client_credentials&")
    Call<List<User>> getFitBarkAuthCode(
            @Query("client_id") String fitBarkID,
            @Query("redirect_uri") String fitBarkRedirect
    );
}
