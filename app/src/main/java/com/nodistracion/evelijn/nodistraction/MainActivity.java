package com.nodistracion.evelijn.nodistraction;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.nodistracion.evelijn.nodistraction.ListofApps.ListofApps;

public class MainActivity extends AppCompatActivity {
    private int interactionMode;
    private Button YourApps;
    private Button YourBlockLists;
    private ProgressBar Load;
    private TextView Timer;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YourApps = (Button) findViewById((R.id.button));
        YourBlockLists = (Button) findViewById((R.id.yourlists));
        Load = (ProgressBar) findViewById(R.id.loading);
        Load.setVisibility(View.INVISIBLE);

        }





   public void YourAppsClicked(View view) {
       YourApps.setVisibility(View.INVISIBLE);
       Load.setVisibility(View.VISIBLE);

      {Intent intent=new Intent(this,
               ListofApps.class);
       startActivity(intent);}
    }

    public void YourBlockLists(View view) {
        {Intent intent=new Intent(this,
                ItemsActivity.class);
            startActivity(intent);}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.yourappsbutton:
            {Intent intent=new Intent(this,
                    ListofApps.class);
                startActivity(intent);}
                return true;
            case R.id.settimebutton:
            {Intent intent=new Intent(this,
                    TimerActivity.class);
                startActivity(intent);}
                return true;
            /*case R.id.action_settings:
            {Intent intent=new Intent(this,
                    Settings.class);
                startActivity(intent);}
                return true;*/
            case R.id.homebutton:
            {Intent intent=new Intent(this,
                    MainActivity.class);
                startActivity(intent);}
                return true;
            default:
                return super.onOptionsItemSelected(item);
    }

}}

