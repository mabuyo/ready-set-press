package com.cmput301.mmabuyo.readysetpress;


/**
 * Created by mmabuyo on 2015-09-28.
 */
public class ButtonTimer {
    private long startTime;
    private long endTime;
    private long timeElapsed;
    private boolean clicked;

    public ButtonTimer() {
        this.clicked = Boolean.FALSE;
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

    public boolean getClicked() {
        return this.clicked;
    }



}
