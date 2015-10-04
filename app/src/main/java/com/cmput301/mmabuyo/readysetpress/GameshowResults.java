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
