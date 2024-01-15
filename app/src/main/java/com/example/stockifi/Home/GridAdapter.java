package com.example.stockifi.Home;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stockifi.Gestion_Produit.InformationsProduitStock;
import com.example.stockifi.R;

import java.util.ArrayList;

public class GridAdapter extends ArrayAdapter<listData> {
    ArrayList<listData> listdata;

    public GridAdapter(Context context, ArrayList<listData> listdata) {
        super(context, 0 ,listdata);
        this.listdata = listdata;

    }

    LayoutInflater inflater;
    @Override
    public int getCount() {
        return listdata.toArray().length;
    }

    @Override
    public listData getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater.inflate(R.layout.grid_all, null);
        }

        ImageView imageView = (ImageView) convertView.findViewById(R.id.gridImage);
        TextView textView = convertView.findViewById(R.id.gridName);
        TextView textView1 = convertView.findViewById(R.id.gridtemps);
        imageView.setImageResource(R.drawable.icon_produit);
        textView.setText(listdata.get(position).getIntitule());
        textView1.setText(listdata.get(position).getDateExpiration());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData produit = listdata.get(position);
                int produitid = produit.getId();
                Intent intent = new Intent(getContext(), InformationsProduitStock.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("produitid", Integer.toString(produitid));
                System.out.println(intent.getStringExtra("produitid"));
                getContext().startActivity(intent);

            }
        });

        return convertView;

    }
}
