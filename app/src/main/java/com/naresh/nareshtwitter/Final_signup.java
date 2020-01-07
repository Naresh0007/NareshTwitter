package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Final_signup extends AppCompatActivity {
    Button signup;
    String email="";
    String username="";

    TextView tv_email, tv_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_signup);
        signup = findViewById(R.id.btn_signup);
        tv_email = findViewById(R.id.final_email);
        tv_user = findViewById(R.id.final_username);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
            username = bundle.getString("username");
            tv_email.setText(bundle.getString("email"));
            tv_user.setText(bundle.getString("username"));
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Final_signup.this, VerificationActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
        tv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnto();
            }
        });
        tv_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                returnto();
            }
        });
    }
    void returnto(){
        Intent returns=new Intent( Final_signup.this,SignupActivity.class );
        returns.putExtra( "email",email );
        returns.putExtra( "username" ,username);
        startActivity( returns );

    }
}
