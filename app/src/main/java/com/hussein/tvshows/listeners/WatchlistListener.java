package com.hussein.tvshows.listeners;

import com.hussein.tvshows.models.TVShow;

public interface WatchlistListener {

    void onTVShowClicked(TVShow tvShow);
    //listener for imageDelete
    void removeTVShowFromWatchlist(TVShow tvShow, int position);

}
