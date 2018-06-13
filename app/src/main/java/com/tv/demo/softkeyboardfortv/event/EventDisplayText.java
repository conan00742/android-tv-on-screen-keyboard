package com.tv.demo.softkeyboardfortv.event;

/**
 * Created by Krot on 6/11/18.
 */

public class EventDisplayText {

    private StringBuilder mDisplayedText;

    public EventDisplayText(StringBuilder mDisplayedText) {
        this.mDisplayedText = mDisplayedText;
    }

    public StringBuilder getDisplayedText() {
        return mDisplayedText;
    }
}
