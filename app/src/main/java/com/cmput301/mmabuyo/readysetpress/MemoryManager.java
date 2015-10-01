package com.cmput301.mmabuyo.readysetpress;

import android.content.Context;

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

    protected void saveFile(Context context, ArrayList<Long> stats, String filename) {
        try {
            FileOutputStream fos = context.openFileOutput(filename,
                    0);
            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(stats, writer);
            writer.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected ArrayList<Long> loadFile(Context context, ArrayList<Long> stats, String filename) {
        try {
            FileInputStream fis = context.openFileInput(filename);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
            stats = gson.fromJson(in, listType);
            return stats;

        } catch (FileNotFoundException e) {
            stats = new ArrayList<Long>();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return stats;
    }
}
