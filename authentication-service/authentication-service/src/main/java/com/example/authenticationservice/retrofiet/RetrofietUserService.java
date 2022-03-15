package com.example.authenticationservice.retrofiet;

import com.example.authenticationservice.credential.KeycloakAccessToken;
import com.example.authenticationservice.user.KeycloakUser;
import com.example.authenticationservice.util.KeycloakConstant;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;
import java.util.Map;

public interface RetrofietUserService {

    @FormUrlEncoded
    @POST("/auth/realms/master/protocol/openid-connect/token")
    Call<KeycloakAccessToken> login(@FieldMap Map<String,String> params);

    @POST("/auth/admin/realms/master/users")
    Call<Void> save(@Body KeycloakUser keycloakUser);

    @GET("/auth/admin/realms/master/users")
    Call<List<KeycloakUser>> findAll();

    @GET("/auth/admin/realms/master/users/{id}")
    Call<KeycloakUser> findById(@Path("id") String id);

    @PUT("/auth/admin/realms/master/users/{id}")
    Call<Void> update(@Path("id") String id, @Body KeycloakUser updateKeycloakUser);

    @DELETE("/auth/admin/realms/master/users/{id}")
    Call<Void> delete(@Path("id") String id);
}
