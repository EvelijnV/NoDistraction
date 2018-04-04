package com.nodistraction8.evelijn.nodistraction;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private TextView txtView;
    private NotificationReceiver nReceiver;
    public ArrayList<String> blockedNotifications = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        blockedNotifications = getIntent().getStringArrayListExtra("filter");
        System.out.println("opened main, apps now in blockednotifications:  " + blockedNotifications);

        if (!NotificationManagerCompat.getEnabledListenerPackages(this).contains(getApplicationContext().getPackageName())) {
            //(piece of code to open right settings
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.textView);
        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.nodistraction8.evelijn.nodistraction");
        registerReceiver(nReceiver,filter);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }
    public void switchClicked(View v){
        Intent i = new Intent("com.nodistraction8.evelijn.nodistraction");
        i.putExtra("command","notifyBlockOnChanged");
        Switch notifySwitch = (Switch) v ;
        i.putExtra("NotifyBlockOn",notifySwitch.isChecked());
        System.out.println("in main notifyswict.isChecked is " + notifySwitch.isChecked());
        sendBroadcast(i);




    }

    public void buttonClicked(View v){

        if(v.getId() == R.id.btnCreateNotify){
            NotificationManager nManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder ncomp = new NotificationCompat.Builder(this);
            ncomp.setContentTitle("My Notification");
            ncomp.setContentText("Notification Listener Service Example");
            ncomp.setTicker("Notification Listener Service Example");
            ncomp.setSmallIcon(R.drawable.ic_launcher_foreground);
            ncomp.setAutoCancel(true);
            nManager.notify((int)System.currentTimeMillis(),ncomp.build());
        }
        else if(v.getId() == R.id.btnClearNotify){
            Intent i = new Intent("com.nodistraction8.evelijn.nodistraction");
            i.putExtra("command","clearall");
            sendBroadcast(i);
        }
        else if(v.getId() == R.id.btnListNotify){
            Intent i = new Intent("com.nodistraction8.evelijn.nodistraction");
            i.putExtra("command","list");
            sendBroadcast(i);
        }
        else if(v.getId() == R.id.btnSelectApps){
            Intent i = new Intent(this,ListofAppsActivity.class);
            //i.putExtra("filter",blockedNotifications);
            startActivity(i);
        }

    }

    class NotificationReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String temp = intent.getStringExtra("notification_event") + "\n" + txtView.getText();
            txtView.setText(temp);
        }
    }

}