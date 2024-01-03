package com.example.stockifi.recettes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class ProduitsSelectionneAdapter extends RecyclerView.Adapter<ProduitsSelectionneAdapter.ViewHolder> {

    private final List<String> produitsList;

    public ProduitsSelectionneAdapter(List<String> produitsList) {
        this.produitsList = produitsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View produitView = inflater.inflate(R.layout.produit_selectionne_item, parent, false);
        return new ViewHolder(produitView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String produit = produitsList.get(position);
        holder.produitSelectionneChip.setText(produit);

        holder.produitSelectionneChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle chip click event
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.produitSelectionneLinearLayout.setVisibility(View.GONE);
//                System.out.println(holder.produitSelectionneChip.getText() + " Clicked !");
//                System.out.println(holder.produitSelectionneLinearLayout.getVisibility());
                produitsList.remove(produit);
                notifyDataSetChanged();

            }
        });
    }

    @Override
    public int getItemCount() {
        return produitsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout produitSelectionneLinearLayout;
        Chip produitSelectionneChip;
        Button deleteButton;

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            produitSelectionneLinearLayout = itemView.findViewById(R.id.produit_selectionne_chip_linearLayout);
            produitSelectionneChip = itemView.findViewById(R.id.produit_selectionne_chip);
            deleteButton = itemView.findViewById(R.id.delete_button_produit_selectionne);
        }
    }
}
