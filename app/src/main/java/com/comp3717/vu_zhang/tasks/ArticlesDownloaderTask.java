package com.comp3717.vu_zhang.tasks;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.comp3717.vu_zhang.SearchResultActivity;
import com.comp3717.vu_zhang.handlers.HttpHandler;
import com.comp3717.vu_zhang.model.Article;
import com.comp3717.vu_zhang.model.NewsAPIResponse;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;

/**
 * Async task class to get json by making HTTP call
 */
public class ArticlesDownloaderTask extends AsyncTask<Void, Void, Void> {
    private WeakReference<Activity> activity;
    private String url;

    public ArticlesDownloaderTask(Activity activity, String url) {
        this.activity = new WeakReference<>(activity);
        this.url = url;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... arg0) {
        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        if (jsonStr != null) {
            Gson gson = new Gson();
            NewsAPIResponse responses = gson.fromJson(jsonStr, NewsAPIResponse.class);
            SearchResultActivity.ArticleList = responses.getArticles();
        } else {
            final Activity activity = this.activity.get();
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(activity.getApplicationContext(),
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
        ListActivity activity = (ListActivity) this.activity.get();
        ArrayAdapter<Article> arrayAdapter = new ArrayAdapter<>(
                activity, android.R.layout.simple_list_item_1, SearchResultActivity.ArticleList
        );
        ListView listArticles = activity.getListView();
        listArticles.setAdapter(arrayAdapter);
    }
}
