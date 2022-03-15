package com.example.authenticationservice.retrofiet;

import com.example.authenticationservice.credential.KeycloakAccessToken;
import com.example.authenticationservice.user.KeycloakUser;
import com.example.authenticationservice.util.KeycloakConstant;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RetrofietUserServiceTest {

    private String token = "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJ2dVh5ZmNCUmg1WGVYdUVCeWFJc0F4Nm83bGNCdUJxd2U4YS1rclpPQURnIn0.eyJqdGkiOiJmM2Y3MTA4ZS1hYmM1LTQ0ZTAtOWM0NS1kZDRkNTQ1ODYyYWQiLCJleHAiOjE2NDY3MzQ5MDgsIm5iZiI6MCwiaWF0IjoxNjQ2NzMxMzA4LCJpc3MiOiJodHRwOi8vbG9jYWxob3N0OjgxODAvYXV0aC9yZWFsbXMvbWFzdGVyIiwiYXVkIjpbIm1hc3Rlci1yZWFsbSIsInNwcmluZ2Jvb3QtcXVpY2tzdGFydC1yZWFsbSIsImFjY291bnQiXSwic3ViIjoiZmJkN2UxYmEtMDQwMy00ZTY2LTk2NTEtODk2OWYyMzQ0ZGZkIiwidHlwIjoiQmVhcmVyIiwiYXpwIjoic2VydmljZTAxIiwiYXV0aF90aW1lIjowLCJzZXNzaW9uX3N0YXRlIjoiZTgxODVlZjEtYmMzNy00MWI2LWExZjEtOGZkZDMwMDhjNGRkIiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJjcmVhdGUtcmVhbG0iLCJvZmZsaW5lX2FjY2VzcyIsImFkbWluIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJtYXN0ZXItcmVhbG0iOnsicm9sZXMiOlsidmlldy1pZGVudGl0eS1wcm92aWRlcnMiLCJ2aWV3LXJlYWxtIiwibWFuYWdlLWlkZW50aXR5LXByb3ZpZGVycyIsImltcGVyc29uYXRpb24iLCJjcmVhdGUtY2xpZW50IiwibWFuYWdlLXVzZXJzIiwicXVlcnktcmVhbG1zIiwidmlldy1hdXRob3JpemF0aW9uIiwicXVlcnktY2xpZW50cyIsInF1ZXJ5LXVzZXJzIiwibWFuYWdlLWV2ZW50cyIsIm1hbmFnZS1yZWFsbSIsInZpZXctZXZlbnRzIiwidmlldy11c2VycyIsInZpZXctY2xpZW50cyIsIm1hbmFnZS1hdXRob3JpemF0aW9uIiwibWFuYWdlLWNsaWVudHMiLCJxdWVyeS1ncm91cHMiXX0sInNwcmluZ2Jvb3QtcXVpY2tzdGFydC1yZWFsbSI6eyJyb2xlcyI6WyJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsInZpZXctcmVhbG0iLCJtYW5hZ2UtaWRlbnRpdHktcHJvdmlkZXJzIiwiaW1wZXJzb25hdGlvbiIsImNyZWF0ZS1jbGllbnQiLCJtYW5hZ2UtdXNlcnMiLCJxdWVyeS1yZWFsbXMiLCJ2aWV3LWF1dGhvcml6YXRpb24iLCJxdWVyeS1jbGllbnRzIiwicXVlcnktdXNlcnMiLCJtYW5hZ2UtZXZlbnRzIiwibWFuYWdlLXJlYWxtIiwidmlldy1ldmVudHMiLCJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwibWFuYWdlLWF1dGhvcml6YXRpb24iLCJtYW5hZ2UtY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyJdfSwiYWNjb3VudCI6eyJyb2xlcyI6WyJtYW5hZ2UtYWNjb3VudCIsIm1hbmFnZS1hY2NvdW50LWxpbmtzIiwidmlldy1wcm9maWxlIl19fSwic2NvcGUiOiJwcm9maWxlIGVtYWlsIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJhZG1pbiJ9.SA5E1FOY1Jv-9JMPgKy-J9xfsk2z-eYUCI3SinaONG7DsEQMKk7AaXX99Ji3oLkQLzQKQPa33kxQFAAzF7ktL8HhvkKraXOBDzkwjr_wumsvrwn0o8sV6XPNfCCo-z_kPq7AHiQGHB3K1bF_k_4z0FIxSUM7cPMqZgsQ1HuoP6DlYz2fDmPikUDvaB1Ij6-QYP9qVcL0n6EAbRMpltYZ4zoAPCv8hcqcmcDBscSlfs2-nJsmpLLc2a5xPZ4YF-GO_Z2kSATR1NjkW9CtF1v_7ppbYRRWmee-ZC_FtzroEEBtKKb9bm57iL3J0uXeDuDCGBydoRvIU3292YN1Lmq-Cw";
    private String currentId = "ad62911b-61e0-42d6-aea6-7b0a2c0d2a88";
    @Test
    void login() {
        Map<String,String> params = new HashMap<>();
        params.put("client_id", KeycloakConstant.KEYCLOAK_CLIENT_ID);
        params.put("username", KeycloakConstant.KEYCLOAK_ADMIN_USERNAME);
        params.put("password", KeycloakConstant.KEYCLOAK_ADMIN_PASSWORD);
        params.put("grant_type", KeycloakConstant.KEYCLOAK_CREDENTIAL_GRANT_TYPE);
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class);
        try {
            KeycloakAccessToken accessToken = service.login(params).execute().body();
            System.out.println(accessToken.getAccess_token());
            System.out.println(accessToken.getExpires_in());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void save() throws IOException {
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, token);
         Response<Void> response
                 = service.save(KeycloakUser.builder().username("xuanhung01").enabled(true).build()).execute();
         if(response.isSuccessful()){
             System.out.println(response.body());
             System.out.println(response.toString());
         }else{
             System.out.println(response.errorBody().string());;
         }
    }

    @Test
    void findAll() throws IOException {
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, token);
        Response<List<KeycloakUser>> response
                = service.findAll().execute();
        if(response.isSuccessful()){
            List<KeycloakUser> list = response.body();
            System.out.println(list.size());
            for (KeycloakUser user : list){
                System.out.println(user.toString());
            }
        }else{
            System.out.println("Error");
            System.out.println(response.errorBody().string());;
        }
    }

    @Test
    void findById() throws IOException {
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, token);
        Response<KeycloakUser> response
                = service.findById(currentId).execute();
        if(response.isSuccessful()){
            KeycloakUser obj = response.body();
            System.out.println(obj.toString());
        }else{
            System.out.println(response.errorBody().string());;
        }
    }

    @Test
    void update() throws IOException {
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, token);
        HashMap<String, Object> atts = new HashMap<>();
        atts.put("hello", "update here 01");
        Response<Void> response
                = service.update(currentId, KeycloakUser.builder().attributes(atts).enabled(true).build()).execute();
        if(response.isSuccessful()){
            System.out.println("Okie");
        }else{
            System.out.println(response.errorBody().string());;
        }
    }

    @Test
    void delete() throws IOException {
        RetrofietUserService service
                = RetrofietServiceGenerator.createService(RetrofietUserService.class, token);
        Response<Void> response
                = service.delete(currentId).execute();
        if(response.isSuccessful()){
            System.out.println("Okie");
        }else{
            System.out.println(response.errorBody().string());;
        }
    }
}