package com.cmput301.mmabuyo.readysetpress;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import java.util.NoSuchElementException;

/**
 * Created by mmabuyo on 2015-09-29.
 */
public class SingleStatsActivity extends AppCompatActivity {
    protected TextView singleOverallStatsList;
    protected TextView singleLast10StatsList;
    protected TextView singleLast100StatsList;
    protected ReactionTime reactionTime;
    protected MemoryManager memoryManager = new MemoryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_singlestats);
        singleOverallStatsList = (TextView) findViewById(R.id.singleOverallStatsList);
        singleLast10StatsList = (TextView) findViewById(R.id.singleLast10StatsList);
        singleLast100StatsList = (TextView) findViewById(R.id.singleLast100StatsList);

        // if file doesn't exist, then single stats is an empty array! it will throw a NoSuchElementException
        reactionTime = memoryManager.loadTrainingResults(this, memoryManager.getSingleStatsFilename());

        try {
            long fastest = reactionTime.getStats().getFastestTime(reactionTime.getReactionTimes());
            long slowest = reactionTime.getStats().getSlowestTime(reactionTime.getReactionTimes());
            long average = reactionTime.getStats().getAverageTime(reactionTime.getReactionTimes());
            //long median = reactionTime.getOverallMedianTime();

            String overallMessage = "ALL\n" +
                    "The fastest reaction time is: " + String.valueOf(fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(average) + '\n';
            singleOverallStatsList.setText(overallMessage);


            long last10fastest = reactionTime.getStats().getFastestTime(reactionTime.getTenRecentReactionTimes());
            long last10slowest = reactionTime.getStats().getSlowestTime(reactionTime.getTenRecentReactionTimes());
            long last10average = reactionTime.getStats().getAverageTime(reactionTime.getTenRecentReactionTimes());

            String last10Message = "LAST TEN\n" +
                    "The fastest reaction time is: " + String.valueOf(last10fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(last10slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(last10average) + '\n';
            singleLast10StatsList.setText(last10Message);

            long last100fastest = reactionTime.getStats().getFastestTime(reactionTime.getHundredRecentReactionTimes());
            long last100slowest = reactionTime.getStats().getSlowestTime(reactionTime.getHundredRecentReactionTimes());
            long last100average = reactionTime.getStats().getAverageTime(reactionTime.getHundredRecentReactionTimes());

            String last100Message = "LAST ONE HUNDRED\n" +
                    "The fastest reaction time is: " + String.valueOf(last100fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(last100slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(last100average) + '\n';
            singleLast100StatsList.setText(last100Message);

        } catch (NoSuchElementException e){
            String errorMessage = "No statistics found. Please play the games and come back to this page!";
            singleOverallStatsList.setText(errorMessage);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();



    }

    public void clearSingleStatistics(View view) {
        // delete the file or resave something empty?
        reactionTime.clearReactionTimes();
        memoryManager.saveTrainingResults(this, reactionTime);
        finish();
    }

    public void sendEmail(View view) {
        String[] addresses = {"mabuyo@ualberta.ca"};
        String subject = "Statistics";
        composeEmail(addresses,subject);
    }

    // http://developer.android.com/guide/components/intents-common.html#Email
    public void composeEmail(String[] addresses, String subject) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
