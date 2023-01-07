package com.hussein.tvshows.network;

import com.hussein.tvshows.responses.TVShowDetailsResponse;
import com.hussein.tvshows.responses.TVShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    //page is the value for most-popular to get page number
    @GET("most-popular")
    Call<TVShowResponse> getMostPopularTVShows(@Query("page") int page);

    @GET("show-details")
    Call<TVShowDetailsResponse> getTVShowDetails(@Query("q") String tvShowId);

}
