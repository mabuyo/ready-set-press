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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class TwoPlayerActivity extends AppCompatActivity {
    protected Player playerOne;
    protected Player playerTwo;
    protected MemoryManager memoryManager = new MemoryManager();
    protected GameshowResults gameResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoplayer_mode);

        // initiate players
        playerOne = new Player(1);
        playerTwo = new Player(2);

        // initiate game results, make sure t load
        gameResults = memoryManager.loadGameshowResults(TwoPlayerActivity.this, memoryManager.getMultiStatsFilename());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_two_player, menu);
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

    public void someoneClicked(View view) {
        int pclicked = 0;
        // find out who, increment their click and save
        switch (view.getId()) {
            case R.id.P1button2P:
                playerOne.addClick();
                gameResults.addClick(playerOne, 2);
                memoryManager.saveGameshowResults(TwoPlayerActivity.this, gameResults);
                pclicked = 1;
                break;
            case R.id.P2button2P:
                playerTwo.addClick();
                gameResults.addClick(playerTwo, 2);
                memoryManager.saveGameshowResults(TwoPlayerActivity.this, gameResults);
                pclicked = 2;
                break;
            default:
                Toast.makeText(this, "Something has gone wrong.", Toast.LENGTH_SHORT).show();
        }

        AlertDialog.Builder playerClickedDialog = new AlertDialog.Builder(this);
        playerClickedDialog.setTitle("Next round!");
        String message = "Player " + pclicked  + " pressed the button first!";
        playerClickedDialog.setMessage(message);

        playerClickedDialog.setPositiveButton("Start again.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {

            }
        });

        AlertDialog alertDialog = playerClickedDialog.create();
        alertDialog.show();
    }
}
