package com.naresh.nareshtwitter;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.naresh.nareshtwitter.api.ApiClass;
import com.naresh.nareshtwitter.model.UserInfo;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Dashboard extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    CameraActivity cma = new CameraActivity();
    SignInActivity signin = new SignInActivity();
    TextView name, email;
    ImageView imageView;
    public static final String base_url = "http://10.0.2.2:3000/";
    String imagePath = base_url + "uploads/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    private void loadUser() {
        String token;
        if (signin.Token.isEmpty()) {
            token = cma.token;

        } else {
            token = signin.Token;

        }
        ApiClass usersAPI = new ApiClass();
        Call<UserInfo> userInfoCall = usersAPI.calls().getUser( token );
        userInfoCall.enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( Dashboard.this, "Code " + response.code(), Toast.LENGTH_SHORT ).show();
                    return;
            }
                UserInfo userInfo= response.body();
                Toast.makeText( Dashboard.this, " "+userInfo.get_id(), Toast.LENGTH_SHORT ).show();
                name.setText( userInfo.getUsername() );
                email.setText( userInfo.getEmail() );
                String imgPath = imagePath +  userInfo.getImage();
                try {
                    URL url = new URL(imgPath);
                    imageView.setImageBitmap( BitmapFactory.decodeStream((InputStream) url.getContent()));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {
                Toast.makeText( Dashboard.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
            }
        });
    }
}
