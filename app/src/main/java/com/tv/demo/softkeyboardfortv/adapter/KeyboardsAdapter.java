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


public class KeyboardsAdapter extends RecyclerView.Adapter<KeyboardsAdapter.KeyboardsViewHolder> {

    private List<String> keyPads;

    public KeyboardsAdapter() {
        keyPads = new ArrayList<>();
    }

    public void setKeyData(List<String> keyPads) {
        this.keyPads = keyPads;
        notifyDataSetChanged();
    }

    @Override
    public KeyboardsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_pad_item, parent, false);
        return new KeyboardsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KeyboardsViewHolder holder, int position) {
        holder.bindKeyData(getDataAt(position));
    }

    @Override
    public int getItemCount() {
        return keyPads.size();
    }

    private String getDataAt(int position) {
        return keyPads.get(position);
    }


    class KeyboardsViewHolder extends RecyclerView.ViewHolder {

        private TextView mTvKeyPad;

        public KeyboardsViewHolder(View itemView) {
            super(itemView);
            mTvKeyPad = itemView.findViewById(R.id.tv_keyPad);
            itemView.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (hasFocus) {
                        ViewCompat.setBackground(v, ContextCompat.getDrawable(v.getContext(), R.drawable.pad_background_focus_light_drawable));
                        mTvKeyPad.setTextColor(Color.WHITE);
                    } else {
                        ViewCompat.setBackground(v, null);
                        mTvKeyPad.setTextColor(Color.BLACK);
                    }
                }
            });
        }

        public void bindKeyData(String data) {
            mTvKeyPad.setText(data);
        }
    }
}
