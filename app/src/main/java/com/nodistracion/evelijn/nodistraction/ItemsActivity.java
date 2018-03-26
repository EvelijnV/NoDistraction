package com.nodistracion.evelijn.nodistraction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.nodistracion.evelijn.nodistraction.ListofApps.ListofApps;

/**
 * Created by Cassey on 14/03/2018.
 */

public class ItemsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button homebutton;
    private TextView On1;
    private TextView On2;
    private TextView Off1;
    private TextView Off2;
    int OnorOff;

    long diffTime;

    private CountDownTimer countDownTimer;
    private boolean timerHasStarted = false;
    private Button startB;
    public TextView text;
    //private final long startTime=diffTime;
    private final long interval = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemonoroff);
        homebutton=(Button)findViewById((R.id.homebutton));

        OnorOff = getIntent().getIntExtra("OnorOff", 0);
        diffTime=getIntent().getLongExtra("diffTime", 0);


        startB = (Button) this.findViewById(R.id.button);
        startB.setOnClickListener(this);
        text = (TextView) this.findViewById(R.id.timer);
        countDownTimer = new MyCountDownTimer(diffTime, interval);
        int hours = (int) ((diffTime / (1000 * 60 * 60)) % 24);
        int minutes = (int) ((diffTime / (1000 * 60)) % 60);
        int seconds = (int) (diffTime / 1000) % 60;

        text.setText(String.format("%02d", hours)
                + ":" + String.format("%02d", minutes)
                + ":" + String.format("%02d", seconds));
        //text.setText(text.getText() + String.valueOf(diffTime / 1000));


       // System.out.println();

        if(OnorOff!=1){
        On1=(TextView)findViewById(R.id.txt_on1);
        On1.setVisibility(View.INVISIBLE);
        On2=(TextView)findViewById(R.id.txt_on2);
        On2.setVisibility(View.INVISIBLE);}
        else{
            On1=(TextView)findViewById(R.id.txt_on1);
            On1.setVisibility(View.VISIBLE);
            On2=(TextView)findViewById(R.id.txt_on2);
            On2.setVisibility(View.VISIBLE);
            Off1=(TextView)findViewById(R.id.txt_off1);
            Off1.setVisibility(View.INVISIBLE);
            Off2=(TextView)findViewById(R.id.txt_off2);
            Off2.setVisibility(View.INVISIBLE);}
    }

    @Override
    public void onClick(View v) {
        if(!timerHasStarted) {
            countDownTimer.start();
            timerHasStarted = true;
            startB.setVisibility(View.INVISIBLE);
        }
    }

    public class MyCountDownTimer extends CountDownTimer {
        public MyCountDownTimer(long diffTime, long interval) {
            super(diffTime, interval);
        }

        @Override
        public void onFinish() {
            text.setText("Dobby is a Free Elf!");
        }

        @Override
        public void onTick(long millisUntilFinished) {

            int hours = (int) ((millisUntilFinished / (1000 * 60 * 60)) % 24);
            int minutes = (int) ((millisUntilFinished / (1000 * 60)) % 60);
            int seconds = (int) (millisUntilFinished / 1000) % 60;

            text.setText(String.format("%02d", hours)
                    + ":" + String.format("%02d", minutes)
                    + ":" + String.format("%02d", seconds));
            //text.setText("" + millisUntilFinished / 1000);
        }
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