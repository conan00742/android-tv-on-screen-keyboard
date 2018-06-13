package com.tv.demo.softkeyboardfortv.adapter;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tv.demo.softkeyboardfortv.R;

import java.util.ArrayList;
import java.util.List;


public class RecentSearchesAdapter extends RecyclerView.Adapter<RecentSearchesAdapter.RecentSearchesViewHolder> {

    private List<String> recentSearches;

    public RecentSearchesAdapter() {
        this.recentSearches = new ArrayList<>();
    }

    public void setSearchContent(String searchContent) {
        this.recentSearches.add(searchContent);
        notifyDataSetChanged();
    }

    @Override
    public RecentSearchesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_item, parent, false);
        return new RecentSearchesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecentSearchesViewHolder holder, int position) {
        holder.bindData(getSuggestionAt(position));
    }

    @Override
    public int getItemCount() {
        return recentSearches.size();
    }

    private String getSuggestionAt(int position) {
        return recentSearches.get(position);
    }

    class RecentSearchesViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvSuggestion;

        public RecentSearchesViewHolder(View itemView) {
            super(itemView);
            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.search_item_focused_background_drawable));
                        mTvSuggestion.setTextColor(Color.BLACK);
                    } else {
                        ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.search_item_background_drawable));
                        mTvSuggestion.setTextColor(Color.WHITE);
                    }
                }
            });
            mTvSuggestion = itemView.findViewById(R.id.tv_suggestion);
        }

        public void bindData(String data) {
            mTvSuggestion.setText(data);
        }
    }
}
