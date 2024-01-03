package com.example.stockifi.recettes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.R;

import java.util.ArrayList;
import java.util.List;

public class RegimeSpeciauxAdapter extends RecyclerView.Adapter<RegimeSpeciauxAdapter.ViewHolder> {

    private final List<String> regimeList;
    private int clickCount;
    private List<String> clickedItems;

    public RegimeSpeciauxAdapter(List<String> regimeList) {
        this.regimeList = regimeList;
        this.clickCount = 0;
        this.clickedItems = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View regimeView = inflater.inflate(R.layout.regime_speciaux_button_item, parent, false);
        return new ViewHolder(regimeView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String regime = regimeList.get(position);
        holder.RegimeSpecialbutton.setText(regime);

        holder.RegimeSpecialbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount = (clickCount + 1) % 2;
                if (clickCount == 1) {
                    // Odd clicks, change text color to black
                    holder.RegimeSpecialbutton.setTextColor(v.getResources().getColor(android.R.color.black));
                    // Store the clicked item text
                    String clickedItemText = holder.RegimeSpecialbutton.getText().toString();
                    clickedItems.add(clickedItemText);
                } else {
                    // Even clicks, change text color to white
                    holder.RegimeSpecialbutton.setTextColor(v.getResources().getColor(android.R.color.white));
                }

                System.out.println("clicked buttons : " + getClickedItems());
            }
        });
    }

    // Method to get the clicked items
    public List<String> getClickedItems() {
        return clickedItems;
    }

    @Override
    public int getItemCount() {
        return regimeList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        Button RegimeSpecialbutton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            RegimeSpecialbutton = itemView.findViewById(R.id.regime_special_button);
        }
    }
}
