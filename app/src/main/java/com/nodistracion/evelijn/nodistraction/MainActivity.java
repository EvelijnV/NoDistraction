package com.nodistracion.evelijn.nodistraction;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "bebasneue.otf");
        TextView myTextview=findViewById(R.id.textView2);
        myTextview.setTypeface(myTypeface);


        //this is a test line
        //ok omdat ij het zegt
    }
}
