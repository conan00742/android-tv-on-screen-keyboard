package com.tv.demo.softkeyboardfortv.event;

import android.view.View;

/**
 * Created by Krot on 6/13/18.
 */

public class EventSendFocusedView {

    private View currentFocusedView;
    private View nextFocusedView;

    public EventSendFocusedView(View currentFocusedView, View nextFocusedView) {
        this.currentFocusedView = currentFocusedView;
        this.nextFocusedView = nextFocusedView;
    }

    public View getCurrentFocusedView() {
        return currentFocusedView;
    }

    public View getNextFocusedView() {
        return nextFocusedView;
    }
}
