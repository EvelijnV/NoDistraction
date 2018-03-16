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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
