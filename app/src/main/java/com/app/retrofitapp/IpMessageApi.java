package com.app.retrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IpMessageApi {
    @GET("?service=ip")
    Call<IpMessage> ipMessage();
}
