package com.cmput301.mmabuyo.readysetpress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Created by mmabuyo on 2015-09-29.
 */
public class SingleStatsActivity extends AppCompatActivity {
    private static final String SINGLESTATS_FILENAME = "singlestats.sav";
    protected ArrayList<Long> singleStats;
    protected TextView singleOverallStatsList;
    protected TextView singleLast10StatsList;
    ButtonTimer singleModeButton;
    protected MemoryManager memoryManager = new MemoryManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_singlestats);
        singleOverallStatsList = (TextView) findViewById(R.id.singleOverallStatsList);
        singleLast10StatsList = (TextView) findViewById(R.id.singleLast10StatsList);
        loadFromFile();

        // if file doesn't exist, then single stats is an empty array! it will throw a NoSuchElementException

        singleModeButton = new ButtonTimer(singleStats);

        try {
            long fastest = singleModeButton.getOverallFastestTime();
            long slowest = singleModeButton.getOverallSlowestTime();
            long average = singleModeButton.getOverallAverageTime();
            //long median = singleModeButton.getOverallMedianTime();

            String overallMessage = "ALL\n" +
                    "The fastest reaction time is: " + String.valueOf(fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(average) + '\n';
            singleOverallStatsList.setText(overallMessage);


            long last10fastest = singleModeButton.getLastTenFastestTime();
            long last10slowest = singleModeButton.getLastTenSlowestTime();
            long last10average = singleModeButton.getLastTenAverageTime();

            String last10Message = "LAST TEN\n" +
                    "The fastest reaction time is: " + String.valueOf(last10fastest) + '\n' +
                    "The slowest reaction time is: " + String.valueOf(last10slowest) + '\n' +
                    "The average reaction time is: " + String.valueOf(last10average) + '\n';
            singleLast10StatsList.setText(last10Message);

        } catch (NoSuchElementException e){
            String errorMessage = "No statistics found. Please play the games and come back to this page!";
            singleOverallStatsList.setText(errorMessage);
        }

    }


    @Override
    protected void onStart() {
        super.onStart();



    }

    private void loadFromFile() {
        this.singleStats = memoryManager.loadFile(this, this.singleStats, memoryManager.getSingleStatsFilename());
//        try {
//            FileInputStream fis = openFileInput(SINGLESTATS_FILENAME);
//            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
//            Gson gson = new Gson();
//            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
//            Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
//            singleStats = gson.fromJson(in, listType);
//
//        } catch (FileNotFoundException e) {
//            singleStats = new ArrayList<Long>();x
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
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
            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void clearSingleStatistics(View view) {
        // delete the file or resave something empty?
        ArrayList<Long> temp = new ArrayList<Long>();
        saveInFile(temp);
    }



}
