package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DescribeActivity extends AppCompatActivity {
    Button btn_Next;
    TextView tv_Skip;
    EditText et_Bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_describe);
        tv_Skip=findViewById( R.id.tv_bio_skip );
        btn_Next=findViewById( R.id.btn_bio_next);
        et_Bio=findViewById( R.id.et_bio );
        tv_Skip.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTO();
            }
        } );
        btn_Next.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_Bio.getText().toString().isEmpty()){
                    Toast.makeText( DescribeActivity.this, "fill your bio first", Toast.LENGTH_SHORT ).show();
                    return;}
                else{
                    NextTO();
                }
            }
        } );
    }
    void NextTO(){
        Intent intent = new Intent( DescribeActivity.this,LikesActivity.class );
        startActivity( intent );
    }
}
