
package com.nodistracion.evelijn.nodistraction;

/**
 * Created by Evelijn on 7-3-2018.
 */

import android.graphics.drawable.Drawable;

public class AppList {

    private String name;
    Drawable icon;

    public AppList(String name, Drawable icon) {
        this.name = name;
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public Drawable getIcon() {
        return icon;
    }
}
