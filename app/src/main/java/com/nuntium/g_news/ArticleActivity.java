package com.nuntium.g_news;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class ArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article);

        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault());
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            date = formatter.parse("2020-02-16T21:00:56Z");
        } catch (ParseException ex) {
            // Do nothing
        }

        String title = "3 Things You Need To Be A Successful Bitcoin HODLer";
        String source = getString(R.string.article_src, "Bitcoinist.com");
        String authorAndTime = getString(R.string.article_author_publishedat, "Trevor Smith", date);
        String description = "And the number one spot of the Cointelegraph’s first-ever Top 100 list goes to… Changpeng Zhao! Was there any doubt? Check out our exclusive interview to find out what’s behind this great workaholic";
        String url = getString(R.string.article_url,"https://bitcoinist.com/3-things-successful-bitcoin-hodler/");
        String content = "Bitcoin blogger Sylvain Saurel has published a thoughtful piece on essential qualities to being a True Bitcoiner. Although insightful, there are other factors that should be considered when choosing to invest in and hold the flagship cryptocurrency.\\r\\nUndersta… [+2937 chars]";

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
}
