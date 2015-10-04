/*
Copyright 2015 Michelle Mabuyo

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 */

package com.cmput301.mmabuyo.readysetpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void singleModeInstructions(View view) {
        AlertDialog.Builder singleModeInstructionsDialog = new AlertDialog.Builder(this);
        singleModeInstructionsDialog.setTitle("Single Mode Training Instructions");
        singleModeInstructionsDialog.setMessage("Click the button when instructed to. Your reaction time will be recorded.");

        singleModeInstructionsDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent switchToTrainingMode = new Intent(MainActivity.this, SingleModeTrainingActivity.class);
                startActivity(switchToTrainingMode);
            }
        });

        AlertDialog alertDialog = singleModeInstructionsDialog.create();
        alertDialog.show();
    }

    public void showStatisticsActivity(View view) {
        Intent switchToStatisticsActivity = new Intent(MainActivity.this, ShowStatsActivity.class);
        startActivity(switchToStatisticsActivity);
    }

    public void showMultiplayerActivity(View view) {
        Intent switchToMultiplayerActivity = new Intent(MainActivity.this, MultiplayerActivity.class);
        startActivity(switchToMultiplayerActivity);
    }
}