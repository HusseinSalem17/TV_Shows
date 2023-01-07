package com.hussein.tvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hussein.tvshows.R;
import com.hussein.tvshows.databinding.ItemContainerTvShowBinding;
import com.hussein.tvshows.listeners.TVShowListener;
import com.hussein.tvshows.models.TVShow;

import java.util.List;

public class TVShowsAdapter extends RecyclerView.Adapter<TVShowsAdapter.TVShowsViewHolder> {

    private List<TVShow> tvShows;
    private LayoutInflater layoutInflater;
    private TVShowListener tvShowListener;

    public TVShowsAdapter(List<TVShow> tvShows, TVShowListener tvShowListener) {
        this.tvShows = tvShows;
        this.tvShowListener = tvShowListener;
    }

    @NonNull
    @Override
    public TVShowsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_tv_show, parent, false
        );
        return new TVShowsViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowsViewHolder holder, int position) {
        holder.bindTVShow(tvShows.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    class TVShowsViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerTvShowBinding itemContainerTvShowBinding;

        //itemContainerTvShowBinding => to get the Views from the layout
        public TVShowsViewHolder(ItemContainerTvShowBinding itemContainerTvShowBinding) {
            super(itemContainerTvShowBinding.getRoot());
            this.itemContainerTvShowBinding = itemContainerTvShowBinding;
        }

        //to set the value of the tvShow (Object) and send his values to the views
        public void bindTVShow(TVShow tvShow) {
            itemContainerTvShowBinding.setTvShow(tvShow);
            itemContainerTvShowBinding.executePendingBindings();
            itemContainerTvShowBinding.getRoot().setOnClickListener(v -> tvShowListener.onTVShowClicked(tvShow));
        }
    }
}
