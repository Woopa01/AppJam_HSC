package com.example.dsm2017.appjam_hsc;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by dsm2017 on 2017-12-16.
 */

public interface RetrofitService {

    @FormUrlEncoded
    @POST("/login")
    Call<ResponseBody> PostLoginData(@FieldMap Map<String,String> map);

    @FormUrlEncoded
    @POST("/addUser")
    Call<ResponseBody> PostSignUpData(@FieldMap Map<String,String> map);

}
