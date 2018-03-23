package com.nodistracion.evelijn.nodistraction;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nodistracion.evelijn.nodistraction.ListofApps.ListofApps;

/**
 * Created by Cassey on 16/03/2018.
 */

public class TimerActivity extends AppCompatActivity{


    private Button settimebutton;
    private TimePicker timepicker;
    public int selectedHour;
    public int selectedMinute;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        settimebutton = findViewById((R.id.settimebutton));
        timepicker = findViewById(R.id.timePicker);
        timepicker.setIs24HourView(true);

    }

    public void onClickSet (View view) {
        selectedHour = timepicker.getHour();
        selectedMinute = timepicker.getMinute();
        Intent intent = new Intent(this, ItemsActivity.class);
            startActivity(intent);

        /*Intent intent1 = new Intent(getApplicationContext(), ItemsActivity.class);
        //Create a bundle object
        Bundle b = new Bundle();

        //Inserts a String value into the mapping of this Bundle
        b.putString("On", on1.getText().toString());
        b.putString("age", age.getText().toString());
        int id = genderRadioGroup.getCheckedRadioButtonId();
        TextView On1 = (TextView) findViewById(id);
        b.putString("gender", radioButton.getText().toString());

        //Add the bundle to the intent.
        intent.putExtras(b);

        //start the DisplayActivity
        startActivity(intent1);*/

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
