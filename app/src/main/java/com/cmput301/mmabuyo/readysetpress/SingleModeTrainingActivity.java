package com.cmput301.mmabuyo.readysetpress;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SingleModeTrainingActivity extends AppCompatActivity {
    private Handler clickPromptHandler = new Handler();
    protected ButtonTimer trainingButtonTimer = new ButtonTimer();
    Button trainingButton;
    protected boolean clickedTooFast = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_mode_button);
        trainingButton = (Button) findViewById(R.id.trainingButton);
        Context context = getApplicationContext();
        clickPromptActivity();

    }

    public void clickPromptActivity() {
        clickPromptHandler.postDelayed(new Runnable() {
            public void run() {
                if (!clickedTooFast) {
                    // shows the prompt to click the button, starts the button timer!
                    trainingButton.setText("Click me!");
                    trainingButtonTimer.setClickable(true);
                    trainingButtonTimer.setStartTime(System.currentTimeMillis());
                }
                clickedTooFast = false;
            }
        }, 2000);
    }

    public void trainingButtonClicked(View view) {
        if (trainingButtonTimer.isClickable()) {
            // record time
            trainingButtonTimer.setEndTime(System.currentTimeMillis());
            long elapsedTime = trainingButtonTimer.getTimeElapsed();

            // TODO: store and save in file


            // display elapsed time
            AlertDialog.Builder reactionTimeDialog = new AlertDialog.Builder(this);
            reactionTimeDialog.setTitle("Reaction Time");
            String message = "You clicked in " + String.valueOf(elapsedTime) + " ms!";
            reactionTimeDialog.setMessage(message);

            reactionTimeDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            AlertDialog alertDialog = reactionTimeDialog.create();
            alertDialog.show();

            // reset button
            trainingButtonTimer.reset();
            trainingButton.setText("Wait...");

        } else {
            AlertDialog.Builder notClickableDialog = new AlertDialog.Builder(this);
            notClickableDialog.setTitle("WAIT!");
            notClickableDialog.setMessage("You must wait until the app prompts you to click the button.");

            notClickableDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            AlertDialog alertDialog = notClickableDialog.create();
            alertDialog.show();


            trainingButtonTimer.reset();
            clickedTooFast = true;
        }

        clickPromptActivity();
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