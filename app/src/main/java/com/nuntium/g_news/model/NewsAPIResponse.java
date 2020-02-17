package com.nuntium.g_news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsAPIResponse {

    @SerializedName("status")
    @Expose
    private String status = null;

    @SerializedName("totalResults")
    @Expose
    private int totalResults;

    @SerializedName("articles")
    @Expose
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }
}
