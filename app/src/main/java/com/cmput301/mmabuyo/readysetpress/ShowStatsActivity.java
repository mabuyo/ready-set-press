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

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.NoSuchElementException;

public class ShowStatsActivity extends AppCompatActivity {
    protected TextView statsList;
    protected ReactionTime reactionTime;
    protected GameshowResults gameshowResults;
    protected MemoryManager memoryManager = new MemoryManager();
    String overallMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stats);

        statsList = (TextView) findViewById(R.id.statsList);

        // if file doesn't exist, then single stats is an empty array! it will throw a NoSuchElementException
        reactionTime = memoryManager.loadTrainingResults(this, memoryManager.getSingleStatsFilename());
        gameshowResults = memoryManager.loadGameshowResults(this, memoryManager.getMultiStatsFilename());

        overallMessage = "";
        overallMessage += "SINGLE TRAINING MODE\n\n";

        try {
            long fastest = reactionTime.getStats().getFastestTime(reactionTime.getReactionTimes());
            long slowest = reactionTime.getStats().getSlowestTime(reactionTime.getReactionTimes());
            long average = reactionTime.getStats().getAverageTime(reactionTime.getReactionTimes());
            long median = reactionTime.getStats().getMedianTime(reactionTime.getReactionTimes());

            overallMessage += "ALL\n" +
                    "The fastest reaction time is: " + String.valueOf(fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(average) + '\n' +
                    "The median reaction time is: " + String.valueOf(median) + "\n\n";

            long last10fastest = reactionTime.getStats().getFastestTime(reactionTime.getTenRecentReactionTimes());
            long last10slowest = reactionTime.getStats().getSlowestTime(reactionTime.getTenRecentReactionTimes());
            long last10average = reactionTime.getStats().getAverageTime(reactionTime.getTenRecentReactionTimes());
            long last10median = reactionTime.getStats().getMedianTime(reactionTime.getTenRecentReactionTimes());

            overallMessage += "LAST TEN\n" +
                    "The fastest reaction time is: " + String.valueOf(last10fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(last10slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(last10average) + '\n' +
                    "The median reaction time is: " + String.valueOf(last10median) + "\n\n";

            long last100fastest = reactionTime.getStats().getFastestTime(reactionTime.getHundredRecentReactionTimes());
            long last100slowest = reactionTime.getStats().getSlowestTime(reactionTime.getHundredRecentReactionTimes());
            long last100average = reactionTime.getStats().getAverageTime(reactionTime.getHundredRecentReactionTimes());
            long last100median = reactionTime.getStats().getMedianTime(reactionTime.getHundredRecentReactionTimes());

            overallMessage += "LAST ONE HUNDRED\n" +
                    "The fastest reaction time is: " + String.valueOf(last100fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(last100slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(last100average) + '\n' +
                    "The median reaction time is: " + String.valueOf(last100median) + "\n\n";


        } catch (NoSuchElementException e){
            String errorMessage = "\nNo statistics found for single mode training.\n\n";
            overallMessage += errorMessage;
        }

        overallMessage += "---------------------------\n";
        overallMessage += "MULTIPLAYER GAMESHOW MODE\n\n";

        String twoPlayerList = "TWO PLAYERS\n" +
                "P1 clicks: " + gameshowResults.getTwoPlayerResults()[0] + '\n' +
                "P2 clicks: " + gameshowResults.getTwoPlayerResults()[1] + "\n\n";

        String threePlayerList = "THREE PLAYERS\n" +
                "P1 clicks: " + gameshowResults.getThreePlayerResults()[0] + '\n' +
                "P2 clicks: " + gameshowResults.getThreePlayerResults()[1] + '\n' +
                "P3 clicks: " + gameshowResults.getThreePlayerResults()[2] + "\n\n";

        String fourPlayerList = "FOUR PLAYERS\n" +
                "P1 clicks: " + gameshowResults.getFourPlayerResults()[0] + '\n' +
                "P2 clicks: " + gameshowResults.getFourPlayerResults()[1] + '\n' +
                "P3 clicks: " + gameshowResults.getFourPlayerResults()[2] + '\n' +
                "P4 clicks: " + gameshowResults.getFourPlayerResults()[3] + "\n\n";

        overallMessage += twoPlayerList + threePlayerList + fourPlayerList;
        statsList.setText(overallMessage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_show_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void clearStatistics(View view) {
        // delete the file or resave something empty?
        reactionTime.clearReactionTimes();
        gameshowResults.clear();
        memoryManager.saveTrainingResults(this, reactionTime);
        memoryManager.saveGameshowResults(this, gameshowResults);
        finish();
    }

    public void sendEmail(View view) {
        String[] addresses = {"mabuyo@ualberta.ca"};
        String subject = "Single Mode Training Statistics";
        composeEmail(addresses, subject);
    }

    // sending email edited from Android Developer Guide, Common Intents:
    // http://developer.android.com/guide/components/intents-common.html#Email
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);

        // get stats as text and insert into body
        String body = overallMessage;
        intent.putExtra(Intent.EXTRA_TEXT, body);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
