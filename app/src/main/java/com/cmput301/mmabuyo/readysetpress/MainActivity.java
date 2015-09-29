package com.cmput301.mmabuyo.readysetpress;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    // http://www.tutorialspoint.com/android/android_alert_dialoges.htm
    public void singleModeInstructions(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Single Mode Training Instructions");
        alertDialogBuilder.setMessage("Click the button when instructed to. Your reaction time will be recorded.");

        alertDialogBuilder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface arg0, int arg1) {
                Intent switchToTrainingMode = new Intent(MainActivity.this, SingleModeTrainingActivity.class);
                startActivity(switchToTrainingMode);

               // Toast.makeText(MainActivity.this,"You clicked yes button", Toast.LENGTH_LONG).show();
            }
        });

//        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                finish();
//            }
//        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}