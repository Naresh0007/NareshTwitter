package com.naresh.nareshtwitter.ui.home;

import android.graphics.Camera;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.naresh.nareshtwitter.CameraActivity;
import com.naresh.nareshtwitter.R;
import com.naresh.nareshtwitter.SignInActivity;
import com.naresh.nareshtwitter.adapter.AdapterTweet;
import com.naresh.nareshtwitter.api.ApiClass;
import com.naresh.nareshtwitter.model.TweetM;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    CameraActivity cma = new CameraActivity();
    SignInActivity Signin = new SignInActivity();
    RecyclerView recyclerView;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.rvHome);

        CurrentUser();
        return root;
    }

    private void CurrentUser() {
        String token;
        if (Signin.Token.isEmpty()) {
            token = cma.token;
        } else {
            token = Signin.Token;

        }
        ApiClass usersAPI = new ApiClass();

        Call<List<TweetM>> userCall = usersAPI.calls().GetTweet(token);
        userCall.enqueue(new Callback<List<TweetM>>() {
            @Override
            public void onResponse(Call<List<TweetM>> call, Response<List<TweetM>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<TweetM> tweetMS = response.body();


                AdapterTweet tweetAdapter = new AdapterTweet(getContext(), tweetMS);
                recyclerView.setAdapter(tweetAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onFailure(Call<List<TweetM>> call, Throwable t) {
                Toast.makeText(getContext(), "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}