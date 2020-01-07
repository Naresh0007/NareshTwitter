package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.naresh.nareshtwitter.api.ApiClass;
import com.naresh.nareshtwitter.model.Check;
import com.naresh.nareshtwitter.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    EditText email, username;
    ImageView Us, Em, back;
    Button next;
    int countUsername = 0;
    int initialbtn = 0;
    String method = "email";
    String Email = "";
    String Username = "";
    boolean chekU=false;
    boolean chekE=false;
    TextView tvChange, sn_em_error, sn_us_error;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        email = findViewById( R.id.et_email );
        username = findViewById( R.id.et_username );
        back=findViewById( R.id.image_back);
        sn_em_error = findViewById( R.id.tv_pass_error );
        sn_us_error = findViewById( R.id.tv_username_error );
        Us = findViewById( R.id.image_check );
        Em = findViewById( R.id.image_emailCheck );
        next = findViewById( R.id.btn_Nextsignup );
        tvChange = findViewById( R.id.textView9 );
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Email = bundle.getString( "email" );
            Username = bundle.getString( "username" );
            email.setText( bundle.getString( "email" ) );
            username.setText( bundle.getString( "username" ) );
        }
        back.setOnClickListener( new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Intent back=new Intent( SignupActivity.this,MainActivity.class );
                                         startActivity( back );
                                     }
                                 }
        );
        next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chekE==true&&chekU==true){

                    User user = new User( Email );
                    Checkuser( user );

                }else{
                    Toast.makeText( SignupActivity.this, "fill require field with valid information", Toast.LENGTH_SHORT ).show();
                    return;
                }

            }
        } );
        username.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int countL = username.length();
                if (count > 0) {
                    if (countUsername >= 0) {
                        countUsername = 50 - countL;
                        sn_us_error.setTextColor( Color.BLACK );
                        sn_us_error.setText( "" + countUsername );
                        Us.setImageResource( R.drawable.ic_checked );
                        chekU=true;
                        Username = username.getText().toString();
                        return;
                    } else if (countUsername < 0) {
                        countUsername = 50 - countL;
                        sn_us_error.setTextColor( Color.RED );
                        sn_us_error.setText( "Must be 50 characters or fewer." );
                        sn_us_error.append( "      " + countUsername );
                        chekU=false;
                        Us.setImageResource( R.drawable.ic_clear );
                        return;


                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );
        tvChange.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (initialbtn == 0) {
                    method = "phone";
                    email.setText( "" );
                    initialbtn++;
                    email.setHint( "used Phone number" );
                    email.setInputType( InputType.TYPE_CLASS_PHONE );
                    email.setMaxLines( 13 );
                    tvChange.setText( "use email instead" );
                    return;
                } else {
                    method = "email";
                    email.setText( "" );
                    initialbtn = 0;
                    email.setInputType( InputType.TYPE_CLASS_TEXT );
                    email.setHint( "used Email" );
                    tvChange.setText( "use phone instead" );
                    return;
                }

            }
        } );
        email.addTextChangedListener( new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                switch (method) {
                    case "email":
                        sn_em_error.setText( "" );
                        if ((email.getText().toString().toLowerCase().contains( "@" )) && (email.getText().toString().toLowerCase().contains( ".com" ))) {
                            Em.setImageResource( R.drawable.ic_checked );
                            Email = email.getText().toString();
                            chekE=true;
                        } else {
                            sn_em_error.setText( "check your email" );
                            Em.setImageResource( R.drawable.ic_clear );
                            chekE=false;

                        }
                        break;
                    case "phone":
                        sn_em_error.setText( "" );
                        if ((email.length() != 10)) {
                            sn_em_error.setText( "check your number" );
                            Em.setImageResource( R.drawable.ic_clear );
                            chekE=false;
                            return;

                        } else {
                            Em.setImageResource( R.drawable.ic_checked );
                            Email = email.getText().toString();
                            chekE=true;
                            return;

                        }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

    }

    void Checkuser(User us) {
        ApiClass apiClass = new ApiClass();
        Call<Check> checkCall = apiClass.calls().check( us );
        checkCall.enqueue( new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText( SignupActivity.this, "error" + response.code(), Toast.LENGTH_SHORT ).show();
                    Log.d( "error", "error" + response.code() );
                    return;
                }
                Check check = response.body();
                //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                if (check.getStatus().equals( "good to go" )) {
                    Intent next = new Intent( SignupActivity.this, CostumizationActivity.class );
                    next.putExtra( "email", Email );
                    next.putExtra( "username", Username );
                    startActivity( next );
                    return;
                } else {
                    //Toast.makeText( SignUP.this, "user " + check.getStatus(), Toast.LENGTH_SHORT ).show();
                    sn_em_error.setText( "exited" );
                    sn_em_error.setTextColor( Color.RED );
                }
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Toast.makeText( SignupActivity.this, "error" + t.getLocalizedMessage(), Toast.LENGTH_SHORT ).show();
                Log.d( "error", "error   " + t.getLocalizedMessage() );

            }
        } );
    }
}