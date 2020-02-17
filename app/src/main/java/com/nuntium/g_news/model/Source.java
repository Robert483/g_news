package com.nuntium.g_news.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Source {
    @SerializedName("id")
    @Expose
    private String id = null;

    public String getId() {
        return id;
    }

    @SerializedName("name")
    @Expose
    private String name = null;

    public String getName() {
        return name;
    }
}
