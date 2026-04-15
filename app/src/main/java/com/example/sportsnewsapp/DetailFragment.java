package com.example.sportsnewsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.content.SharedPreferences;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class DetailFragment extends Fragment {

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button btnBack = view.findViewById(R.id.btnBack);
        Button btnBookmark = view.findViewById(R.id.btnBookmark);
        ImageView img = view.findViewById(R.id.imgDetail);
        TextView title = view.findViewById(R.id.tvDetailTitle);
        TextView desc = view.findViewById(R.id.tvDetailDesc);
        RecyclerView rvRelated = view.findViewById(R.id.rvRelated);

        btnBack.setOnClickListener(v ->
                getParentFragmentManager().popBackStack()
        );

        Bundle bundle = getArguments();

        if (bundle != null) {
            title.setText(bundle.getString("title"));
            desc.setText(bundle.getString("desc"));
            img.setImageResource(bundle.getInt("image"));
        }

        btnBookmark.setOnClickListener(v -> {
            String currentTitle = title.getText().toString();

            SharedPreferences prefs = requireActivity().getSharedPreferences("bookmarks", 0);
            Set<String> bookmarks = new HashSet<>(prefs.getStringSet("titles", new HashSet<>()));

            bookmarks.add(currentTitle);

            prefs.edit().putStringSet("titles", bookmarks).apply();

            Toast.makeText(getContext(), "Bookmarked!", Toast.LENGTH_SHORT).show();
        });

        rvRelated.setLayoutManager(new LinearLayoutManager(getContext()));

        List<NewsItem> relatedList = new ArrayList<>();

        relatedList.add(new NewsItem(
                "Another Football Story",
                "More football action",
                R.drawable.afl,
                "Football",
                false
        ));

        relatedList.add(new NewsItem(
                "Basketball Update",
                "Latest basketball news",
                R.drawable.nba,
                "Basketball",
                false
        ));

        NewsAdapter relatedAdapter = new NewsAdapter(relatedList, item -> {
            // optional: open another detail later
        });

        rvRelated.setAdapter(relatedAdapter);
    }
}