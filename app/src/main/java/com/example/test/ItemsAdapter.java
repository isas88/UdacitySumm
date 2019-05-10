package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemsAdapter extends ArrayAdapter<Items> {

    public ItemsAdapter(Context context, ArrayList<Items> items) {
        super(context, 0,items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemview = convertView;

        if (itemview == null){
            itemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Items currentitem = getItem(position);

        TextView headitem = itemview.findViewById(R.id.head);
        TextView descitem = itemview.findViewById(R.id.desc);
        headitem.setText(currentitem.getHead());
        descitem.setText(currentitem.getDesc());

        return itemview;

    }
}
