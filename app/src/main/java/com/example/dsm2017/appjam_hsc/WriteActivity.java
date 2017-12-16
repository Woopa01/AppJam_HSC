package com.example.dsm2017.appjam_hsc;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import static com.example.dsm2017.appjam_hsc.LoginActivity.url;
import static com.example.dsm2017.appjam_hsc.R.layout.dialog_complete;

/**
 * Created by dsm2017 on 2017-12-16.
 */

public class WriteActivity extends AppCompatActivity {

    EditText petition_title, petition_explain;
    TextView send_petition;
    Retrofit retrofit;
    RetrofitService retrofitService;
    String petition = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_writepage);

        petition_title = (EditText) findViewById(R.id.petition_title_input);
        petition_explain = (EditText) findViewById(R.id.petition_explain_input);
        send_petition = (TextView) findViewById(R.id.send_petition);

        send_petition.setOnClickListener(new TextView.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(petition_title.equals("")||petition_explain.equals("")){
                    Toast.makeText(WriteActivity.this, "글을 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    PostPetitionData(petition_title.getText().toString(), petition_explain.getText().toString());
                }
            }

        });



    }

    public void PostPetitionData(String title, String explain) {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Map map = new HashMap();
        map.put("petitionTitle",title);
        map.put("petitionContent",explain);
        map.put("petitionerCount",petition);

        retrofitService = retrofit.create(RetrofitService.class);
        Call<ResponseBody> call = retrofitService.PostPetition(map);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.code() == 200 ){
                    Log.d("RETROFIT","success");
                    final Dialog dialog = new Dialog(WriteActivity.this);
                    View complete_dialog = getLayoutInflater().inflate(R.layout.dialog_complete, null);
                    View main_link = complete_dialog.findViewById(R.id.complete_mainlink);
                    dialog.setContentView(complete_dialog);
                    dialog.show();

                    main_link.setOnClickListener(new View.OnClickListener(){

                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(WriteActivity.this,MainActivity.class);
                            startActivity(intent);
                            dialog.cancel();
                        }

                    });

                    Window window = dialog.getWindow();
                    window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                } else{
                    Log.d("RETROFIT","failed") ;
                    Toast.makeText(WriteActivity.this, "잠시 후 다시 시도해주세요..", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("RETROFIT","try_failed");
                Toast.makeText(WriteActivity.this, "인터넷 연결을 확인해주세요.", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
