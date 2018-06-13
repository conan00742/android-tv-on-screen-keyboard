package com.tv.demo.softkeyboardfortv;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.tv.demo.softkeyboardfortv.adapter.KeyboardsAdapter;
import com.tv.demo.softkeyboardfortv.event.EventDisplayText;
import com.tv.demo.softkeyboardfortv.event.EventSearching;
import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;


public class CustomSoftKeyboardView extends FrameLayout {

    private RecyclerView mRvKeys;
    private TextView mTvKeyDelete;
    private TextView mTvKeyClear;
    private TextView mTvKeyNumbers;
    private TextView mTvKeySpace;
    private TextView mTvKeySearch;

    private int mContentLen;
    private StringBuilder mDisplayedText;
    private List<String> mCharacterKeyData;
    private List<String> mNumberKeyData;
    private KeyboardsAdapter mAdapter;
    private View mCurrentFocusedView;
    private View mNextFocusedView;
    private boolean isChangeToNumPads;

    public CustomSoftKeyboardView(@NonNull Context context) {
        super(context);
        initViews();
        initNumberKeyPads();
        initCharacterKeyPads();
        initAdapter(context);
    }

    public CustomSoftKeyboardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initViews();
        initNumberKeyPads();
        initCharacterKeyPads();
        initAdapter(context);
    }

    public CustomSoftKeyboardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
        initNumberKeyPads();
        initCharacterKeyPads();
        initAdapter(context);
    }

    public CustomSoftKeyboardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initViews();
        initNumberKeyPads();
        initCharacterKeyPads();
        initAdapter(context);
    }


    private void initViews() {
        mDisplayedText = new StringBuilder();
        mContentLen = 0;
        isChangeToNumPads = false;
        View keyboardView = LayoutInflater.from(getContext()).inflate(R.layout.custom_keyboard_item, null);
        mRvKeys = keyboardView.findViewById(R.id.rv_keyPads);
        mTvKeyDelete = keyboardView.findViewById(R.id.tv_keyDelete);
        mTvKeyDelete.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.pad_background_focus_light_drawable));
                    mTvKeyDelete.setTextColor(Color.WHITE);
                } else {
                    ViewCompat.setBackground(v, null);
                    mTvKeyDelete.setTextColor(Color.BLACK);
                }
            }
        });

        mTvKeyClear = keyboardView.findViewById(R.id.tv_keyClear);
        mTvKeyClear.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.pad_background_focus_light_drawable));
                    mTvKeyClear.setTextColor(Color.WHITE);
                } else {
                    ViewCompat.setBackground(v, null);
                    mTvKeyClear.setTextColor(Color.BLACK);
                }
            }
        });

        mTvKeyNumbers = keyboardView.findViewById(R.id.tv_keyNumbers);
        mTvKeyNumbers.setText(getContext().getString(R.string.number_pads));
        mTvKeyNumbers.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.pad_background_focus_light_drawable));
                    mTvKeyNumbers.setTextColor(Color.WHITE);
                } else {
                    ViewCompat.setBackground(v, null);
                    mTvKeyNumbers.setTextColor(Color.BLACK);
                }
            }
        });

        mTvKeySpace = keyboardView.findViewById(R.id.tv_space);
        mTvKeySpace.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.pad_background_focus_light_drawable));
                    mTvKeySpace.setTextColor(Color.WHITE);
                } else {
                    ViewCompat.setBackground(v, null);
                    mTvKeySpace.setTextColor(Color.BLACK);
                }
            }
        });

        mTvKeySearch = keyboardView.findViewById(R.id.tv_search);
        mTvKeySearch.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.pad_background_focus_light_drawable));
                    mTvKeySearch.setTextColor(Color.WHITE);
                } else {
                    ViewCompat.setBackground(v, null);
                    mTvKeySearch.setTextColor(Color.BLACK);
                }
            }
        });

        addView(keyboardView);


    }

    private void initCharacterKeyPads() {
        mCharacterKeyData = new ArrayList<>();
        mCharacterKeyData.add("A");
        mCharacterKeyData.add("B");
        mCharacterKeyData.add("C");
        mCharacterKeyData.add("D");
        mCharacterKeyData.add("E");
        mCharacterKeyData.add("F");
        mCharacterKeyData.add("G");
        mCharacterKeyData.add("H");
        mCharacterKeyData.add("I");
        mCharacterKeyData.add("J");
        mCharacterKeyData.add("K");
        mCharacterKeyData.add("L");
        mCharacterKeyData.add("M");
        mCharacterKeyData.add("N");
        mCharacterKeyData.add("O");
        mCharacterKeyData.add("P");
        mCharacterKeyData.add("Q");
        mCharacterKeyData.add("R");
        mCharacterKeyData.add("S");
        mCharacterKeyData.add("T");
        mCharacterKeyData.add("U");
        mCharacterKeyData.add("V");
        mCharacterKeyData.add("W");
        mCharacterKeyData.add("X");
        mCharacterKeyData.add("Y");
        mCharacterKeyData.add("Z");
        mCharacterKeyData.add("-");
        mCharacterKeyData.add("'");
    }

    private void initNumberKeyPads() {
        mNumberKeyData = new ArrayList<>();
        mNumberKeyData.add("1");
        mNumberKeyData.add("2");
        mNumberKeyData.add("3");
        mNumberKeyData.add("&");
        mNumberKeyData.add("#");
        mNumberKeyData.add("(");
        mNumberKeyData.add(")");
        mNumberKeyData.add("4");
        mNumberKeyData.add("5");
        mNumberKeyData.add("6");
        mNumberKeyData.add("@");
        mNumberKeyData.add("!");
        mNumberKeyData.add("?");
        mNumberKeyData.add(":");
        mNumberKeyData.add("7");
        mNumberKeyData.add("8");
        mNumberKeyData.add("9");
        mNumberKeyData.add("0");
        mNumberKeyData.add(".");
        mNumberKeyData.add("_");
        mNumberKeyData.add("\"");
    }

    private void initAdapter(Context context) {
        mAdapter = new KeyboardsAdapter();
        mRvKeys.setAdapter(mAdapter);
        mAdapter.setKeyData(mCharacterKeyData);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 7);
        mRvKeys.setLayoutManager(gridLayoutManager);
    }

    public void reset() {
        if (mDisplayedText.length() > 0) {
            mDisplayedText.delete(0, mDisplayedText.length());
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_UP) {
            if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                View selectedView = findFocus();
                switch (selectedView.getId()) {
                    case R.id.tv_keyDelete:
                        if (mDisplayedText.length() > 0) {
                            mDisplayedText.deleteCharAt(mContentLen);
                            mContentLen = mDisplayedText.length() - 1;
                            EventBus.getDefault().post(new EventDisplayText(mDisplayedText));
                        }
                        break;
                    case R.id.tv_keyClear:
                        if (mDisplayedText.length() > 0) {
                            mDisplayedText.delete(0, mDisplayedText.length());
                            mContentLen = 0;
                            EventBus.getDefault().post(new EventDisplayText(mDisplayedText));
                        }
                        break;
                    case R.id.tv_keyNumbers:
                        if (isChangeToNumPads) {
                            isChangeToNumPads = false;
                            mTvKeyNumbers.setText(getContext().getString(R.string.number_pads));
                            mAdapter.setKeyData(mCharacterKeyData);
                        } else {
                            isChangeToNumPads = true;
                            mTvKeyNumbers.setText(getContext().getString(R.string.character_pads));
                            mAdapter.setKeyData(mNumberKeyData);
                        }
                        break;
                    case R.id.tv_space:
                        mDisplayedText.append(" ");
                        EventBus.getDefault().post(new EventDisplayText(mDisplayedText));
                        break;
                    case R.id.tv_search:
                        if (mDisplayedText.length() > 0) {
                            EventBus.getDefault().post(new EventSearching(mDisplayedText.toString()));
                        }
                        Toast.makeText(getContext(), "PRESS SEARCH", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        mDisplayedText.append(getKeyPadData(findFocus()));
                        mContentLen = mDisplayedText.length() - 1;
                        EventBus.getDefault().post(new EventDisplayText(mDisplayedText));
                        break;
                }
            }
        }

        return super.dispatchKeyEvent(event);
    }


    private String getKeyPadData(View view) {
        TextView textView = view.findViewById(R.id.tv_keyPad);
        return textView.getText().toString();
    }



}
