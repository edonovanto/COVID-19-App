package com.novanto.covid.news;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.novanto.covid.News;
import com.novanto.covid.R;

import java.util.ArrayList;

public class ListNewsAdapter extends RecyclerView.Adapter<ListNewsAdapter.ListNewsHolder> {

    private ArrayList<NewsModel> listNews;
    private static final String TAG = News.class.getSimpleName();

    public ListNewsAdapter(ArrayList<NewsModel> list){
        this.listNews = list;
    }

    @NonNull
    @Override
    public ListNewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ListNewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListNewsHolder holder, int position) {
        NewsModel newsModel = listNews.get(position);

//        Glide.with(holder.itemView.getContext())
//                .load(newsModel.getPhoto())
//                .apply(new RequestOptions().override(140,90))
//                .into(holder.imgPhoto);


        holder.tvTitle.setText(newsModel.getTitle());
        holder.tvDesc.setText(newsModel.getDescription());
    }

    @Override
    public int getItemCount() {
        Log.d(TAG,"Size List News : " + listNews.size());
        return listNews.size();
    }

    public class ListNewsHolder extends RecyclerView.ViewHolder {

        ImageView imgPhoto;
        TextView tvTitle, tvDesc;

        public ListNewsHolder(@NonNull View itemView) {
            super(itemView);
//            imgPhoto = (ImageView) itemView.findViewById(R.id.img_item_news);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_news);
            tvDesc = (TextView) itemView.findViewById(R.id.tv_decs_news);
        }
    }
}
