package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Login;
    Button createUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Login = findViewById( R.id.tv_login );
        createUser = findViewById( R.id.btn_createUser );
        Login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login= new Intent( MainActivity.this,SignInActivity.class );
                startActivity( login );
            }
        } );
        createUser.setOnClickListener( new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               Intent signUP= new Intent( MainActivity.this,SignupActivity.class );
                                               startActivity( signUP );
                                           }
                                       }
        );
    }

}
