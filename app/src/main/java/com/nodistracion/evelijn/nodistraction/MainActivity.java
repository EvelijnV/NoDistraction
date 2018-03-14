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



    }
    public void onClickList(View view){
        Intent intent=new Intent(this,ListofApps.class);
        startActivity(intent);
    }
}
