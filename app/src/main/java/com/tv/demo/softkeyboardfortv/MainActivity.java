package com.tv.demo.softkeyboardfortv;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.tv.demo.softkeyboardfortv.adapter.RecentSearchesAdapter;
import com.tv.demo.softkeyboardfortv.event.EventDisplayText;
import com.tv.demo.softkeyboardfortv.event.EventSearching;
import com.tv.demo.softkeyboardfortv.event.EventSendFocusedView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private EditText mEdtSearch;
    private CustomSoftKeyboardView customSoftKeyboardView;
    private RecyclerView mRvRecentSearches;
    private RecentSearchesAdapter mRecentSearchesAdapter;
    private LinearLayout mRoot;
    private View currentFocusedView;
    private View nextFocusedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRoot = findViewById(R.id.root);
        getWindow().getDecorView().setBackgroundColor(ContextCompat.getColor(this, R.color.activity_light_bg));
        mEdtSearch = findViewById(R.id.edt_search);
        mRvRecentSearches = findViewById(R.id.rv_recentSearches);
        customSoftKeyboardView = findViewById(R.id.customKeyboardView);
        setUpSuggestionsAdapter();
    }

    private void setUpSuggestionsAdapter() {
        mRecentSearchesAdapter = new RecentSearchesAdapter();
        mRvRecentSearches.setAdapter(mRecentSearchesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRvRecentSearches.setLayoutManager(linearLayoutManager);
    }

    private List<String> generateSuggestionsList() {
        List<String> suggestionsList = new ArrayList<>();
        suggestionsList.add("Doraemon");
        suggestionsList.add("Lord of The Rings");
        suggestionsList.add("Hannibal");
        suggestionsList.add("How To Train Your Dragon");
        suggestionsList.add("Game of Thrones Season 6");
        suggestionsList.add("The Walking Dead Season 4");
        suggestionsList.add("Geostorm");
        suggestionsList.add("Inception");
        suggestionsList.add("Jurassic World: Fallen Kingdom");
        suggestionsList.add("Deadpool 2");
        suggestionsList.add("How I Met Your Mother");
        suggestionsList.add("Tron: Legacy");
        return suggestionsList;
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void displayText(EventDisplayText eventDisplayText) {
        if (eventDisplayText != null) {
            mEdtSearch.setText(eventDisplayText.getDisplayedText().toString());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void showRecentSearches(EventSearching eventSearching) {
        if (eventSearching != null) {
            mRecentSearchesAdapter.setSearchContent(eventSearching.getSearchContent());
            mEdtSearch.setText(null);
            customSoftKeyboardView.reset();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void receiveFocusedViews(EventSendFocusedView eventSendFocusedView) {
        if (eventSendFocusedView != null) {
            currentFocusedView = eventSendFocusedView.getCurrentFocusedView();
            nextFocusedView = eventSendFocusedView.getNextFocusedView();
        }
    }


}
