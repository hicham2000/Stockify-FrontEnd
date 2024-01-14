package com.example.stockifi.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stockifi.R;

import java.util.ArrayList;

public class GridAdapter extends BaseAdapter {
    Context context;
    ArrayList<listData> listdata;

    public GridAdapter(Context context, ArrayList<listData> listdata) {
        this.context = context;
        this.listdata = listdata;

    }

    LayoutInflater inflater;
    @Override
    public int getCount() {
        return listdata.toArray().length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.grid_all, null);
        }

        ImageView imageView = convertView.findViewById(R.id.gridImage);
        TextView textView = convertView.findViewById(R.id.gridName);
        TextView textView1 = convertView.findViewById(R.id.gridName);
        //imageView.setImageResource(image[position]);
        textView.setText(listdata.get(position).getIntitule());
        textView1.setText(listdata.get(position).getDateExpiration());
        return convertView;
    }
}
