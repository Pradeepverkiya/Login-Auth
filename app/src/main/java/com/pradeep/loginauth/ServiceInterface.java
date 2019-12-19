package com.pradeep.loginauth;


import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ServiceInterface {


    @POST("login")
    Call<JsonObject> postData(
            @Header("Content-Type") String contentType,
            @Header("Accept") String accept,
            @Body JsonObject body);

}

