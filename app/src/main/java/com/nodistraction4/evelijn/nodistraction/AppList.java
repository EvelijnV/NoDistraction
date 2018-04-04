package com.nodistraction4.evelijn.nodistraction;

import android.graphics.drawable.Drawable;

/**
 * Created by Evelijn on 21-3-2018.
 */

public class AppList {
    private String name;
    Drawable icon;
    String packageName;

    public AppList(String name,String PackageName,Drawable icon){
        this.name = name;
        this.icon = icon;
        this.packageName = PackageName;
    }

    public String getName(){
        return name;
    }

    public Drawable getIcon(){
        return icon;
    }
    public String getPackageName(){
        return packageName;
    }
}
