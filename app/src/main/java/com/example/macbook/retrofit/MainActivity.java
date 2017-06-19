package com.example.macbook.retrofit;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.macbook.retrofit.Constants.API_BASE_URL;
import static com.example.macbook.retrofit.Constants.FITBARK_CLIENT_ID;
import static com.example.macbook.retrofit.Constants.FITBARK_REDIRECT;
import static com.example.macbook.retrofit.Constants.UNSPLASH_ID;

public class MainActivity extends AppCompatActivity {

    public Button main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getPictures();
//        getAuthCode();
        main = (Button) findViewById(R.id.button);
        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
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
