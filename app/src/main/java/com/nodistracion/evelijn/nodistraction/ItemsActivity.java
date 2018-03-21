package com.nodistracion.evelijn.nodistraction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nodistracion.evelijn.nodistraction.ListofApps.ListofApps;

/**
 * Created by Cassey on 14/03/2018.
 */

public class ItemsActivity extends AppCompatActivity {

    private Button homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemonoroff);
        homebutton=(Button)findViewById((R.id.homebutton));
}
    public void HomeClicked (View view) {
        {Intent intent=new Intent(this,
                MainActivity.class);
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
    }
}