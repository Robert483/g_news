package com.nuntium.g_news;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.nuntium.g_news.model.Article;
import com.nuntium.g_news.model.HttpHandler;
import com.nuntium.g_news.model.NewsAPIResponse;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class SearchResultActivity extends ListActivity {
    private String TAG = SearchResultActivity.class.getSimpleName();
    public static List<Article> articleList = new ArrayList<>();
    private String service_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // format the url based on user inputs
        //TODO main activity pass in message
        String query = "Vancouver";
        SimpleDateFormat date_pattern = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        int DAY_IN_MS = 1000 * 60 * 60 * 24;
        String date_text = date_pattern.format(new Date((new Date()).getTime() - (7 * DAY_IN_MS)));
        service_url = getString(R.string.search_result_url, query, date_text);

        new GetArticles().execute();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(this, ArticleActivity.class);
        i.putExtra("index", (int) id);
        startActivity(i);
    }

    /**
     * Async task class to get json by making HTTP call
     */
    private class GetArticles extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting response
            String jsonStr = sh.makeServiceCall(service_url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                Log.d(TAG, "Json: " + jsonStr);
                Gson gson = new Gson();
                NewsAPIResponse responses = gson.fromJson(jsonStr, NewsAPIResponse.class);
                articleList = responses.getArticles();
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            ArrayAdapter<Article> arrayAdapter = new ArrayAdapter<>(
                    SearchResultActivity.this, android.R.layout.simple_list_item_1, articleList
            );
            ListView listArticles = getListView();
            listArticles.setAdapter(arrayAdapter);
        }
    }
}