package com.example.stockifi.recettes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stockifi.R;
import com.google.android.material.chip.Chip;

import java.util.List;

public class FiltreProduitsSelectionneAdapter extends RecyclerView.Adapter<FiltreProduitsSelectionneAdapter.ViewHolder>{
    private List<String> produitsList;

    public FiltreProduitsSelectionneAdapter(List<String> produitsList) {
        this.produitsList = produitsList;
    }

    public void setSelectedProduits(List<String> produitsList) {
        this.produitsList = produitsList;
    }

    public List<String> getSelectedProduits() {
        return produitsList;
    }

    @NonNull
    @Override
    public FiltreProduitsSelectionneAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View produitView = inflater.inflate(R.layout.recette_produit_selectionne_item, parent, false);
        return new FiltreProduitsSelectionneAdapter.ViewHolder(produitView);
    }

    @Override
    public void onBindViewHolder(@NonNull FiltreProduitsSelectionneAdapter.ViewHolder holder, int position) {
        String produitName = produitsList.get(position);
        holder.produitSelectionneTextView.setText(produitName);


        holder.produitSelectionneCheckbox.setOnCheckedChangeListener(null);
        holder.produitSelectionneCheckbox.setChecked(true);
        holder.produitSelectionneCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(isChecked) {
                System.out.println(produitName + " Checked ");
            }
        });
    }

    @Override
    public int getItemCount() {
        return produitsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox produitSelectionneCheckbox;
        TextView produitSelectionneTextView;

        @SuppressLint("WrongViewCast")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            produitSelectionneCheckbox = itemView.findViewById(R.id.check_box_produit_selectionner);
            produitSelectionneTextView = itemView.findViewById(R.id.text_view_produit_selectionner);
        }
    }
}
