package com.nodistracion.evelijn.nodistraction;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class ListofApps extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listapps);
        //Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/bebasneue.otf");
        //TextView myTextview=findViewById(R.id.installed_app_list);
       // myTextview.setTypeface(myTypeface);

        ListView userInstalledApps = (ListView)findViewById(R.id.installed_app_list);

        List<AppList> installedApps = getInstalledApps();
        com.nodistracion.evelijn.nodistraction.AppAdapter installedAppAdapter = new com.nodistracion.evelijn.nodistraction.AppAdapter(ListofApps.this, installedApps);
        userInstalledApps.setAdapter(installedAppAdapter);



        //this piece below, can't seem to solve it, but it should block apps
       /* ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRecentTasks(1, ActivityManager.RECENT_WITH_EXCLUDED);
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo)(i.next());
            try {
                CharSequence c = pm.getApplicationLabel(pm.getApplicationInfo(
                        info.processName, PackageManager.GET_META_DATA));
                Log.w("LABEL", c.toString());
                //created the xml blockmessage to show up when an app is opened, perhaps should be used on the place of LABEL?
            } catch (Exception e) {
// Name Not Found Exception
            }
        }*/

    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    private List<AppList> getInstalledApps() {
        List<AppList> res = new ArrayList<AppList>();
        List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            if ((isSystemPackage(p) == false || isSystemPackage(p))) { // for now show both system and installed apps
                String appName = p.applicationInfo.loadLabel(getPackageManager()).toString();
                Drawable icon = p.applicationInfo.loadIcon(getPackageManager());
                res.add(new com.nodistracion.evelijn.nodistraction.AppList(appName, icon));
            }
        }
        return res;
    }

    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true : false;
    }



}