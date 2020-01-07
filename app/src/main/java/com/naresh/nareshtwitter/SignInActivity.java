package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.naresh.nareshtwitter.api.LoginBill;
import com.naresh.nareshtwitter.model.User;

public class SignInActivity extends AppCompatActivity {
    EditText username, password;
    Button login;
    TextView sign_up;

    public static String Token = "";
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        login = findViewById(R.id.btn_login);
        sign_up = findViewById(R.id.tv_sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignInActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {

                    if (!TextUtils.isEmpty(username.getText().toString())) {
                        if (!TextUtils.isEmpty(password.getText().toString())) {
                            User u = new User(username.getText().toString(),
                                    password.getText().toString());
                            login(u);
                        } else {
                            password.setError("empty");
                        }
                    } else {
                        username.setError("empty");
                    }
                }
            }

            private void login(User u) {
                LoginBill loginBLL = new LoginBill();
                StrictModeClass.StrictMode();
                if (loginBLL.checkUser(u.getEmail(), u.getPassword())) {
                    Intent intent = new Intent(SignInActivity.this, Dashboard.class);
                    Token = loginBLL.Token;
                    startActivity(intent);
                } else {
                    Toast.makeText(SignInActivity.this, "Either username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

