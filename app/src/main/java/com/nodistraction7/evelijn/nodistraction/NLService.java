package com.nodistraction7.evelijn.nodistraction;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import java.util.ArrayList;

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private NLServiceReceiver nlservicereciver;

    public ArrayList<String> blockedNotifications = new ArrayList<String>();
    public boolean notifyBlockOn = false;

    @Override
    public void onCreate() {
        super.onCreate();
        nlservicereciver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.nodistraction7.evelijn.nodistraction");
        registerReceiver(nlservicereciver,filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(nlservicereciver);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        Log.i(TAG,"**********  onNotificationPosted");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText + "\t" + sbn.getPackageName());
        Intent i = new  Intent("com.nodistraction7.evelijn.nodistraction");
        i.putExtra("notification_event","onNotificationPosted :" + sbn.getPackageName() + "\n");
        sendBroadcast(i);

        //if notification is from app in list blockedNotifications directely delete notification
        if(notifyBlockOn && blockedNotifications.contains(sbn.getPackageName())){
            cancelNotification(sbn.getKey());
            System.out.println("notification blocked");
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG,"********** onNOtificationRemoved");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
        Intent i = new  Intent("com.nodistraction7.evelijn.nodistraction");
        i.putExtra("notification_event","onNotificationRemoved :" + sbn.getPackageName() + "\n");

        sendBroadcast(i);
    }

    class NLServiceReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            System.out.println("in onRecieve");
            String command=intent.getStringExtra("command");
            if(command==null){
                System.out.println("No command!");
                return;
            }
            else if(command.equals("clearall")){
                NLService.this.cancelAllNotifications();
            }
            else if(command.equals("list")){
                Intent i1 = new  Intent("com.nodistraction7.evelijn.nodistraction");
                i1.putExtra("notification_event","=====================");
                sendBroadcast(i1);
                int i=1;
                for (StatusBarNotification sbn : NLService.this.getActiveNotifications()) {
                    Intent i2 = new  Intent("com.nodistraction7.evelijn.nodistraction");
                    i2.putExtra("notification_event",i +" " + sbn.getPackageName() + "\n");
                    sendBroadcast(i2);
                    i++;
                }
                Intent i3 = new  Intent("com.nodistraction7.evelijn.nodistraction");
                i3.putExtra("notification_event","===== Notification List ====");
                sendBroadcast(i3);

            }
            else if(command.equals("filterChanged")){
                blockedNotifications = intent.getStringArrayListExtra("filter");
                System.out.println(blockedNotifications);
            }
            else if(command.equals("notifyBlockOnChanged")){
                notifyBlockOn = intent.getExtras().getBoolean("notifyblockOn");
                System.out.println("in NLService notifyBlockOn = "+ notifyBlockOn);
            }

        }
    }
}
