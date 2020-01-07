package com.naresh.nareshtwitter.api;

import com.naresh.nareshtwitter.model.Check;
import com.naresh.nareshtwitter.model.Image;
import com.naresh.nareshtwitter.model.SignupRes;
import com.naresh.nareshtwitter.model.TweetM;
import com.naresh.nareshtwitter.model.User;
import com.naresh.nareshtwitter.model.UserInfo;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {
    @FormUrlEncoded
    @POST("users/login")
    Call<SignupRes> checkUser(@Field("email") String username, @Field("password") String password);


    @POST("users/signup")
    Call<SignupRes> register(@Body User cud);

    @Multipart
    @POST("upload")
    Call<Image> uploadImage(@Part MultipartBody.Part imageFile);

    @POST("users/check")
    Call<Check> check(@Body User email);

    @POST("users/showalltweet")
    Call<List<TweetM>> GetTweet(@Header("Authorization") String token);

    @POST("users/me")
    Call<UserInfo> getUser(@Header("Authorization") String token);

}

