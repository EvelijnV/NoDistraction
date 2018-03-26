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
import java.util.Calendar;
import android.widget.TimePicker.OnTimeChangedListener;

import com.nodistracion.evelijn.nodistraction.ListofApps.ListofApps;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Cassey on 16/03/2018.
 */

public class TimerActivity extends AppCompatActivity implements OnTimeChangedListener{


    public  int OnorOff;
    private Button settimebutton;
    private Button calculate;
    private TimePicker timepicker;
    public int selectedHour;
    public int selectedMinute;
    private TextView sysCurrent;
    private TextView difference;
    private Calendar calendar;

    private static final int hoursInMilis = 3600000;
    private static final int minutesInMilis = 60000;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        settimebutton = findViewById((R.id.settimebutton));
        timepicker = findViewById(R.id.timePicker);
        timepicker.setIs24HourView(true);

        timepicker.setOnTimeChangedListener((OnTimeChangedListener) this);

        //sysCurrent = (TextView) findViewById(R.id.systemTime);
        difference = (TextView) findViewById(R.id.difference);

    }

    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        selectedHour = hourOfDay;
        selectedMinute = minute;
    }

    public void calculate(View view)
    {
        long currentTime = System.currentTimeMillis();
        long diffTime = 0;

        calendar = Calendar.getInstance();
        //sysCurrent.setText(currentTime +"");

        calendar.setTimeInMillis(currentTime);
        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE, selectedMinute);

        diffTime = calendar.getTimeInMillis()- currentTime;

        difference.setText(String.format("%02d:%02d",
                diffTime/hoursInMilis,
                (diffTime%hoursInMilis)/minutesInMilis));




    }


    public void onClickSet (View view) {
       /* selectedHour = timepicker.getHour();
        selectedMinute = timepicker.getMinute();*/
        OnorOff=1;

        long currentTime = System.currentTimeMillis();
        long diffTime = 0;

        calendar = Calendar.getInstance();
        //sysCurrent.setText(currentTime +"");

        calendar.setTimeInMillis(currentTime);
        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
        calendar.set(Calendar.MINUTE, selectedMinute);
        diffTime = calendar.getTimeInMillis()- currentTime;





        Intent intent = new Intent(this, ItemsActivity.class);
        intent.putExtra("OnorOff", OnorOff);
        intent.putExtra("diffTime", diffTime);
            startActivity(intent);




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
