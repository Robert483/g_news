package com.nuntium.g_news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.nuntium.g_news.model.Article;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ArticleActivity extends AppCompatActivity {
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        int articleIndex = getIntent().getIntExtra("index", -1);
        Article article = SearchResultActivity.articleList.get(articleIndex);
        Date date = article.getPublishedAt();

        String title = article.getTitle();
        String source = getString(R.string.article_src, article.getSource().getName());
        String authorAndTime = getString(R.string.article_author_publishedat, article.getAuthor(), date);
        String description = article.getDescription();
        String url = getString(R.string.article_url,article.getUrl());
        this.url = article.getUrl();
        String content = article.getContent();

        TextView textView = findViewById(R.id.txtv_title);
        textView.setText(title);
        textView = findViewById(R.id.txtv_src);
        textView.setText(Html.fromHtml(source, Html.FROM_HTML_MODE_COMPACT));
        textView = findViewById(R.id.txtv_author_publishedat);
        textView.setText(Html.fromHtml(authorAndTime, Html.FROM_HTML_MODE_COMPACT));
        textView = findViewById(R.id.txtv_description);
        textView.setText(description);
        textView = findViewById(R.id.txtv_url);
        textView.setText(Html.fromHtml(url, Html.FROM_HTML_MODE_COMPACT));
        textView = findViewById(R.id.txtv_content);
        textView.setText(content);



    }

    public void onClickURL(View view) {
        Uri uri = Uri.parse(url);
        Intent viewIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(viewIntent);
    }
}
