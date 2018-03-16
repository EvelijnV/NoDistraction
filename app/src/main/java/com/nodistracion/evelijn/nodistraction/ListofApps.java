package com.nodistracion.evelijn.nodistraction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.Timer;

/**
 * Created by Cassey on 16/03/2018.
 */

public class ListofApps extends AppCompatActivity {

    private Button timebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listapps);
        timebutton=(Button)findViewById((R.id.timebutton));
    }

    public void TimerClicked (View view) {
        {Intent intent=new Intent(this,
                TimerActivity.class);
            startActivity(intent);}
    }
}