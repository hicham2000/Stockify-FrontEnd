package com.example.stockifi.Home;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stockifi.R;
import com.example.stockifi.Repas.ViewRepas;

import java.util.ArrayList;

public class GridAdapter2 extends ArrayAdapter<listData2> {

    ArrayList<listData2> listdata2;

    public GridAdapter2(Context context, ArrayList<listData2> listdata2) {
        super(context, 0 ,listdata2);
        this.listdata2 = listdata2;

    }

    LayoutInflater inflater;
    @Override
    public int getCount() {
        return listdata2.toArray().length;
    }

    @Override
    public listData2 getItem(int position) {
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

        ImageView imageView = convertView.findViewById(R.id.gridImage);
        TextView textView = convertView.findViewById(R.id.gridName);
        TextView textView1 = convertView.findViewById(R.id.gridtemps);
        imageView.setImageResource(R.drawable.instagram);
        textView.setText(listdata2.get(position).getIntitule());
        textView1.setText(listdata2.get(position).getDatePeremtion());

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData2 repas = listdata2.get(position);
                int repasid = repas.getId();
                Intent intent = new Intent(getContext(), ViewRepas.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("repasid", Integer.toString(repasid));
                System.out.println(intent.getStringExtra("repasid"));
                getContext().startActivity(intent);

            }
        });

        return convertView;
    }
}
