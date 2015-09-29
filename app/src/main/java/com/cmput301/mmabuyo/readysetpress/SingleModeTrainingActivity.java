package com.cmput301.mmabuyo.readysetpress;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SingleModeTrainingActivity extends AppCompatActivity {
    private Handler clickPromptHandler = new Handler();
    protected ButtonTimer trainingButton = new ButtonTimer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_mode_button);

        clickPromptHandler.postDelayed(new Runnable() {
            public void run() {
                displayClickPrompt();
            }
        }, 2000);
    }

    private void displayClickPrompt() {
        // shows the prompt to click the button, starts the button timer!
        Toast.makeText(this, "Click me now!", Toast.LENGTH_LONG).show();
        trainingButton.setStartTime(System.currentTimeMillis());
    }

    private void trainingButtonClicked() {
        // record time
        trainingButton.setEndTime(System.currentTimeMillis());
        long elapsedTime = trainingButton.getTimeElapsed();

        // store    
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_mode_training, menu);
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
