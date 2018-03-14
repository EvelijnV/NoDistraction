package com.nodistracion.evelijn.nodistraction;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        /*Typeface myTypeface = Typeface.createFromAsset(getAssets(), "bebasneue.otf");
        TextView myTextview=findViewById(R.id.textView2);
        myTextview.setTypeface(myTypeface);
        TextView myButton=findViewById(R.id.button);
        myButton.setTypeface(myTypeface);/*
        TextView mylist=findViewById(R.id.list_app_name);
        mylist.setTypeface(myTypeface);
        TextView myprofilename=findViewById(R.id.txt_profile_name);
        myprofilename.setTypeface(myTypeface);
        TextView mydays=findViewById(R.id.txt_days);
        mydays.setTypeface(myTypeface);
        TextView myonoff=findViewById(R.id.txt_on_off);
        myonoff.setTypeface(myTypeface);
        TextView myapp=findViewById(R.id.installed_app_list);
        myapp.setTypeface(myTypeface);*/
    }

    public void onClickSet(View view) {
        Intent intent = new Intent(this, ItemOnorOff.class);
        startActivity(intent);
    }


}
