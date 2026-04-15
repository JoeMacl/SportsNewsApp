package com.example.sportsnewsapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.widget.Button;

public class BookmarksFragment extends Fragment {

    public BookmarksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmarks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnBackBookmarks = view.findViewById(R.id.btnBackBookmarks);
        RecyclerView rvBookmarks = view.findViewById(R.id.rvBookmarks);

        btnBackBookmarks.setOnClickListener(v ->
                getParentFragmentManager().popBackStack()
        );

        rvBookmarks.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences prefs = requireActivity().getSharedPreferences("bookmarks", 0);
        Set<String> savedTitles = prefs.getStringSet("titles", null);

        List<NewsItem> bookmarkList = new ArrayList<>();

        if (savedTitles != null) {
            List<NewsItem> allNews = new ArrayList<>();

            allNews.add(new NewsItem(
                    "Football Finals",
                    "Exciting match ends in draw",
                    R.drawable.afl,
                    "Football",
                    true
            ));

            allNews.add(new NewsItem(
                    "Basketball Championship",
                    "Top teams face off",
                    R.drawable.nba,
                    "Basketball",
                    true
            ));

            allNews.add(new NewsItem(
                    "Cricket Series",
                    "Australia dominates the series",
                    R.drawable.cricket,
                    "Cricket",
                    true
            ));

            for (NewsItem item : allNews) {
                if (savedTitles.contains(item.getTitle())) {
                    bookmarkList.add(item);
                }
            }
        }

        NewsAdapter adapter = new NewsAdapter(bookmarkList, item -> {
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

        rvBookmarks.setAdapter(adapter);
    }
}