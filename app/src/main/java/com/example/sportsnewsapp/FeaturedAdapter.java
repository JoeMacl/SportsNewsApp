package com.example.sportsnewsapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedViewHolder> {

    private List<NewsItem> featuredList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(NewsItem item);
    }

    public FeaturedAdapter(List<NewsItem> featuredList, OnItemClickListener listener) {
        this.featuredList = featuredList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public FeaturedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_featured, parent, false);
        return new FeaturedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedViewHolder holder, int position) {
        NewsItem item = featuredList.get(position);

        holder.tvFeaturedTitle.setText(item.getTitle());
        holder.imgFeatured.setImageResource(item.getImageResId());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return featuredList.size();
    }

    public static class FeaturedViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFeatured;
        TextView tvFeaturedTitle;

        public FeaturedViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFeatured = itemView.findViewById(R.id.imgFeatured);
            tvFeaturedTitle = itemView.findViewById(R.id.tvFeaturedTitle);
        }
    }
}