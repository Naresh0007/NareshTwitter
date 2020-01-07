package com.naresh.nareshtwitter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.naresh.nareshtwitter.adapter.AdapterInterest;
import com.naresh.nareshtwitter.model.DataSet;

import java.util.ArrayList;
import java.util.List;

public class LikesActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button btnNext;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        recyclerView=findViewById( R.id.RV_likes );
        btnNext=findViewById( R.id.btn_next );
        tv=findViewById( R.id.tv_skip );

        List<DataSet> interestsList = new ArrayList<>();
        interestsList.add( new DataSet("Sport", "NFL", "NBA", "MLB","Soccer","NHL"));
        interestsList.add( new DataSet("News", "Weather", "History", "Politics","Health","game"));
        interestsList.add( new DataSet("Music", "Pop", "Hip-Hop", "Country","Rock","game"));
        interestsList.add( new DataSet("Country", "Nepal", "China", "Japan","Korea","game"));
        interestsList.add( new DataSet("Sport", "NFL", "NBA", "MLB","Soccer","NHL"));
        interestsList.add( new DataSet("News", "Weather", "History", "Politics","Health","game"));
        interestsList.add( new DataSet("Music", "Pop", "Hip-Hop", "Country","Classic Rock","game"));
        interestsList.add( new DataSet("Country", "Nepal", "China", "Japan","Korea","game"));
        AdapterInterest adapterInterest = new AdapterInterest(this, interestsList);
        recyclerView.setAdapter( adapterInterest );
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        tv.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTO();
            }
        } );
        btnNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NextTO();
            }
        } );

    }
    void NextTO(){
        Intent intent = new Intent( LikesActivity.this,Dashboard.class );
        startActivity( intent );
    }
}