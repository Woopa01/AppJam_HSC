package com.example.dsm2017.appjam_hsc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText signin_id, signin_password;
    Button signin_button,signup_button;
    Retrofit retrofit;
    RetrofitService retrofitService;
    static String url = "http://60.0.15.124:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signin_id = (EditText) findViewById(R.id.EditText_id);
        signin_password = (EditText) findViewById(R.id.EditText_password);
        signin_button = (Button) findViewById(R.id.signinButton);
        signup_button = (Button) findViewById(R.id.signupButton);

        signin_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
            PostSigninData(signin_id.getText().toString(),signin_password.getText().toString());
                Toast.makeText(MainActivity.this, "test", Toast.LENGTH_SHORT).show();
            }

        });

        signup_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {

            }

        });


    }

    public void PostSigninData(String id, String passward) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map map = new HashMap();
        map.put("username",id);
        map.put("password",passward);

        retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> call = retrofitService.PostLoginData(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 ){
                    Log.d("RETROFIT","succssed");
                } else{
                    Log.d("RETROFIT","failed") ;
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("RETROFIT","try_failed");
            }
        });

    }

}
