package com.nodistracion.evelijn.nodistraction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

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
}