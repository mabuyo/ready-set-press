package com.cmput301.mmabuyo.readysetpress;

import android.content.Context;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by mmabuyo on 2015-09-30.
 */
public class MemoryManager {
    private static final String SINGLESTATS_FILENAME = "singlestats.sav";
    private static final String MULTISTATS_FILENAME = "multistats.sav";
    public MemoryManager() {

    }

    protected String getSingleStatsFilename() {
        return this.SINGLESTATS_FILENAME;
    }

    protected String getMultiStatsFilename() {
        return this.MULTISTATS_FILENAME;
    }

    // The methods below were edited from UAlberta CMPUT 301, CMPUT 301 Lab Materials
    protected ReactionTime loadTrainingResults(Context context, String filename) {
        ReactionTime results;
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            results = gson.fromJson(in, ReactionTime.class);
            return results;
        } catch (FileNotFoundException e) {
            generateTrainingResults(context);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ReactionTime();
    }

    protected void generateTrainingResults(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(getSingleStatsFilename(),
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            ReactionTime results = new ReactionTime();    // only different line in generate and save
            gson.toJson(results, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void saveTrainingResults(Context context, ReactionTime reactionTime) {
        try {
            FileOutputStream fos = context.openFileOutput(getSingleStatsFilename(),
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(reactionTime, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected GameshowResults loadGameshowResults(Context context, String filename) {
        GameshowResults results = new GameshowResults();
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            results = gson.fromJson(in, GameshowResults.class);
            return results;
        } catch (FileNotFoundException e) {
            generateGameshowResults(context);
            loadGameshowResults(context, getMultiStatsFilename());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    protected void generateGameshowResults(Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(getMultiStatsFilename(),
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            GameshowResults results = new GameshowResults();    // only different line in generate and save
            gson.toJson(results, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected void saveGameshowResults(Context context, GameshowResults results) {
        try {
            FileOutputStream fos = context.openFileOutput(getMultiStatsFilename(),
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(results, writer);
            writer.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
