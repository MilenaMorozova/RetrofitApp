package com.app.retrofitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Button IpButton, DateButton;
    private final String BASE_URL = "http://date.jsontest.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.DataTextView);
        IpButton = findViewById(R.id.IpButton);
        DateButton = findViewById(R.id.DateButton);

        IpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendIpRequest();
            }
        });
        DateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDateRequest();
            }
        });
    }

    private void sendIpRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpMessageApi ipMessagesApi = retrofit.create(IpMessageApi.class);
        Call<IpMessage> ipMessage = ipMessagesApi.ipMessage();

        ipMessage.enqueue(new Callback<IpMessage>() {
            @Override
            public void onResponse(Call<IpMessage> call, Response<IpMessage> response) {
                if (response.isSuccessful()) {
                    Log.d("RESPONSE_SUCCESS", "response " + response.body());
                    textView.setText("IP: " + response.body().getIp());
                } else {
                    Log.d("RESPONSE_FAIL", "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<IpMessage> call, Throwable t) {
                Log.d("RESPONSE_FAIL", "failure " + t);
            }
        });
    }

    private void sendDateRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        DateMessageApi dateMessageApi = retrofit.create(DateMessageApi.class);
        Call<DateMessage> dateMessage = dateMessageApi.dateMessage();

        dateMessage.enqueue(new Callback<DateMessage>() {
            @Override
            public void onResponse(Call<DateMessage> call, Response<DateMessage> response) {
                if (response.isSuccessful()) {
                    Log.d("RESPONSE_SUCCESS", "response " + response.body());
                    textView.setText("Date: " + response.body().getDate());
                } else {
                    Log.d("RESPONSE_FAIL", "response code " + response.code());
                }
            }

            @Override
            public void onFailure(Call<DateMessage> call, Throwable t) {
                Log.d("RESPONSE_FAIL", "failure " + t);
            }
        });
    }
}