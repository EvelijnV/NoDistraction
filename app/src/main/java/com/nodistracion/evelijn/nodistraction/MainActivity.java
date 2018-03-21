package com.nodistracion.evelijn.nodistraction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


import com.nodistracion.evelijn.nodistraction.ListofApps.ListofApps;

public class MainActivity extends AppCompatActivity {
    private int interactionMode;
    private Button YourApps;
    private ProgressBar Load;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        YourApps=(Button)findViewById((R.id.button));
        Load=(ProgressBar)findViewById(R.id.loading);
        Load.setVisibility(View.INVISIBLE);



    }



   /* public void onClickMenu(View view) {
        if(interactionMode==R.id.settimebutton)
        {Intent intent=new Intent(this,
                ItemsActivity.class);
            startActivity(intent);}
        else{
            Intent intent=new Intent(this, ListofApps.class);
            startActivity(intent);
        }
    }*/
   public void YourAppsClicked(View view) {
       YourApps.setVisibility(View.INVISIBLE);
       Load.setVisibility(View.VISIBLE);

      {Intent intent=new Intent(this,
               ListofApps.class);
       startActivity(intent);}
    }

    public void YourBlockLists(View view) {
        YourApps.setVisibility(View.INVISIBLE);
        Load.setVisibility(View.VISIBLE);

        {Intent intent=new Intent(this,
                ListofApps.class);
            startActivity(intent);}
    }


  /* @Override
   public boolean onCreateOptionsMenu(Menu menu) {
       // Inflate the menu; this adds items to the action bar if it is present.
       getMenuInflater().inflate(R.menu.options_menu, menu);
       // use the xml file
      //menu.findItem(R.id.settimebutton).setChecked(true);//change?
      // interactionMode = R.id.settimebutton;

       return true;
   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        return true;

    }*/

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

