package com.naresh.nareshtwitter.api;

import com.naresh.nareshtwitter.model.SignupRes;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class LoginBill {
    boolean isSuccess = false;
    public static   String Token;
    public boolean checkUser(String username, String password) {

        ApiClass usersAPI = new ApiClass();
        // Url.getInstance().create(UsersAPI.class);
        Call<SignupRes> usersCall = usersAPI.calls().checkUser(username, password);

        try {
            Response<SignupRes> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {
                SignupRes signUpResponse=loginResponse.body();
                Token=signUpResponse.getToken();


                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
