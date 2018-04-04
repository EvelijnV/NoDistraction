package com.nodistraction4.evelijn.nodistraction;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NLService extends NotificationListenerService {

    private String TAG = this.getClass().getSimpleName();
    private NLServiceReceiver nlservicereciver;

    public ArrayList<String> blockedNotifications = new ArrayList<String>();

    @Override
    public void onCreate() {
        super.onCreate();
        nlservicereciver = new NLServiceReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.nodistraction4.evelijn.nodistraction");
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
        Intent i = new  Intent("com.nodistraction4.evelijn.nodistraction");
        i.putExtra("notification_event","onNotificationPosted :" + sbn.getPackageName() + "\n");
        sendBroadcast(i);

        //if notification is from app in list blockedNotifications directely delete notification
        if(blockedNotifications.contains(sbn.getPackageName())){
            cancelNotification(sbn.getKey());
            System.out.println("notification blocked");
        }

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i(TAG,"********** onNOtificationRemoved");
        Log.i(TAG,"ID :" + sbn.getId() + "\t" + sbn.getNotification().tickerText +"\t" + sbn.getPackageName());
        Intent i = new  Intent("com.nodistraction4.evelijn.nodistraction");
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
            else if(intent.getStringExtra("command").equals("clearall")){
                NLService.this.cancelAllNotifications();
            }
            else if(intent.getStringExtra("command").equals("list")){
                Intent i1 = new  Intent("com.nodistraction4.evelijn.nodistraction");
                i1.putExtra("notification_event","=====================");
                sendBroadcast(i1);
                int i=1;
                for (StatusBarNotification sbn : NLService.this.getActiveNotifications()) {
                    Intent i2 = new  Intent("com.nodistraction4.evelijn.nodistraction");
                    i2.putExtra("notification_event",i +" " + sbn.getPackageName() + "\n");
                    sendBroadcast(i2);
                    i++;
                }
                Intent i3 = new  Intent("com.nodistraction4.evelijn.nodistraction");
                i3.putExtra("notification_event","===== Notification List ====");
                sendBroadcast(i3);

            }
            else if(intent.getStringExtra("command").equals("filterChanged")){
                blockedNotifications = intent.getStringArrayListExtra("filter");
                System.out.println(blockedNotifications);
            }

        }
    }

    public static class ListofAppsActivity extends Activity {
        public ArrayList<String> blockedNotifications;

        private MyBroadcastReciever Receiver;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            //blockedNotifications = getIntent().getStringArrayListExtra("filter");
            System.out.println("onCreate List of Apps, "+"these apps are in blockedNotifications: "+ blockedNotifications);
            super.onCreate(savedInstanceState);
            setContentView(com.nodistraction4.evelijn.nodistraction.R.layout.activity_listof_apps);

            ListView userInstalledApps = (ListView)findViewById(com.nodistraction4.evelijn.nodistraction.R.id.appList);

            List<AppList> installedApps = getInstalledApps();

            AppAdapter installedAppAdapter = new AppAdapter(this, installedApps);
            userInstalledApps.setAdapter(installedAppAdapter);

            Receiver = new MyBroadcastReciever();
            IntentFilter filter = new IntentFilter();
            filter.addAction("com.nodistraction4.evelijn.nodistraction");
            registerReceiver(Receiver,filter);


        }

        private List<AppList> getInstalledApps(){
            List<AppList> res = new ArrayList<AppList>();
            List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
            for(int i=0; i< packs.size(); i++){
                PackageInfo p = packs.get(i);
                if ((! isSystemPackage(p) || isSystemPackage(p))) { // for now show both system and installed apps
                    String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                    String packageName = p.packageName;
                    Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                    res.add(new AppList(appName,packageName,icon));
                }
            }
            return res;
        }

        private boolean isSystemPackage(PackageInfo pkgInfo){
            return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM)!= 0)? true : false;
        }
        public void OnSaveAppsBtnClicked(View v){
            Intent i = new Intent(this,MainActivity.class);
            i.putExtra("filter",blockedNotifications);
            startActivity(i);
            System.out.println("OnSaveAppsBtnClicked");
        }
        class MyBroadcastReciever extends BroadcastReceiver {

            @Override
            public void onReceive(Context context, Intent intent) {
                System.out.println("broadcast recieved");
                if(intent.getStringExtra("command").equals("filterChanged")){
                    blockedNotifications = intent.getStringArrayListExtra("filter");
                    System.out.println(blockedNotifications);
                }
            }
        }

        public class AppAdapter extends BaseAdapter {
            public ArrayList<String> appsOn = new ArrayList<String>();
            private LayoutInflater layoutInflater;
            private List<AppList> listStorage;

            public AppAdapter(Context context, List<AppList> customizedListView) {
                layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                listStorage = customizedListView;
            }

            @Override
            public int getCount() {
                return listStorage.size();
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }


            public String getPackageName(int position){return listStorage.get(position).getPackageName();}

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {

                ViewHolder listViewHolder;
                if (convertView == null) {
                    listViewHolder = new ViewHolder();
                    convertView = layoutInflater.inflate(com.nodistraction4.evelijn.nodistraction.R.layout.installedapp, parent, false);

                    listViewHolder.textInListView = (TextView) convertView.findViewById(com.nodistraction4.evelijn.nodistraction.R.id.list_app_name);
                    listViewHolder.imageInListView = (ImageView) convertView.findViewById(com.nodistraction4.evelijn.nodistraction.R.id.app_icon);
                    listViewHolder.switchInListView = (Switch) convertView.findViewById(com.nodistraction4.evelijn.nodistraction.R.id.appSwitch);
                    convertView.setTag(listViewHolder);

                } else {
                    listViewHolder = (ViewHolder) convertView.getTag();
                }
                listViewHolder.textInListView.setText(listStorage.get(position).getName());
                listViewHolder.imageInListView.setImageDrawable(listStorage.get(position).getIcon());

                listViewHolder.switchInListView.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked && !appsOn.contains(listStorage.get(position).getPackageName())){
                            appsOn.add(listStorage.get(position).getPackageName());
                        }
                        else if(!isChecked && appsOn.contains(listStorage.get(position).getPackageName())){
                            appsOn.remove(listStorage.get(position).getPackageName());
                        }
                        //System.out.println(appsOn);
                        Intent i = new Intent("com.nodistraction4.evelijn.nodistraction");
                        i.putExtra("command","filterChanged");
                        i.putExtra("filter",appsOn);
                        ListofAppsActivity.this.sendBroadcast(i);
                        //System.out.println("filterChanged broadcast send");
                    }
                });
                listViewHolder.switchInListView.setChecked(blockedNotifications.contains(listStorage.get(position).getPackageName()));

                return convertView;
            }



            class ViewHolder {

                TextView textInListView;
                ImageView imageInListView;
                Switch switchInListView;
            }
        }
    }
}
