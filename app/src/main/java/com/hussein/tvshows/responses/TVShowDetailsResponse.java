package com.hussein.tvshows.responses;

import com.google.gson.annotations.SerializedName;
import com.hussein.tvshows.models.TVShowDetails;

public class TVShowDetailsResponse {

    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
