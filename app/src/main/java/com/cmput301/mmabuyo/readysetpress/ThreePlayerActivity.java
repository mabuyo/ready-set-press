package com.cmput301.mmabuyo.readysetpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ThreePlayerActivity extends AppCompatActivity {
    protected Player playerOne;
    protected Player playerTwo;
    protected Player playerThree;
    protected MemoryManager memoryManager = new MemoryManager();
    protected GameshowResults gameResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.threeplayer_mode);

        // initiate players
        playerOne = new Player(1);
        playerTwo = new Player(2);
        playerThree = new Player(3);

        // initiate game results, make sure it loads
        gameResults = memoryManager.loadGameshowResults(ThreePlayerActivity.this, memoryManager.getMultiStatsFilename());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_three_player, menu);
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
            case R.id.P1button3P:
                playerOne.addClick();
                gameResults.addClick(playerOne, 3);
                memoryManager.saveGameshowResults(ThreePlayerActivity.this, gameResults);
                pclicked = 1;
                break;
            case R.id.P2button3P:
                playerTwo.addClick();
                gameResults.addClick(playerTwo, 3);
                memoryManager.saveGameshowResults(ThreePlayerActivity.this, gameResults);
                pclicked = 2;
                break;
            case R.id.P3button3P:
                playerThree.addClick();
                gameResults.addClick(playerThree, 3);
                memoryManager.saveGameshowResults(ThreePlayerActivity.this, gameResults);
                pclicked = 3;
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