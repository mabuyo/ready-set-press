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

import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class SingleModeTrainingActivity extends AppCompatActivity {
    private Handler clickPromptHandler = new Handler();
    protected ButtonTimer trainingButtonTimer = new ButtonTimer();
    Button trainingButton;
    protected boolean clickedTooFast = false;
    private static final String SINGLESTATS_FILENAME = "singlestats.sav";

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
        // click button prompted to click!
        if (trainingButtonTimer.isClickable()) {
            // record time
            trainingButtonTimer.setEndTime(System.currentTimeMillis());
            long elapsedTime = trainingButtonTimer.getTimeElapsed();

            // TODO: store and save in file
            trainingButtonTimer.addReactionTime(elapsedTime);
            saveInFile(trainingButtonTimer.getReactionTimes());

            // display elapsed time
            AlertDialog.Builder reactionTimeDialog = new AlertDialog.Builder(this);
            reactionTimeDialog.setTitle("Reaction Time");
            String message = "You clicked in " + String.valueOf(elapsedTime) + " ms!";
            reactionTimeDialog.setMessage(message);

            reactionTimeDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {
                    // reset button
                    trainingButtonTimer.reset();
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
                    trainingButtonTimer.reset();
                    trainingButton.setText("Wait...");
                    clickedTooFast = false;
                    clickPromptActivity();
                }
            });

            AlertDialog alertDialog = notClickableDialog.create();
            alertDialog.show();

        }

    }

    private void saveInFile(ArrayList<Long> stats) {

        try {
            FileOutputStream fos = openFileOutput(SINGLESTATS_FILENAME,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(stats, writer);
            writer.flush();
            fos.close();
            Toast.makeText(this, "Saved!", Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
