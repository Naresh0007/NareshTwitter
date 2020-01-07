package com.naresh.nareshtwitter.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
        public  Api calls(){
            String base_url = "http://10.0.2.2:3000/";
            //  String base_url = "http://172.100.100.5:3000/";

            Retrofit retrofit = new Retrofit.Builder().baseUrl(base_url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api userAPI=retrofit.create(Api.class);
            return userAPI;
        }
}
