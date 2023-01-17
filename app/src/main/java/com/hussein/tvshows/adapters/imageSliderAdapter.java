package com.hussein.tvshows.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.hussein.tvshows.R;
import com.hussein.tvshows.databinding.ItemContainerSliderImageBinding;

public class imageSliderAdapter extends RecyclerView.Adapter<imageSliderAdapter.ImageSliderViewHolder> {

    private String[] sliderImages;
    private LayoutInflater layoutInflater;

    public imageSliderAdapter(String[] sliderImages) {
        this.sliderImages = sliderImages;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerSliderImageBinding sliderImagesBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_container_slider_image, parent, false);
        return new ImageSliderViewHolder(sliderImagesBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {

        holder.bindSliderImage(sliderImages[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImages.length;
    }

    static class ImageSliderViewHolder extends RecyclerView.ViewHolder {

        private ItemContainerSliderImageBinding itemContainerSliderImageBinding;

        public ImageSliderViewHolder(ItemContainerSliderImageBinding itemContainerSliderImageBinding) {
            super(itemContainerSliderImageBinding.getRoot());
            this.itemContainerSliderImageBinding = itemContainerSliderImageBinding;
        }

        public void bindSliderImage(String imageURL) {
            itemContainerSliderImageBinding.setImageURL(imageURL);
        }

    }
}
