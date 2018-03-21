package com.nodistraction4.evelijn.nodistraction;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private TextView txtView;
    private NotificationReceiver nReceiver;
    public ArrayList<String> blockedNotifications = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!NotificationManagerCompat.getEnabledListenerPackages(this).contains(getApplicationContext().getPackageName())) {
            //(piece of code to open right settings
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }
        setContentView(R.layout.activity_main);
        txtView = (TextView) findViewById(R.id.textView);
        nReceiver = new NotificationReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.nodistraction4.evelijn.nodistraction");
        registerReceiver(nReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nReceiver);
    }
    public void switchClicked(View v){
        Intent i = new Intent("com.nodistraction4.evelijn.nodistraction");
        i.putExtra("command","filterChanged");
        Switch notifySwitch = (Switch) v ;

        // put all apps in the blockedNotifications when switch is on
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        ArrayList<String> allPackageNames = new ArrayList<>();
        for (int j=0; j<packs.size(); j++){
            PackageInfo p = packs.get(j);
            allPackageNames.add(p.packageName);
        }
        if (notifySwitch.isChecked()){
            blockedNotifications.addAll(allPackageNames);
        }
        else{
            blockedNotifications.removeAll(allPackageNames);
        }
        i.putExtra("filter",blockedNotifications);
        sendBroadcast(i);
        System.out.println(blockedNotifications);



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
            Intent i = new Intent("com.nodistraction4.evelijn.nodistraction");
            i.putExtra("command","clearall");
            sendBroadcast(i);
        }
        else if(v.getId() == R.id.btnListNotify){
            Intent i = new Intent("com.nodistraction4.evelijn.nodistraction");
            i.putExtra("command","list");
            sendBroadcast(i);
        }
        else if(v.getId() == R.id.btnSelectApps){
            Intent i = new Intent(this,SelectAppsActivity.class);
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