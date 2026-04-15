package com.example.sportsnewsapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import android.widget.Toast;
import android.widget.Button;

public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView rvFeatured = view.findViewById(R.id.rvFeatured);
        RecyclerView rvNews = view.findViewById(R.id.rvNews);

        Button btnBookmarks = view.findViewById(R.id.btnBookmarks);

        btnBookmarks.setOnClickListener(v -> {
            BookmarksFragment bookmarksFragment = new BookmarksFragment();

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, bookmarksFragment)
                    .addToBackStack(null)
                    .commit();
        });

        // Set layout managers
        rvFeatured.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        rvNews.setLayoutManager(
                new LinearLayoutManager(getContext())
        );

        // Dummy data
        List<NewsItem> newsList = new ArrayList<>();

        newsList.add(new NewsItem(
                "Football Finals",
                "Tense match ends in a draw",
                R.drawable.afl,
                "Football",
                false
        ));

        newsList.add(new NewsItem(
                "Basketball Championship",
                "The best teams face-off in the final",
                R.drawable.nba,
                "Basketball",
                true
        ));

        newsList.add(new NewsItem(
                "Cricket Series",
                "Australia pulls off a comeback to win",
                R.drawable.cricket,
                "Cricket",
                true
        ));

        List<NewsItem> featuredList = new ArrayList<>();

        for (NewsItem item : newsList) {
            if (item.isFeatured()) {
                featuredList.add(item);
            }
        }

        // Adapter
        FeaturedAdapter featuredAdapter = new FeaturedAdapter(featuredList, item -> {
            DetailFragment detailFragment = new DetailFragment();

            Bundle bundle = new Bundle();
            bundle.putString("title", item.getTitle());
            bundle.putString("desc", item.getDescription());
            bundle.putInt("image", item.getImageResId());

            detailFragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        rvFeatured.setAdapter(featuredAdapter);

        NewsAdapter adapter = new NewsAdapter(newsList, item -> {

            DetailFragment detailFragment = new DetailFragment();

            Bundle bundle = new Bundle();
            bundle.putString("title", item.getTitle());
            bundle.putString("desc", item.getDescription());
            bundle.putInt("image", item.getImageResId());

            detailFragment.setArguments(bundle);

            getParentFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detailFragment)
                    .addToBackStack(null)
                    .commit();
        });

        rvNews.setAdapter(adapter);
    }
}