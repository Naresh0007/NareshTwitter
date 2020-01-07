package com.naresh.nareshtwitter;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class PasswordActivity extends AppCompatActivity {
    EditText et_pass;
    TextView tv_error;
    ImageButton imageButton;
    int i = 0;
    String email = "";
    String username = "";
    String password="";
    Button btnlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        et_pass = findViewById( R.id.et_Pass );
        tv_error= findViewById( R.id.tv_error );
        imageButton = findViewById( R.id.btn_showpass );
        btnlogin = findViewById( R.id.btn_login );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString( "email" );
            username = bundle.getString( "username" );

        }
        btnlogin.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.isEmpty()){
                    Toast.makeText( PasswordActivity.this, "check password", Toast.LENGTH_SHORT ).show();
                    return;}else{
                    Intent intent = new Intent( PasswordActivity.this, CameraActivity.class);
                    intent.putExtra( "email",email );
                    intent.putExtra( "username",username );
                    intent.putExtra( "password",password );
                    startActivity( intent );
                }
            }
        } );
        et_pass.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (et_pass.length() >= 6) {
                    tv_error.setTextColor( Color.BLACK );
                    tv_error.setText( "" );
                    password=et_pass.getText().toString();
                    et_pass.setBackgroundTintList( ColorStateList.valueOf( Color.parseColor( "#00acee" ) ) );
                    return;
                } else {
                    et_pass.setBackgroundTintList( ColorStateList.valueOf( Color.RED ) );
                    tv_error.setTextColor( Color.RED );
                    tv_error.setText( "password must be more than 6 chracters" );
                    return;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        imageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    et_pass.setInputType( InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD );
                    i++;
                } else {
                    et_pass.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                    i = 0;
                }

            }
        } );
    }
}

