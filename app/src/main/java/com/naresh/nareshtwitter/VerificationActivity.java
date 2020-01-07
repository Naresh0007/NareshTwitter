package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class VerificationActivity extends AppCompatActivity {
    TextView tv_verification;
    Button btn_next;
    String email = "";
    String username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        tv_verification = findViewById( R.id.tvCode );
        btn_next = findViewById( R.id.btn_CodeLogin );
        Thread timer = new Thread() {
            @Override
            public void run() {
                try {
                    sleep( 1000 );
                    tv_verification.setText( "1,2,5,4,0,6" );
                    Bundle bundle = getIntent().getExtras();
                    if (bundle != null) {
                        email = bundle.getString( "email" );
                        username = bundle.getString( "username" );
                        Log.d( "email", email );
                        Log.d( "username", username );

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        timer.start();

        btn_next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_verification.getText().toString().isEmpty()) {
                    Toast.makeText( VerificationActivity.this, "Wait", Toast.LENGTH_SHORT ).show();
                    return;
                }
                Intent intent = new Intent( VerificationActivity.this, PasswordActivity.class );
                intent.putExtra( "email", email );
                intent.putExtra( "username", username );
                startActivity( intent );
            }
        } );
    }

}