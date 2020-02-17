package com.comp3717.vu_zhang.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import androidx.annotation.NonNull;

public class Article {
    @SerializedName("title")
    @Expose
    private String title = null;

    public String getTitle() {
        return title;
    }

    @SerializedName("source")
    @Expose
    private Source source = null;

    public Source getSource() {
        return source;
    }

    @SerializedName("author")
    @Expose
    private String author = null;

    public String getAuthor() {
        return author;
    }

    @SerializedName("description")
    @Expose
    private String description = null;

    public String getDescription() {
        return description;
    }

    @SerializedName("url")
    @Expose
    private String url = null;

    public String getUrl() {
        return url;
    }

    @SerializedName("urlToImage")
    @Expose
    private String urlToImage = null;

    public String getUrlToImage() {
        return urlToImage;
    }

    @SerializedName("publishedAt")
    @Expose
    private Date publishedAt = null;

    public Date getPublishedAt() {
        return publishedAt;
    }

    @SerializedName("content")
    @Expose
    private String content = null;

    public String getContent() {
        return content;
    }

    @NonNull
    @Override
    public String toString() {
        return this.title;
    }
}
