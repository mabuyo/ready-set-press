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
 * Created by mmabuyo on 2015-09-30.
 *  * Purpose:
 *      This class is responsible for acting as the users of the app (players of the Gameshow Mode
 *      of the app). It stores playerID and clicks for that user.
 * Design Rationale:
 *      pid is used to determine the player number and update the corresponding index in the
 *      GameshowResults class.
 * Outstanding Issues:
 *      None.
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
