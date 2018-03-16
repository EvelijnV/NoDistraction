package com.nodistracion.evelijn.nodistraction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Cassey on 16/03/2018.
 */

public class TimerActivity extends AppCompatActivity{


    private Button settimebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        settimebutton=(Button)findViewById((R.id.settimebutton));
    }

    public void onClickSet (View view) {
        {Intent intent=new Intent(this,
                ItemsActivity.class);
            startActivity(intent);}
    }
}
