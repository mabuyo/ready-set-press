package com.cmput301.mmabuyo.readysetpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MultiplayerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplayer_mode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_multiplayer, menu);
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

    public void switchTo2PMode(View view) {
        AlertDialog.Builder multiplayerInstructionsDialog = new AlertDialog.Builder(this);
        multiplayerInstructionsDialog.setTitle("Multiplayer Gameshow Instructions");
        multiplayerInstructionsDialog.setMessage("The host will dismiss this message. The first player to click their button wins!");

        multiplayerInstructionsDialog.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent switchToTwoPlayerActivity = new Intent(MultiplayerActivity.this, TwoPlayerActivity.class);
                startActivity(switchToTwoPlayerActivity);
            }
        });

        AlertDialog alertDialog = multiplayerInstructionsDialog.create();
        alertDialog.show();

    }

}
