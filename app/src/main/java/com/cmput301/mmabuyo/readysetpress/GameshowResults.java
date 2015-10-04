package com.cmput301.mmabuyo.readysetpress;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by mmabuyo on 2015-10-01.
 */
public class GameshowResults {
    protected int[] twoPlayerResults;
    protected int[] threePlayerResults;
    protected int[] fourPlayerResults;
    protected MemoryManager memoryManager = new MemoryManager();

    public GameshowResults() {
        twoPlayerResults = new int[2];
        threePlayerResults = new int[3];
        fourPlayerResults = new int[4];

    }

    public int[] getTwoPlayerResults() {
        return twoPlayerResults;
    }

    public void setTwoPlayerResults(int[] twoPlayerResults) {
        this.twoPlayerResults = twoPlayerResults;
    }

    public int[] getThreePlayerResults() {
        return threePlayerResults;
    }

    public void setThreePlayerResults(int[] threePlayerResults) {
        this.threePlayerResults = threePlayerResults;
    }

    public int[] getFourPlayerResults() {
        return fourPlayerResults;
    }

    public void setFourPlayerResults(int[] fourPlayerResults) {
        this.fourPlayerResults = fourPlayerResults;
    }

    protected void addClick(Player player, int numberOfPlayers) {
        switch(numberOfPlayers) {
            case 2:
                getTwoPlayerResults()[player.getPid()-1]++;
                break;
            case 3:
                getThreePlayerResults()[player.getPid()-1]++;
                break;
            case 4:
                getFourPlayerResults()[player.getPid()-1]++;
                break;
        }
    }

    protected void clear() {
        this.setTwoPlayerResults(new int[2]);
        this.setThreePlayerResults(new int[3]);
        this.setFourPlayerResults(new int[4]);
    }
}
