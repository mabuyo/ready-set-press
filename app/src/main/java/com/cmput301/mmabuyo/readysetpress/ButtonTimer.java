package com.cmput301.mmabuyo.readysetpress;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * Created by mmabuyo on 2015-09-28.
 */
public class ButtonTimer {
    private long startTime;
    private long endTime;
    private long timeElapsed;
    private boolean clickable;

    public ButtonTimer() {
        this.clickable = Boolean.FALSE;
    }

    public ButtonTimer(long startTime) {
        this.startTime = startTime;
    }
    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public long getTimeElapsed() {
        return this.timeElapsed = getEndTime() - getStartTime();
    }

    public boolean isClickable() {
        return this.clickable;
    }

    public void setClickable(boolean state) {
        this.clickable = state;
    }

    public void reset() {
        this.startTime = System.currentTimeMillis();
        this.endTime = 0;
        this.clickable = false;
        this.timeElapsed = 0;
    }

}
