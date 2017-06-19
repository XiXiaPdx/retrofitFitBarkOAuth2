package com.example.macbook.retrofit;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.macbook.retrofit.Constants.FITBARK_CLIENT_ID;
import static com.example.macbook.retrofit.Constants.FITBARK_REDIRECT;
import static com.example.macbook.retrofit.Constants.FITBARK_SECRET;

public class LoginActivity extends AppCompatActivity {
    public Button loginButton;
    public static final String intentRedirect = "myapp://app.open";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAuthCode();
            }
        });
//        Log.d("CREATING", "CREATE CREATE");
////        Uri uri = getIntent().getData();
////        if (uri != null ){
////            Log.d("URI NOT NULL IN CREATE", uri.toString());
////        }
    }

    public void getAuthCode(){
        Intent intent = new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(ServiceGenerator.FITBARK_BASE_URL + "/authorize?response_type=code&client_credentials&" + "client_id=" +FITBARK_CLIENT_ID + "&redirect_uri=" + intentRedirect));
        startActivity(intent);

    }


    @Override
    protected void onResume() {

        super.onResume();
        // the intent filter defined in AndroidManifest will handle the return from ACTION_VIEW intent
        Uri uri = getIntent().getData();
        if (uri != null && uri.toString().startsWith(intentRedirect)  ) {
            // use the parameter your API exposes for the code (mostly it's "code")
            String code = uri.getQueryParameter("code");
            if (code != null) {
                // get access token
                FitBarkAPI loginService = ServiceGenerator.createService(FitBarkAPI.class, FITBARK_CLIENT_ID, FITBARK_SECRET);
                Call<AccessToken> call = loginService.getAccessToken(code, "authorization_code", FITBARK_CLIENT_ID, FITBARK_SECRET, intentRedirect);
                call.enqueue(new Callback<AccessToken>() {
                    @Override
                    public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                        if(response.code() == 200){
                            Log.d("response", response.body().getAccessToken().toString());
//                            AccessToken accessToken = response.body();
//                            Log.d("accessToken", accessToken.getAccessToken());
                        }
                    }

                    @Override
                    public void onFailure(Call<AccessToken> call, Throwable t) {

                    }
                });


            } else if (uri.getQueryParameter("error") != null) {
                Log.d("ERROR URI", "ERROR ERROR ERROR");
            }
        }
    }
}
