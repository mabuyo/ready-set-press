package com.cmput301.mmabuyo.readysetpress;


import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by mmabuyo on 2015-09-28.
 */
public class ReactionTime {
    private long startTime;
    private long endTime;
    private long reactionTime;
    private boolean clickable;
    protected ArrayList<Long> reactionTimes = new ArrayList<Long>();
    protected StatsCalculator statsCalculator = new StatsCalculator();
    protected MemoryManager memoryManager = new MemoryManager();

    public ReactionTime() {
        this.clickable = Boolean.FALSE;
    }

    public ReactionTime(long startTime) {
        this.clickable = Boolean.FALSE;
        this.startTime = startTime;
    }

    public ReactionTime(ArrayList<Long> reactionTimes) {
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

    public long getReactionTime() {
        return this.reactionTime = getEndTime() - getStartTime();
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
        this.reactionTime = 0;
    }

    public ArrayList<Long> getReactionTimes() {
        return this.reactionTimes;
    }

    public void setReactionTimes(ArrayList<Long> reactionTimes) {
        this.reactionTimes = reactionTimes;
    }

    public void addReactionTime(long time) {
        this.reactionTimes.add(time);
    }

    public void clearReactionTimes() {
        reactionTimes.clear();
    }

    public StatsCalculator getStats() {
        return this.statsCalculator;
    }

    public ArrayList<Long> getTenRecentReactionTimes() {
        ArrayList<Long> rtimes = this.getReactionTimes();
        if (rtimes.size() == 0) {
            return rtimes;
        }
        if (rtimes.size() < 10) {
            return rtimes;
        } else {
            ArrayList<Long> recentTimes = new ArrayList<Long>();
            List<Long> last10 = rtimes.subList(rtimes.size()-10, rtimes.size());
            recentTimes.addAll(0, last10);
            return recentTimes;
        }
    }
}
