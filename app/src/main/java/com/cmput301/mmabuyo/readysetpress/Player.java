package com.cmput301.mmabuyo.readysetpress;

/**
 * Created by mmabuyo on 2015-09-30.
 */
public class Player {
    protected int pid;
    protected int clicks;

    public Player(int pid) {
        this.pid = pid;
        this.clicks = 0;
    }

    public int getPid() {
        return pid;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }


    public void addClick() {
        this.clicks++;
    }
}
