/*
Copyright 2015 Michelle Mabuyo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.cmput301.mmabuyo.readysetpress;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Long> getHundredRecentReactionTimes() {
        ArrayList<Long> rtimes = this.getReactionTimes();
        if (rtimes.size() == 0) {
            return rtimes;
        }
        if (rtimes.size() < 100) {
            return rtimes;
        } else {
            ArrayList<Long> recentTimes = new ArrayList<Long>();
            List<Long> last100 = rtimes.subList(rtimes.size()-100, rtimes.size());
            recentTimes.addAll(0, last100);
            return recentTimes;
        }
    }

}
