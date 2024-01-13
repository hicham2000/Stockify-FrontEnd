package com.example.stockifi.Home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stockifi.R;

public class GridCategorieAdapter extends BaseAdapter {
    Context context;
    String[] itemsName;

    public GridCategorieAdapter(Context context, String[] itemsName) {
        this.context = context;
        this.itemsName = itemsName;
    }
    LayoutInflater inflater;
    @Override
    public int getCount() {
        return itemsName.length;
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
            convertView = inflater.inflate(R.layout.grid_categorie, null);
        }
        TextView textView = convertView.findViewById(R.id.gridCatégorie);
        textView.setText(itemsName[position]);
        return convertView;
    }
}
