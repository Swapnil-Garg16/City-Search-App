package com.example.hp.citysearchapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by hp on 22-03-2016.
 */
public class CustomListAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<City> cityItems;

    public CustomListAdapter(Activity activity, List<City> cityItems) {
        this.activity = activity;
        this.cityItems = cityItems;
    }

    @Override
    public int getCount() {
        return cityItems.size();
    }

    @Override
    public Object getItem(int position) {
        return cityItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.custom_adapter, null);

        TextView id = (TextView) convertView.findViewById(R.id.id);
        TextView city = (TextView) convertView.findViewById(R.id.title_city);
        TextView description = (TextView) convertView.findViewById(R.id.description_city);
        TextView exv1 = (TextView) convertView.findViewById(R.id.extra_value1);
        TextView exv2 = (TextView) convertView.findViewById(R.id.extra_value2);
        TextView exv3 = (TextView) convertView.findViewById(R.id.extra_value3);
        TextView exv4 = (TextView) convertView.findViewById(R.id.extra_value4);
        TextView exv5 = (TextView) convertView.findViewById(R.id.extra_value5);
        TextView exv6 = (TextView) convertView.findViewById(R.id.extra_value6);
        TextView exv7 = (TextView) convertView.findViewById(R.id.extra_value7);
        TextView exv8 = (TextView) convertView.findViewById(R.id.extra_value8);
        TextView exv9 = (TextView) convertView.findViewById(R.id.extra_value9);

        City c = cityItems.get(position);


        // id
        id.setText(c.getId());
        //city
        city.setText(c.getTitle());
        //description
        description.setText(c.getDescription());
        //exv1
        exv1.setText(c.getExv1());
        //exv2
        exv2.setText(c.getExv2());
        //exv3
        exv3.setText(c.getExv3());
        //exv4
        exv4.setText(c.getExv4());
        //exv5
        exv5.setText(c.getExv5());
        //exv6
        exv6.setText(c.getExv6());
        //exv7
        exv7.setText(c.getExv7());
        //exv8
        exv8.setText(c.getExv8());
        //exv9
        exv9.setText(c.getExv9());
        return convertView;
    }
}
