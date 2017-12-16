package com.example.dsm2017.appjam_hsc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    EditText signin_id, signin_password;
    Button signin_button;
    TextView signup_link;
    Retrofit retrofit;
    RetrofitService retrofitService;
    static String url = "http://60.0.15.124:3000";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        signin_id = (EditText) findViewById(R.id.EditText_id);
        signin_password = (EditText) findViewById(R.id.EditText_password);
        signin_button = (Button) findViewById(R.id.signin_link);
        signup_link = (TextView) findViewById(R.id.signup_link);

        signin_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
            PostSigninData(signin_id.getText().toString(),signin_password.getText().toString());
            }

        });

        signup_link.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);
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
                    Log.d("RETROFIT","success");
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this, "성공적으로 로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                } else{
                    Log.d("RETROFIT","failed") ;
                    Toast.makeText(LoginActivity.this, "아이디를 다시 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("RETROFIT","try_failed");
                Toast.makeText(LoginActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();            }
        });

    }

}
