package com.nuntium.g_news;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.nuntium.g_news.model.Article;
import com.nuntium.g_news.tasks.ArticlesDownloaderTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class SearchResultActivity extends ListActivity {
    public static List<Article> ArticleList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // format the url based on user inputs
        String query = getIntent().getStringExtra("keyword");
        SimpleDateFormat date_pattern = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        int DAY_IN_MS = 1000 * 60 * 60 * 24;
        String date_text = date_pattern.format(new Date((new Date()).getTime() - (7 * DAY_IN_MS)));

        String service_url = getString(R.string.search_result_url, query, date_text);
        new ArticlesDownloaderTask(this, service_url).execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, ArticleActivity.class);
        i.putExtra("index", (int) id);
        startActivity(i);
    }
}