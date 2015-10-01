package com.cmput301.mmabuyo.readysetpress;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.NoSuchElementException;

public class MultiplayerStatsActivity extends AppCompatActivity {
    protected GameshowResults gameshowResults;
    protected TextView gameshowResultsList;
    protected MemoryManager memoryManager = new MemoryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_multistats);

        gameshowResultsList = (TextView) findViewById(R.id.twoPlayerStatsList);
        gameshowResults = memoryManager.loadGameshowResults(MultiplayerStatsActivity.this, memoryManager.getMultiStatsFilename());

        gameshowResultsList.setText("P1 clicks: " + gameshowResults.getTwoPlayerResults()[0]);

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
}
