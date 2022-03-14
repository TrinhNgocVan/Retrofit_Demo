package com.example.demo;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class Main {
    public static void main(String[] args){
        // User service manual creation
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())  // parsing data when we send request and get response like jackson
                .client(httpClient.build())
                .build();
        // cretate user service
        UserService userservice = retrofit.create(UserService.class);
        Call<User> callSync = userservice.getUser("eugenp");
        Call<User> callAsync = userservice.getUser("eugenp");
//        try {
//            Response<User> response = callSync.execute();
//            User user = response.body();
//            System.out.println(user.toString());
//        }catch (IOException e){
//
//        }

        // Execute the call asynchronously. Get a positive or negative callback.
        callAsync.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                System.out.println("User value for call asynchronously : "+user.toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                System.out.println(throwable);
            }
        });
    }
}
