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

public class GridAdapter2 extends BaseAdapter {
    Context context;
    ArrayList<listData2> listdata2;

    public GridAdapter2(Context context, ArrayList<listData2> listdata2) {
        this.context = context;
        this.listdata2 = listdata2;
    }

    LayoutInflater inflater;
    @Override
    public int getCount() {
        return listdata2.toArray().length;
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
        textView.setText(listdata2.get(position).getIntitule());
        textView1.setText(listdata2.get(position).getDatePeremtion());
        return convertView;
    }
}
