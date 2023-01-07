package com.hussein.tvshows.responses;

import com.google.gson.annotations.SerializedName;
import com.hussein.tvshows.models.TVShow;

import java.util.List;

//all json file for most-popular
//this class get the response but TvShow just for tvShows(container)OR(List)
//to connect with pages and get it (the container for tv shows)
public class TVShowResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("pages")
    private int totalPages;

    @SerializedName("tv_shows")
    private List<TVShow> TVShows;

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<TVShow> getTVShows() {
        return TVShows;
    }
}
