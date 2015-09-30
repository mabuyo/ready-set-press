package com.cmput301.mmabuyo.readysetpress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by mmabuyo on 2015-09-29.
 */
public class SingleStatsActivity extends AppCompatActivity {
    private static final String SINGLESTATS_FILENAME = "singlestats.sav";
    protected ArrayList<Long> singleStats;
    private ArrayAdapter<Long> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_singlestats);
    }


    @Override
    protected void onStart() {
        super.onStart();
//        loadFromFile(singleStats);
//        if (singleStats == null) {
//            throw new RuntimeException();
//        }
    }

    private void loadFromFile(ArrayList<Long> stats) {
        try {
            FileInputStream fis = openFileInput(SINGLESTATS_FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Following line based on https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html retrieved 2015-09-21
            Type listType = new TypeToken<ArrayList<Long>>() {}.getType();
            stats = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            stats = new ArrayList<Long>();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
