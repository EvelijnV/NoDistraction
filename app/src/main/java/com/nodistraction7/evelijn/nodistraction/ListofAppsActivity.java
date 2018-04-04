package com.nodistraction7.evelijn.nodistraction;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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


public class ListofAppsActivity extends Activity {
    public ArrayList<String> appsOn = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listof_apps);
        //appsOn = getIntent().getStringArrayListExtra("filter");
        //Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/bebasneue.otf");
        //TextView myTextview=findViewById(R.id.installed_app_list);
        // myTextview.setTypeface(myTypeface);

        ListView userInstalledApps = (ListView)findViewById(R.id.appList);

        List<AppList> installedApps = getInstalledApps();
        AppAdapter installedAppAdapter = new AppAdapter(ListofAppsActivity.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);
    }

    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false || isSystemPackage(p))) { // for now show both system and installed apps
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                String packageName = p.applicationInfo.packageName;
                res.add(new com.nodistraction7.evelijn.nodistraction.AppList(appName,packageName, icon));
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }

    public class AppAdapter extends BaseAdapter {
        private LayoutInflater layoutInflater;
        private List<com.nodistraction7.evelijn.nodistraction.AppList> listStorage;

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
                convertView = layoutInflater.inflate(R.layout.installedapp, parent, false);

                listViewHolder.textInListView = (TextView) convertView.findViewById(R.id.list_app_name);
                listViewHolder.imageInListView = (ImageView) convertView.findViewById(R.id.app_icon);
                listViewHolder.switchInListView = (Switch) convertView.findViewById(R.id.appSwitch);
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
                        System.out.println(appsOn);
                        //Intent i
                    }
                    else if(!isChecked && appsOn.contains(listStorage.get(position).getPackageName())){
                        appsOn.remove(listStorage.get(position).getPackageName());
                        System.out.println(appsOn);
                    }
                }
            });
            listViewHolder.switchInListView.setChecked(appsOn.contains(listStorage.get(position).getPackageName()));

            return convertView;
        }



         class ViewHolder {

            TextView textInListView;
            ImageView imageInListView;
            Switch switchInListView;
        }
    }

    public void OnSaveAppsBtnClicked (View v){
        Intent i2 = new Intent("com.nodistraction7.evelijn.nodistraction");
        i2.putExtra("command", "filterChanged");
        i2.putExtra("filter",appsOn);
        sendBroadcast(i2);
        Intent i = new Intent(this,MainActivity.class);
        i.putExtra("filter",appsOn);
        System.out.println("go back to main, new list of apps is:  " + appsOn);
        startActivity(i);
    }

}