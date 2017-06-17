package com.example.macbook.retrofit;

import android.app.Service;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.macbook.retrofit.Constants.API_BASE_URL;
import static com.example.macbook.retrofit.Constants.UNSPLASH_ID;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPictures();

    }

    public void getPictures (){


        SplashPictures client =  ServiceGenerator.createService(SplashPictures.class);

        Call<List<Picture>> call =  client.splashPictures(UNSPLASH_ID);

        call.enqueue(new Callback<List<Picture>>() {
            @Override
            public void onResponse(Call<List<Picture>> call, Response<List<Picture>> response) {
Log.d("Response",response.body().get(0).getImageId().toString());

            }

            @Override
            public void onFailure(Call<List<Picture>> call, Throwable t) {

            }
        });

    }
}