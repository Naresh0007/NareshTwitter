package com.naresh.nareshtwitter.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.naresh.nareshtwitter.R;
import com.naresh.nareshtwitter.StrictMode.StrictModeClass;
import com.naresh.nareshtwitter.model.TweetM;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AdapterTweet extends RecyclerView.Adapter<AdapterTweet.TweetList> {
    Context context;
    List<TweetM> dataSetList;
    public static final String base_url = "http://10.0.2.2:3000/";
    String imagePath = base_url + "uploads/";

    public AdapterTweet(Context context, List<TweetM> dataSetList) {
        this.context = context;
        this.dataSetList = dataSetList;
    }

    @NonNull
    @Override
    public AdapterTweet.TweetList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.homel_rv, parent, false);
        return new AdapterTweet.TweetList(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterTweet.TweetList holder, int position) {

        TweetM tm = dataSetList.get(position);
        holder.txt_head.setText(tm.getHeadingtext());
        holder.txt_body.setText(tm.getMessagetext());
        StrictModeClass.StrictMode();
        String imgPath = imagePath + tm.getImage();
        try {
            URL url = new URL(imgPath);
            holder.m_img.setImageBitmap(BitmapFactory.decodeStream((InputStream) url.getContent()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return dataSetList.size();
    }

    public class TweetList extends RecyclerView.ViewHolder{
        ImageView p_img, m_img;
        TextView txt_head, txt_body;

        public TweetList(@NonNull View itemView) {
            super(itemView);
            p_img = itemView.findViewById(R.id.P_img);
            m_img = itemView.findViewById(R.id.M_img);
            txt_body = itemView.findViewById(R.id.M_txt);
            txt_head = itemView.findViewById(R.id.H_txt);
        }
    }
}