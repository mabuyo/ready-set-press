package com.cmput301.mmabuyo.readysetpress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * Created by mmabuyo on 2015-10-01.
 */
public class StatsCalculator {
    public StatsCalculator() {

    }

    protected Long getFastestTime(ArrayList<Long> reactionTimes) {
        return Collections.max(reactionTimes);
    }

    protected long getSlowestTime(ArrayList<Long> reactionTimes) {
        return Collections.min(reactionTimes);

    }

    protected long getAverageTime(ArrayList<Long> reactionTimes) {
        if (reactionTimes.size() == 0) {
            throw new NoSuchElementException();
        } else {
            long sum = 0;
            for (Long time : reactionTimes) {
                sum += time;
            }
            return sum / reactionTimes.size();
        }
    }
}
