package com.cmput301.mmabuyo.readysetpress;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mmabuyo on 2015-09-28.
 */
public class ButtonTimer {
    private long startTime;
    private long endTime;
    private long timeElapsed;
    private boolean clickable;
    protected ArrayList<Long> reactionTimes = new ArrayList<Long>();

    public ButtonTimer() {
        this.clickable = Boolean.FALSE;
    }

    public ButtonTimer(long startTime) {
        this.clickable = Boolean.FALSE;
        this.startTime = startTime;
    }

    public ButtonTimer(ArrayList<Long> reactionTimes) {
        this.clickable = Boolean.FALSE;
        this.reactionTimes = reactionTimes;
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

    public ArrayList<Long> getReactionTimes() {
        return this.reactionTimes;
    }

    public void addReactionTime(long time) {
        reactionTimes.add(time);
    }

    public void clearReactionTimes() {
        reactionTimes.clear();
    }

    public long getOverallFastestTime() {
        return Collections.max(this.getReactionTimes());
    }

    public long getOverallSlowestTime() {
        return Collections.min(this.getReactionTimes());
    }

    public long getOverallAverageTime() {
        ArrayList<Long> reactionTimes = this.getReactionTimes();
        long sum = 0;
        for (Long time : reactionTimes) {
            sum += time;
        }
        return sum / reactionTimes.size();
    }

//    public long getOverallMedianTime() {
//        ArrayList<Long> reactionTimes = getReactionTimes();
//
//
//
//    }

    public long getLastTenFastestTime() {
        //Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
        ArrayList<Long> rtimes = getReactionTimes();
        if (rtimes.size() <= 10) {
            return Collections.max(this.getReactionTimes());
        } else {
            return Collections.max(rtimes.subList(rtimes.size() - 11, rtimes.size() - 1));
        }
    }

    public long getLastTenSlowestTime() {
        ArrayList<Long> rtimes = getReactionTimes();
        if (rtimes.size() <= 10) {
            return Collections.min(this.getReactionTimes());
        } else {
            return Collections.min(rtimes.subList(rtimes.size() - 11, rtimes.size() - 1));
        }
    }

    public long getLastTenAverageTime() {
        ArrayList<Long> rtimes = getReactionTimes();
        List<Long> times;
        if (rtimes.size() <= 10) {
            times = this.getReactionTimes();
        } else {
            times = rtimes.subList(rtimes.size()-11,rtimes.size()-1);
        }
        long sum = 0;
        for (Long time : times) {
            sum += time;
        }
        return sum / times.size();
    }

}
