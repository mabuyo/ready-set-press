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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    protected long getMedianTime(ArrayList<Long> reactionTimes) {
        Long[] rtimes;
        // toArray: http://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html
        rtimes = reactionTimes.toArray(new Long[reactionTimes.size()]);
        Arrays.sort(rtimes);
        long median;

        // even size, get average of two middle values
        if (rtimes.length % 2 == 0) {
            median = (rtimes[rtimes.length/2] + rtimes[rtimes.length/2 - 1])/2;
        } else {    // odd size, just get middle value
            median = rtimes[rtimes.length/2];
        }
        return median;
    }
}
