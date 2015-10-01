package com.cmput301.mmabuyo.readysetpress;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.twoplayer_mode);

        // initiate players
        playerOne = new Player(1);
        playerTwo = new Player(2);

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
        // find out who, increment their click and save
        switch (view.getId()) {
            case R.id.P1button2P:
                playerOne.addClick();
                Toast.makeText(this, "Player 1 clicked first!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.P2button2P:
                playerTwo.addClick();
                Toast.makeText(this, "Player 2 clicked first!", Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(this, "Something's gone wrong.", Toast.LENGTH_SHORT).show();
        }
    }


}
