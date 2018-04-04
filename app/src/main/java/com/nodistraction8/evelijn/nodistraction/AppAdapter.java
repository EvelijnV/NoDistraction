/*
package com.nodistraction4.evelijn.nodistraction;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

*/
/**
 * Created by Evelijn on 21-3-2018.
 *//*


public class AppAdapter extends BaseAdapter {
    public ArrayList<String> appsOn = new ArrayList<String>();
    private LayoutInflater layoutInflater;
    private List<com.nodistraction8.evelijn.nodistraction.AppList> listStorage;

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



    static class ViewHolder {

        TextView textInListView;
        ImageView imageInListView;
        Switch switchInListView;
    }
}

*/
