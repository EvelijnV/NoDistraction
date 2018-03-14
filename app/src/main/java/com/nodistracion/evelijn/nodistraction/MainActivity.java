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
        setContentView(R.layout.activity_main);
        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "bebasneue.otf");
        TextView myTextview=findViewById(R.id.textView2);
        myTextview.setTypeface(myTypeface);
        TextView myButton=findViewById(R.id.button);
        myButton.setTypeface(myTypeface);

    }

    public void onClick(View view) {
        Intent intent = new Intent(this, ItemOnorOff.class);
        startActivity(intent);
    }
}
