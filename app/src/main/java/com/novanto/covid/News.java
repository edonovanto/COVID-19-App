package com.novanto.covid;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.novanto.covid.news.ListNewsAdapter;
import com.novanto.covid.news.NewsModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class News extends Fragment {

    private RecyclerView rvNews;

    WebView myWebView;

    ArrayList<NewsModel> list = new ArrayList<>();

    ArrayList<String> titleArr = new ArrayList<>();
    ArrayList<String> descArr = new ArrayList<>();
    ArrayList<String> urlArr = new ArrayList<>();
    ArrayList<String> photoArr = new ArrayList<>();

    private ProgressBar progressBar;
    private static final String TAG = News.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.news_frag, container, false);

        rvNews = (RecyclerView) rootView.findViewById(R.id.rv_news);
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));

        myWebView = (WebView) rootView.findViewById(R.id.webView);

        progressBar = (ProgressBar) rootView.findViewById(R.id.progressBar);

        callAPI();

        return rootView;
    }

    public void callAPI(){
        progressBar.setVisibility(View.VISIBLE);

        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.smartable.ai/coronavirus/news/US";
        client.addHeader("Subscription-Key", "a4349d14f30045fda88a6706a6364739");

        client.get(url,  new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                progressBar.setVisibility(View.INVISIBLE);

                String result = new String(responseBody);

                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray dataArray = jsonObject.getJSONArray("news");

                    for(int i=0;i<dataArray.length();i++){
                        JSONObject dataObject = dataArray.getJSONObject(i);
                        String title = dataObject.getString("title");
                        String desc = dataObject.getString("excerpt");
                        String url = dataObject.getString("webUrl");
//                        JSONArray photoData = dataObject.getJSONArray("images");
//                        JSONObject dataPhoto = photoData.getJSONObject(0);
//                        String photo = dataPhoto.getString("url");

                        titleArr.add(title);
                        descArr.add(desc);
                        urlArr.add(url);
//                        photoArr.add(photo);
                    }

                    Log.d(TAG, "Get List News");
                    list.addAll(getListNews());
                    showRecyclerList(list);

                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
                // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                progressBar.setVisibility(View.INVISIBLE);
                String errorMessage;
                switch (statusCode) {
                    case 401:
                        errorMessage = statusCode + " : Bad Request";
                        break;
                    case 403:
                        errorMessage = statusCode + " : Forbidden";
                        break;
                    case 404:
                        errorMessage = statusCode + " : Not Found";
                        break;
                    default:
                        errorMessage =  statusCode + " : " + e.getMessage();
                        break;
                }
//                Toast.makeText(getActivity(), errorMessage, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public ArrayList<NewsModel> getListNews(){

        ArrayList<NewsModel> listNews = new ArrayList<>();

        for (int i = 0;i<titleArr.size();i++){
            NewsModel newsModel = new NewsModel();
            newsModel.setTitle(titleArr.get(i));
            newsModel.setDescription(descArr.get(i));
            newsModel.setUrl(urlArr.get(i));

//            Log.d(TAG,"Adding Title " + i + " :" + titleArr.get(i) + "\n Desc: " + descArr.get(i)+ " \n URL : " + urlArr.get(i) + "\n");

            listNews.add(newsModel);
        }
        
        return listNews;

    }

    public void showRecyclerList(ArrayList<NewsModel> list){
        rvNews.setLayoutManager(new LinearLayoutManager(getActivity()));
        ListNewsAdapter listNewsAdapter = new ListNewsAdapter(list);
        rvNews.setAdapter(listNewsAdapter);

        listNewsAdapter.setOnItemClickCallback(new ListNewsAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(NewsModel data) {
                showSelectedNews(data);
            }
        });
    }

    private void showSelectedNews(NewsModel newsModel){
            myWebView.loadUrl(newsModel.getUrl());
            myWebView.setVisibility(View.VISIBLE);
        Toast.makeText(getActivity(),"You choose : " + newsModel.getTitle() ,Toast.LENGTH_SHORT).show();
    }

}