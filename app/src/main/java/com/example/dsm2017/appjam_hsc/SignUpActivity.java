package com.example.dsm2017.appjam_hsc;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dsm2017.appjam_hsc.Retrofit.RetrofitService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.dsm2017.appjam_hsc.LoginActivity.url;

/**
 * Created by dsm2017 on 2017-12-16.
 */

public class SignUpActivity extends AppCompatActivity {

    EditText signup_name, signup_studentid, signup_id, signup_password, signup_checkpassword;
    String name, studnet_id, id, password, checkpassword;
    Button signup_button;
    Retrofit retrofit;
    RetrofitService retrofitService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signup);

        signup_name = (EditText) findViewById(R.id.signup_name);
        signup_studentid = (EditText) findViewById(R.id.signup_student_id);
        signup_id = (EditText) findViewById(R.id.signup_password);
        signup_password = (EditText) findViewById(R.id.signup_password);
        signup_checkpassword = (EditText) findViewById(R.id.signup_checkpassword);
        signup_button = (Button) findViewById(R.id.signup_button);

        signup_button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v) {
            name = signup_name.getText().toString();
            studnet_id = signup_studentid.getText().toString();
            id = signup_id.getText().toString();
            password = signup_password.getText().toString();
            checkpassword = signup_checkpassword.getText().toString();

            if(password.equals(checkpassword)) {
                PostSignUpRequest(name, studnet_id, id, password);
            } else {
                Toast.makeText(SignUpActivity.this, "비밀번호가 일치 하지 않습니다.", Toast.LENGTH_SHORT).show();
            }

            }

        });

    }

    public void PostSignUpRequest(String name, String studnet_id, String id, String password) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map map = new HashMap();
        map.put("name",name);
        map.put("number",studnet_id);
        map.put("username",id);
        map.put("password",password);

        retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> call = retrofitService.PostSignUpData(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 ){
                    Log.d("RETROFIT","success");
                    Intent intent = new Intent(SignUpActivity.this,LoginActivity.class);
                    startActivity(intent);
                    Toast.makeText(SignUpActivity.this, "성공적으로 회원가입 되었습니다.", Toast.LENGTH_SHORT).show();
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
