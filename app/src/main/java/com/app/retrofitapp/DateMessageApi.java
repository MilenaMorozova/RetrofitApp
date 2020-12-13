package com.app.retrofitapp;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DateMessageApi {
    @GET("?service=date")
    Call<DateMessage> dateMessage();
}
