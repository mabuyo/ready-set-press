package com.cmput301.mmabuyo.readysetpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MultiplayerStatsActivity extends AppCompatActivity {
    protected GameshowResults gameshowResults;
    protected TextView twoPResultsList;
    protected TextView threePResultsList;
    protected TextView fourPResultsList;
    protected MemoryManager memoryManager = new MemoryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_multistats);

        twoPResultsList = (TextView) findViewById(R.id.twoPlayerStatsList);
        threePResultsList = (TextView) findViewById(R.id.threePlayerStatsList);
        fourPResultsList = (TextView) findViewById(R.id.fourPlayerStatsList);

        gameshowResults = memoryManager.loadGameshowResults(MultiplayerStatsActivity.this, memoryManager.getMultiStatsFilename());

        String twoPlayerList = "P1 clicks: " + gameshowResults.getTwoPlayerResults()[0] + '\n' +
                "P2 clicks: " + gameshowResults.getTwoPlayerResults()[1] + '\n';
        twoPResultsList.setText(twoPlayerList);

        String threePlayerList = "P1 clicks: " + gameshowResults.getThreePlayerResults()[0] + '\n' +
                "P2 clicks: " + gameshowResults.getThreePlayerResults()[1] + '\n' +
                "P3 clicks: " + gameshowResults.getThreePlayerResults()[2] + '\n';
        threePResultsList.setText(threePlayerList);

        String fourPlayerList = "P1 clicks: " + gameshowResults.getFourPlayerResults()[0] + '\n' +
                "P2 clicks: " + gameshowResults.getFourPlayerResults()[1] + '\n' +
                "P3 clicks: " + gameshowResults.getFourPlayerResults()[2] + '\n' +
                "P4 clicks: " + gameshowResults.getFourPlayerResults()[3] + '\n';
        fourPResultsList.setText(fourPlayerList);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer_stats, menu);
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

    public void clearMultiplayerStatistics(View view) {
        // delete the file or resave something empty?
        gameshowResults.clear();
        memoryManager.saveGameshowResults(this, gameshowResults);
        finish();
    }
}
