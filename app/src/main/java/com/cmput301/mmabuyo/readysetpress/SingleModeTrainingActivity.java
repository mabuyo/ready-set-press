package com.cmput301.mmabuyo.readysetpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class SingleModeTrainingActivity extends AppCompatActivity {
    private Handler clickPromptHandler = new Handler();
    protected ReactionTime trainingReactionTime;
    protected Button trainingButton;
    protected boolean clickedTooFast = false;
    MemoryManager memoryManager = new MemoryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_mode_button);
        trainingButton = (Button) findViewById(R.id.trainingButton);

        // reaction timer
        trainingReactionTime = memoryManager.loadTrainingResults(SingleModeTrainingActivity.this, memoryManager.getSingleStatsFilename());

        clickPromptActivity();

    }

    public void clickPromptActivity() {
        clickPromptHandler.postDelayed(new Runnable() {
            public void run() {
                if (!clickedTooFast) {
                    // shows the prompt to click the button, starts the button timer!
                    trainingButton.setText("Click me!");
                    trainingReactionTime.setClickable(true);
                    trainingReactionTime.setStartTime(System.currentTimeMillis());
                }
                clickedTooFast = false;
            }
        }, 2000);
    }

    public void trainingButtonClicked(View view) {
        // click button prompted to click!
        if (trainingReactionTime.isClickable()) {
            // record time
            trainingReactionTime.setEndTime(System.currentTimeMillis());
            trainingReactionTime.addReactionTime(trainingReactionTime.getReactionTime());
            trainingReactionTime.memoryManager.saveTrainingResults(this, trainingReactionTime);

            // display elapsed time
            AlertDialog.Builder reactionTimeDialog = new AlertDialog.Builder(this);
            reactionTimeDialog.setTitle("Reaction Time");
            String message = "You clicked in " + String.valueOf(trainingReactionTime.getReactionTime()) + " ms!";
            reactionTimeDialog.setMessage(message);

            reactionTimeDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // reset button
                    trainingReactionTime.reset();
                    trainingButton.setText("Wait...");
                    clickPromptActivity();
                }
            });

            AlertDialog alertDialog = reactionTimeDialog.create();
            alertDialog.show();

        // you clicked without being prompted
        } else {
            AlertDialog.Builder notClickableDialog = new AlertDialog.Builder(this);
            notClickableDialog.setTitle("WAIT!");
            notClickableDialog.setMessage("You must wait until the app prompts you to click the button.");

            notClickableDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    trainingReactionTime.reset();
                    trainingButton.setText("Wait...");
                    clickedTooFast = false;
                    clickPromptActivity();
                }
            });

            AlertDialog alertDialog = notClickableDialog.create();
            alertDialog.show();

        }

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
